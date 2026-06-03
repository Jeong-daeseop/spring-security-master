<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/filters

## Purpose
커스텀 Security 필터. REST API 로그인 요청을 처리한다.

## Key Files

| File | Description |
|------|-------------|
| `RestAuthenticationFilter.java` | `AbstractAuthenticationProcessingFilter` 확장 — `/api/login` POST 요청의 JSON body에서 username/password 추출 후 `RestAuthenticationToken` 생성 및 인증 처리 |

## For AI Agents

### Working In This Directory
- `attemptAuthentication()`: `ObjectMapper`로 JSON 파싱 → `RestAuthenticationToken` 생성 → `AuthenticationManager.authenticate()`
- `successfulAuthentication()` / `unsuccessfulAuthentication()`: 핸들러에 위임
- `RestApiDsl`을 통해 `UsernamePasswordAuthenticationFilter` 앞에 배치

### Dependencies

#### Internal
- `security/token/RestAuthenticationToken`
- `security/handler/RestAuthenticationSuccessHandler`, `RestAuthenticationFailureHandler`

<!-- MANUAL: -->
