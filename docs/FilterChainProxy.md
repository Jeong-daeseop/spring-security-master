# FilterChainProxy 상세 설명

## 위치와 역할

```
DelegatingFilterProxy
        ↓ 위임
FilterChainProxy        ← 여기
        ↓ 선택
SecurityFilterChain 1, 2, 3...
        ↓ 실행
각 보안 필터들
```

`DelegatingFilterProxy`가 서블릿 컨테이너와 스프링을 연결한다면,  
`FilterChainProxy`는 **실제 보안 필터 체인을 관리하고 실행하는 관제탑**입니다.

---

## 핵심 구조

```java
public class FilterChainProxy implements Filter {

    // 여러 개의 SecurityFilterChain을 보유
    private List<SecurityFilterChain> filterChains;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) {

        // 1. 요청 URL에 맞는 SecurityFilterChain 선택
        List<Filter> filters = getFilters(request);

        // 2. 선택된 필터 체인 순서대로 실행
        VirtualFilterChain virtualFilterChain = new VirtualFilterChain(chain, filters);
        virtualFilterChain.doFilter(request, response);
    }

    // URL 패턴 매칭으로 적합한 FilterChain 선택
    private List<Filter> getFilters(HttpServletRequest request) {
        for (SecurityFilterChain chain : filterChains) {
            if (chain.matches(request)) {  // URL 패턴 확인
                return chain.getFilters(); // 매칭된 체인의 필터 목록 반환
            }
        }
        return null; // 매칭 없으면 보안 처리 없이 통과
    }
}
```

---

## SecurityFilterChain 여러 개를 관리하는 이유

실제 서비스에서는 URL마다 다른 보안 정책이 필요합니다.

```java
// SecurityFilterChain 1: API 요청 (/api/**)
@Bean
@Order(1)
public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
    http
        .securityMatcher("/api/**")   // 이 URL 패턴에만 적용
        .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))
        .authorizeHttpRequests(a -> a.anyRequest().authenticated());
    return http.build();
}

// SecurityFilterChain 2: 일반 웹 요청
@Bean
@Order(2)
public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
    http
        .securityMatcher("/**")       // 나머지 모든 URL
        .formLogin(Customizer.withDefaults())
        .authorizeHttpRequests(a -> a.anyRequest().authenticated());
    return http.build();
}
```

```
GET /api/users
    ↓
FilterChainProxy
    ├── SecurityFilterChain 1 (/api/**) → 매칭! → 이 체인 실행
    └── SecurityFilterChain 2 (/**)    → 건너뜀

GET /home
    ↓
FilterChainProxy
    ├── SecurityFilterChain 1 (/api/**) → 불일치
    └── SecurityFilterChain 2 (/**)    → 매칭! → 이 체인 실행
```

---

## VirtualFilterChain — 내부 실행 방식

```java
// FilterChainProxy 내부 클래스 (의사코드)
private static class VirtualFilterChain implements FilterChain {

    private final List<Filter> additionalFilters; // 보안 필터 목록
    private final FilterChain originalChain;      // 서블릿 컨테이너의 원래 체인
    private int currentPosition = 0;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) {

        if (currentPosition == additionalFilters.size()) {
            // 모든 보안 필터 통과 → 원래 서블릿 체인으로 복귀
            originalChain.doFilter(request, response);
            return;
        }

        // 다음 보안 필터 실행
        Filter nextFilter = additionalFilters.get(currentPosition++);
        nextFilter.doFilter(request, response, this); // this = VirtualFilterChain
    }
}
```

```
VirtualFilterChain 실행 흐름:

[0] DisableEncodeUrlFilter
[1] WebAsyncManagerIntegrationFilter
[2] SecurityContextHolderFilter
[3] HeaderWriterFilter
[4] CsrfFilter
[5] LogoutFilter
[6] UsernamePasswordAuthenticationFilter  ← 인증
[7] ExceptionTranslationFilter            ← 예외 처리
[8] AuthorizationFilter                   ← 인가
        ↓ 모두 통과
originalChain (DispatcherServlet)         ← 실제 요청 처리
```

---

## FilterChainProxy가 제공하는 부가 기능

### 1 — SecurityContext 초기화 보장

```java
// 요청 시작 전 SecurityContext 클리어
// 응답 후 SecurityContext 클리어 (메모리 누수 방지)
SecurityContextHolder.clearContext();
```

### 2 — 방화벽 (HttpFirewall)

```java
// 악의적인 URL 패턴 차단
// 예: /./admin, /admin/../secret 같은 경로 조작 시도
private HttpFirewall firewall = new StrictHttpFirewall();

public void doFilter(...) {
    FirewalledRequest fwRequest = firewall.getFirewalledRequest(request);
    // 비정상 요청이면 여기서 RequestRejectedException 발생
}
```

### 3 — 디버그 로깅

```yaml
# application.yml
logging:
  level:
    org.springframework.security: DEBUG
```

```
# 출력 예시
Security filter chain: [
  DisableEncodeUrlFilter
  WebAsyncManagerIntegrationFilter
  SecurityContextHolderFilter
  ...
  AuthorizationFilter
]
```

---

## 전체 흐름 요약

```
HTTP 요청
    ↓
서블릿 컨테이너 Filter Chain
    ↓
DelegatingFilterProxy          (서블릿 ↔ 스프링 연결)
    ↓
FilterChainProxy               (보안 체인 관제탑)
    ├── HttpFirewall 검사
    ├── 매칭되는 SecurityFilterChain 선택
    └── VirtualFilterChain 실행
            ↓
        보안 필터들 순차 실행
            ↓
        DispatcherServlet      (실제 요청 처리)
```