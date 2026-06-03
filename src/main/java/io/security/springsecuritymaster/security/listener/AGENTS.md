<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/listener

## Purpose
애플리케이션 시작 시 초기 데이터를 설정하는 이벤트 리스너.

## Key Files

| File | Description |
|------|-------------|
| `SetupDataLoader.java` | `ApplicationListener<ContextRefreshedEvent>` 구현 — 최초 1회 실행하여 `ROLE_ADMIN` 역할과 `admin` 계정 생성 |

## For AI Agents

### Working In This Directory
- `alreadySetup` 플래그로 중복 실행 방지 (앱 재시작 시에도 DB에 이미 있으면 건너뜀)
- 초기 데이터 추가 필요 시 `setupData()` 메서드 확장
- 초기 비밀번호는 `PasswordEncoder`로 인코딩됨 (평문 저장 금지)

### Dependencies

#### Internal
- `users/repository/UserRepository`
- `admin/repository/RoleRepository`
- `domain/entity/Account`, `Role`

<!-- MANUAL: -->
