<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# users

## Purpose
일반 사용자 기능 패키지. 로그인 화면, 회원가입, REST API 엔드포인트, 사용자 데이터 접근을 담당한다.

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `controller/` | MVC 컨트롤러 및 REST API 컨트롤러 (see `controller/AGENTS.md`) |
| `repository/` | UserRepository — Account 엔티티 접근 |
| `service/` | UserService — 회원가입 비즈니스 로직 |

## For AI Agents

### Working In This Directory
- `UserRepository.findByUsername()` — `FormUserDetailsService`와 `SetupDataLoader`에서 공용 사용
- 회원가입: `UserService` → `PasswordEncoder`로 비밀번호 인코딩 후 저장

### Dependencies

#### Internal
- `domain/entity/Account`
- `security/service/FormUserDetailsService` — UserRepository 사용

<!-- MANUAL: -->
