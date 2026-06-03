<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/configs

## Purpose
Spring Security 설정 클래스. 두 개의 SecurityFilterChain과 공통 보안 빈을 정의한다.

## Key Files

| File | Description |
|------|-------------|
| `SecurityConfig.java` | `@EnableWebSecurity` — Form FilterChain(기본) + REST FilterChain(`@Order(1)`, `/api/**`) 정의 |
| `AuthConfig.java` | 공통 빈 — `PasswordEncoder`, `RoleHierarchyImpl`, `ModelMapper` 등 |

## For AI Agents

### Working In This Directory
- **Form FilterChain**: 모든 요청을 `CustomDynamicAuthorizationManager`로 위임, `/login` 폼 로그인
- **REST FilterChain**: `/api/**` 매칭, JSON 로그인(`/api/login`), `RestApiDsl`로 커스텀 필터 등록
- `AuthenticationManager`는 REST FilterChain 내부에서 `http.getSharedObject()`로 직접 빌드 (한 번만 호출)
- CSRF는 REST에서 주석 처리 상태 — 활성화/비활성화 시 클라이언트 영향 확인 필요

### Dependencies

#### Internal
- `security/dsl/RestApiDsl`
- `security/handler/` — 6개 핸들러
- `security/manager/CustomDynamicAuthorizationManager`
- `security/entrypoint/RestAuthenticationEntryPoint`

<!-- MANUAL: -->
