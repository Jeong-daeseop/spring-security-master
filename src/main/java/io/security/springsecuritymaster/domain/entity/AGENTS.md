<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# domain/entity

## Purpose
JPA 엔티티 클래스. 인증·인가에 필요한 핵심 도메인 모델을 정의한다.

## Key Files

| File | Description |
|------|-------------|
| `Account.java` | 사용자 계정 — id, username, password, age, `Set<Role> userRoles` (ManyToMany, `account_roles` 조인 테이블) |
| `Role.java` | 역할 — roleName, roleDesc; Account·Resources와 다대다 관계 |
| `Resources.java` | URL-Role 매핑 — resourceName(URL), httpMethod, orderNum, resourceType, `Set<Role> roleSet` (`role_resources` 조인 테이블) |
| `RoleHierarchy.java` | 역할 계층 — 부모-자식 관계로 상위 역할이 하위 역할 권한을 포함 |

## For AI Agents

### Working In This Directory
- 모든 엔티티는 `jakarta.persistence.*` 사용 (javax.persistence 금지)
- Lombok `@Builder`, `@Getter`, `@Setter` 활용
- `Account ↔ Role`: `account_roles` 조인 테이블 (ManyToMany, LAZY, CascadeType.MERGE)
- `Resources ↔ Role`: `role_resources` 조인 테이블 (ManyToMany, LAZY)
- `Resources`는 `AuditingEntityListener` 적용 (변경 감사)

### Common Patterns
- `ddl-auto: update` — 스키마 변경 시 자동 반영 (운영 환경 주의)
- 엔티티 변경 시 관련 DTO(`domain/dto/`)도 함께 수정

<!-- MANUAL: -->
