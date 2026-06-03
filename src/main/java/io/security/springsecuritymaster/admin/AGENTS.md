<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# admin

## Purpose
관리자 기능 패키지. 사용자(Account), 역할(Role), 리소스(URL-Role 매핑), 역할 계층(RoleHierarchy)에 대한 CRUD를 제공한다. 리소스 변경 시 `CustomDynamicAuthorizationManager.reload()`를 호출하여 인가 규칙을 실시간 반영한다.

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `controller/` | 관리자 MVC 컨트롤러 (see `controller/AGENTS.md`) |
| `repository/` | Spring Data JPA 리포지토리 인터페이스 (see `repository/AGENTS.md`) |
| `service/` | 서비스 인터페이스 및 구현체 (see `service/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- 새 관리 기능 추가 시 controller → service(interface + impl) → repository 순서로 작성
- 리소스(URL-Role) 변경은 반드시 `CustomDynamicAuthorizationManager.reload()` 호출 필요

### Dependencies

#### Internal
- `domain/entity/` — Account, Role, Resources, RoleHierarchy 엔티티
- `domain/dto/` — AccountDto, RoleDto, ResourcesDto
- `security/manager/CustomDynamicAuthorizationManager` — 인가 규칙 리로드

<!-- MANUAL: -->
