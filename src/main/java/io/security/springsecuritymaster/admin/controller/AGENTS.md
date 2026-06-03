<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# admin/controller

## Purpose
관리자 기능 MVC 컨트롤러. 사용자·역할·리소스 관리 화면의 요청/응답을 처리한다.

## Key Files

| File | Description |
|------|-------------|
| `AdminController.java` | 관리자 홈 및 공통 진입 엔드포인트 |
| `UserManagementController.java` | 사용자(Account) 목록·상세·수정·삭제 |
| `RoleController.java` | 역할(Role) CRUD |
| `ResourcesController.java` | URL-Role 매핑(Resources) CRUD — 변경 시 인가 규칙 리로드 |

## For AI Agents

### Working In This Directory
- URL 패턴: `/admin/**` (SecurityConfig의 Form FilterChain에서 처리)
- 리소스 변경 후 `CustomDynamicAuthorizationManager.reload()` 호출 필수
- Thymeleaf 모델 바인딩: `Model.addAttribute()` + `th:object` 폼 바인딩

### Dependencies

#### Internal
- `admin/service/` — UserManagementService, RoleService, ResourcesService
- `security/manager/CustomDynamicAuthorizationManager` — reload()

<!-- MANUAL: -->
