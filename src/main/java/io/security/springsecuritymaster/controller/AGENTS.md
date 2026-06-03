<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# controller

## Purpose
공통 페이지 컨트롤러. 홈, 대시보드, 역할별 페이지 라우팅을 담당한다.

## Key Files

| File | Description |
|------|-------------|
| `HomeController.java` | `/`, `/dashboard`, `/user`, `/manager`, `/admin`, `/db` 등 주요 뷰 엔드포인트 |

## For AI Agents

### Working In This Directory
- Form FilterChain에서 처리 (`CustomDynamicAuthorizationManager`로 인가 결정)
- 뷰 이름 반환 → `src/main/resources/templates/` 하위 Thymeleaf 템플릿 렌더링

<!-- MANUAL: -->
