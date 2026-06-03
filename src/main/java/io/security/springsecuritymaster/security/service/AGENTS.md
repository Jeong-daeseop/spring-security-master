<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/service

## Purpose
Spring Security 인증·인가에 필요한 서비스 클래스.

## Key Files

| File | Description |
|------|-------------|
| `FormUserDetailsService.java` | `UserDetailsService` 구현 — `UserRepository`로 Account 조회 후 `AccountContext` 반환; Form/REST Provider 공용 |
| `DynamicAuthorizationService.java` | `UrlRoleMapper`를 통해 URL-Role 매핑 Map을 제공; `CustomDynamicAuthorizationManager`가 사용 |

## For AI Agents

### Working In This Directory
- `FormUserDetailsService`: `loadUserByUsername(username)` — Account 없으면 `UsernameNotFoundException`
- `DynamicAuthorizationService`: `getUrlRoleMappings()` → `Map<String, String>` (URL → role/expression)
- `DynamicAuthorizationService`는 `UrlRoleMapper` 구현체(`PersistentUrlRoleMapper` or `MapBasedUrlRoleMapper`)를 주입받아 동작

### Dependencies

#### Internal
- `users/repository/UserRepository`
- `security/mapper/` — UrlRoleMapper 구현체
- `domain/dto/AccountContext`
- `domain/entity/Account`

<!-- MANUAL: -->
