<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security

## Purpose
Spring Security 전체 구성 패키지. Form 인증과 REST API 인증을 병렬로 구성하며, DB 기반 동적 인가를 구현한다.

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `configs/` | `SecurityConfig` — 두 개의 FilterChain 정의; `AuthConfig` — 공통 빈(PasswordEncoder, RoleHierarchy 등) |
| `details/` | `FormWebAuthenticationDetails` — 로그인 폼 추가 데이터(secretKey 등) 수집 |
| `dsl/` | `RestApiDsl` — REST 인증 필터 체인을 선언형으로 구성하는 커스텀 DSL |
| `entrypoint/` | `RestAuthenticationEntryPoint` — 미인증 REST 요청에 401 응답 |
| `exception/` | `SecretException` — 커스텀 보안 예외 |
| `filters/` | `RestAuthenticationFilter` — `/api/login` JSON 요청을 처리하는 커스텀 필터 |
| `handler/` | Form/REST 인증 성공·실패·접근거부 핸들러 (총 6개) |
| `listener/` | `SetupDataLoader` — 앱 시작 시 기본 admin 계정 + ROLE_ADMIN 생성 |
| `manager/` | `CustomDynamicAuthorizationManager` — DB에서 URL-Role 매핑을 읽어 동적으로 인가 결정 |
| `mapper/` | `UrlRoleMapper` 인터페이스 + `PersistentUrlRoleMapper`(DB) + `MapBasedUrlRoleMapper`(인메모리) |
| `provider/` | `FormAuthenticationProvider` + `RestAuthenticationProvider` — 커스텀 인증 처리 |
| `service/` | `FormUserDetailsService` (UserDetailsService 구현), `DynamicAuthorizationService` |
| `token/` | `RestAuthenticationToken` — REST 인증용 커스텀 Authentication 토큰 |

## For AI Agents

### Architecture

```
[Form 인증]
  /login (POST) → UsernamePasswordAuthenticationFilter
               → FormAuthenticationProvider
               → FormUserDetailsService.loadUserByUsername()
               → AccountContext (UserDetails)
               → FormAuthenticationSuccessHandler / FormAuthenticationFailureHandler

[REST 인증]
  /api/login (POST, JSON) → RestAuthenticationFilter
                          → RestAuthenticationProvider
                          → FormUserDetailsService.loadUserByUsername()
                          → RestAuthenticationSuccessHandler / RestAuthenticationFailureHandler

[동적 인가]
  모든 요청 → CustomDynamicAuthorizationManager.check()
            → PersistentUrlRoleMapper (DB: RESOURCES 테이블)
            → URL 패턴 매칭 → AuthorityAuthorizationManager or WebExpressionAuthorizationManager
            → 역할 계층(RoleHierarchyImpl) 적용
```

### Working In This Directory
- FilterChain 순서: REST(`@Order(1)`, `/api/**`) → Form(기본, `/**`)
- 인가 규칙 변경 후 반드시 `CustomDynamicAuthorizationManager.reload()` 호출
- 새 커스텀 필터 추가 시 `RestApiDsl`에 등록

### Testing Requirements
- 인증 테스트: `spring-security-test` (`@WithMockUser`, `SecurityMockMvcRequestPostProcessors`)
- 인가 테스트: `MockMvc` + CSRF 토큰 포함

### Common Patterns
- 커스텀 DSL: `AbstractHttpConfigurer<RestApiDsl<H>, H>` 상속
- `RestAuthenticationToken`: credentials(로그인 전) / authorities(로그인 후) 구분

## Dependencies

### Internal
- `domain/entity/` — Account, Role
- `domain/dto/` — AccountContext
- `admin/repository/ResourcesRepository` — URL-Role 매핑 조회

### External
- `spring-security-core`, `spring-security-web`, `spring-security-config`

<!-- MANUAL: -->
