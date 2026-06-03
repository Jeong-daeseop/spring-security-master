<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/token

## Purpose
REST API 인증 전용 커스텀 Authentication 토큰.

## Key Files

| File | Description |
|------|-------------|
| `RestAuthenticationToken.java` | `AbstractAuthenticationToken` 확장 — 인증 전(credentials만)/인증 후(principal+authorities) 두 가지 생성자 제공 |

## For AI Agents

### Working In This Directory
- `RestAuthenticationFilter`에서 인증 전 토큰 생성: `new RestAuthenticationToken(username, password)`
- `RestAuthenticationProvider`에서 인증 후 토큰 반환: `new RestAuthenticationToken(accountContext, authorities)`
- `RestAuthenticationProvider.supports()`: `RestAuthenticationToken.class` 지정

<!-- MANUAL: -->
