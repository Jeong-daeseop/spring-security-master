<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# admin/repository

## Purpose
관리자 기능용 Spring Data JPA 리포지토리 인터페이스.

## Key Files

| File | Description |
|------|-------------|
| `UserManagementRepository.java` | Account 엔티티 CRUD |
| `RoleRepository.java` | Role 엔티티 CRUD + `findByRoleName()` |
| `RoleHierarchyRepository.java` | RoleHierarchy 엔티티 CRUD |
| `ResourcesRepository.java` | Resources(URL-Role 매핑) 엔티티 CRUD — `CustomDynamicAuthorizationManager`가 참조 |

## For AI Agents

### Working In This Directory
- Spring Data JPA `JpaRepository<Entity, ID>` 상속
- `ResourcesRepository`는 `CustomDynamicAuthorizationManager`에서 직접 주입받아 URL-Role 목록을 조회함

### Dependencies

#### Internal
- `domain/entity/` — Account, Role, RoleHierarchy, Resources

<!-- MANUAL: -->
