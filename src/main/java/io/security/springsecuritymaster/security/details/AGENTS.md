<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/details

## Purpose
Form 로그인 요청에서 추가 데이터(secretKey 등)를 수집하는 `WebAuthenticationDetails` 확장.

## Key Files

| File | Description |
|------|-------------|
| `FormWebAuthenticationDetails.java` | `WebAuthenticationDetails` 확장 — 로그인 폼의 `secretKey` 파라미터 추출 |
| `FormWebAuthenticationDetailsSource.java` | `AuthenticationDetailsSource` 구현 — `FormWebAuthenticationDetails` 생성 팩토리 |

## For AI Agents

### Working In This Directory
- `SecurityConfig`에서 `.authenticationDetailsSource(authenticationDetailsSource)`로 등록
- `FormAuthenticationProvider`에서 `details.getSecretKey()`로 추가 검증 수행
- 추가 폼 파라미터가 필요하면 `FormWebAuthenticationDetails`에 필드 추가

<!-- MANUAL: -->
