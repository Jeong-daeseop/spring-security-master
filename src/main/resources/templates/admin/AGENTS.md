<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# templates/admin

## Purpose
관리자 전용 UI 템플릿. 사용자 관리, 역할(Role) 관리, 리소스(URL-Role 매핑) 관리 CRUD 화면을 제공한다.

## Key Files

| File | Description |
|------|-------------|
| `home.html` | 관리자 홈 |
| `users.html` | 사용자 목록 |
| `userdetails.html` | 사용자 상세/수정 |
| `roles.html` | 역할 목록 |
| `rolesdetails.html` | 역할 상세/수정 |
| `resources.html` | 리소스(URL-Role 매핑) 목록 |
| `resourcesdetails.html` | 리소스 상세/수정 |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `layout/` | 관리자 전용 header, sidebar 프래그먼트 |
| `content/` | 각 관리 화면의 본문 콘텐츠 프래그먼트 |

## For AI Agents

### Working In This Directory
- 관리자 레이아웃은 `layout/header.html`, `layout/sidebar.html` 사용
- 리소스 수정 후 `CustomDynamicAuthorizationManager.reload()` 호출 → 인가 규칙 실시간 반영
- CSRF 토큰: form 태그에 `th:action` 사용 시 자동 포함

### Dependencies
- `AdminController`, `ResourcesController`, `RoleController`, `UserManagementController` (admin.controller 패키지)

<!-- MANUAL: -->
