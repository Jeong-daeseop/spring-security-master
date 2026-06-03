<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/mapper

## Purpose
URL-Role 매핑 전략을 추상화하는 인터페이스와 구현체.

## Key Files

| File | Description |
|------|-------------|
| `UrlRoleMapper.java` | 인터페이스 — `getUrlRoleMappings(): Map<String, String>` |
| `PersistentUrlRoleMapper.java` | DB 기반 구현 — `ResourcesRepository`에서 Resources 엔티티 조회 후 `{URL → role}` Map 반환 |
| `MapBasedUrlRoleMapper.java` | 인메모리 기반 구현 — 하드코딩된 Map (테스트/개발용) |

## For AI Agents

### Working In This Directory
- `PersistentUrlRoleMapper`가 운영 기본값 (`CustomDynamicAuthorizationManager`에서 사용)
- `MapBasedUrlRoleMapper`는 DB 없이 빠른 테스트 시 `DynamicAuthorizationService`에 교체 주입
- 새로운 매핑 전략 추가 시 `UrlRoleMapper` 인터페이스 구현

### Dependencies

#### Internal
- `admin/repository/ResourcesRepository`
- `domain/entity/Resources`, `Role`

<!-- MANUAL: -->
