<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/provider

## Purpose
커스텀 `AuthenticationProvider` 구현체. Form과 REST 인증 각각의 비즈니스 로직(자격증명 검증, UserDetails 로드)을 담당한다.

## Key Files

| File | Description |
|------|-------------|
| `FormAuthenticationProvider.java` | Form 로그인 인증 — `UsernamePasswordAuthenticationToken` 처리, `PasswordEncoder`로 비밀번호 검증, `FormWebAuthenticationDetails`에서 secretKey 추가 검증 |
| `RestAuthenticationProvider.java` | REST API 로그인 인증 — `RestAuthenticationToken` 처리, 동일한 `FormUserDetailsService` 사용 |

## For AI Agents

### Working In This Directory
- `supports()` 메서드로 각 Provider가 처리할 `Authentication` 타입 지정
  - Form: `UsernamePasswordAuthenticationToken`
  - REST: `RestAuthenticationToken`
- 인증 성공 시 `authorities`를 포함한 새 토큰 반환
- `BadCredentialsException` / `UsernameNotFoundException` 으로 인증 실패 처리

### Dependencies

#### Internal
- `security/service/FormUserDetailsService`
- `domain/dto/AccountContext`

<!-- MANUAL: -->
