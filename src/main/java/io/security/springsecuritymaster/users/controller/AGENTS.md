<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# users/controller

## Purpose
일반 사용자 화면 컨트롤러와 REST API 엔드포인트.

## Key Files

| File | Description |
|------|-------------|
| `LoginController.java` | `/login`, `/signup`, `/denied` 페이지 요청 처리 |
| `UserController.java` | 회원가입 폼 제출 처리 — `UserService.createUser()` 호출 |
| `RestApiController.java` | `/api/user`, `/api/manager`, `/api/admin` REST 엔드포인트 — 권한별 응답 반환 |

## For AI Agents

### Working In This Directory
- `LoginController`: GET 요청만 처리 (로그인 POST는 Spring Security 필터가 처리)
- `RestApiController`: REST FilterChain(`@Order(1)`)에서 인가 처리 후 도달
- CSRF: Form 요청에는 CSRF 토큰 필요, REST `/api/**`는 설정 확인 필요

### Dependencies

#### Internal
- `users/service/UserService`
- `domain/dto/AccountDto`

<!-- MANUAL: -->
