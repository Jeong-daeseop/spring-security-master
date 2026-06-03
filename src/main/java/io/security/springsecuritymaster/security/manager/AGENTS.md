<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/manager

## Purpose
DB 기반 동적 인가를 담당하는 커스텀 `AuthorizationManager`. 앱 실행 중에도 URL-Role 매핑을 실시간으로 갱신할 수 있다.

## Key Files

| File | Description |
|------|-------------|
| `CustomDynamicAuthorizationManager.java` | `AuthorizationManager<RequestAuthorizationContext>` 구현체 — `@PostConstruct`로 초기 매핑 로드, `reload()`로 실시간 갱신 |

## For AI Agents

### Working In This Directory
- `check()`: URL 패턴 순서대로 매칭 → 매칭 시 해당 `AuthorizationManager`에 위임, 미매칭 시 `ACCESS`(허용)
- `customAuthorizationManager(role)`: role이 `ROLE`로 시작하면 `AuthorityAuthorizationManager`, 그 외 SpEL 표현식은 `WebExpressionAuthorizationManager`
- 역할 계층(`RoleHierarchyImpl`) 적용: 상위 역할이 하위 역할 권한 자동 포함
- `reload()` 메서드는 `synchronized` — 리소스 변경 API 호출 후 반드시 호출

### Common Patterns
- SpEL 표현식 자동 수정: `hasRole(ADMIN)` → `hasRole('ADMIN')` (따옴표 자동 추가)
- `MvcRequestMatcher` 사용 (AntPathRequestMatcher 아님) — Spring MVC 경로 변수 지원

### Dependencies

#### Internal
- `admin/repository/ResourcesRepository` — URL-Role 매핑 조회
- `security/mapper/PersistentUrlRoleMapper` — DB 매핑 변환
- `security/service/DynamicAuthorizationService`

<!-- MANUAL: -->
