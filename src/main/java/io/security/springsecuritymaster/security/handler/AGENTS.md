<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/handler

## Purpose
Form 인증과 REST 인증의 성공·실패·접근거부 이벤트를 처리하는 핸들러 클래스.

## Key Files

| File | Description |
|------|-------------|
| `FormAuthenticationSuccessHandler.java` | Form 로그인 성공 — 대시보드로 리다이렉트 |
| `FormAuthenticationFailureHandler.java` | Form 로그인 실패 — 로그인 페이지로 리다이렉트 + 오류 파라미터 |
| `FormAccessDeniedHandler.java` | Form 요청 접근 거부 — `/denied` 페이지로 리다이렉트 |
| `RestAuthenticationSuccessHandler.java` | REST 로그인 성공 — JSON 응답(사용자 정보 + 200) |
| `RestAuthenticationFailureHandler.java` | REST 로그인 실패 — JSON 응답(오류 메시지 + 401) |
| `RestAccessDeniedHandler.java` | REST 요청 접근 거부 — JSON 응답(403) |

## For AI Agents

### Working In This Directory
- Form 핸들러: `HttpServletResponse.sendRedirect()` 사용
- REST 핸들러: `ObjectMapper`로 JSON 직렬화하여 응답 body에 작성
- `SecurityConfig`에서 각 FilterChain에 핸들러 주입

<!-- MANUAL: -->
