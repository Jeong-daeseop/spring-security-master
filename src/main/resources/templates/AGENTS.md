<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# templates

## Purpose
Thymeleaf HTML 템플릿 루트. Form 인증 기반 일반 페이지, REST 대시보드, 관리자 페이지로 구성된다.

## Key Files

| File | Description |
|------|-------------|
| `admin.html` | 관리자 페이지 진입점 |
| `dashboard.html` | 인증 후 기본 대시보드 |
| `db.html` | DB 관련 페이지 |
| `manager.html` | 매니저 권한 페이지 |
| `user.html` | 일반 사용자 페이지 |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `admin/` | 관리자 UI — 사용자/역할/리소스 CRUD (see `admin/AGENTS.md`) |
| `content/` | 각 페이지의 본문 콘텐츠 프래그먼트 |
| `layout/` | 공통 레이아웃 프래그먼트 (header, footer, sidebar) |
| `login/` | 로그인, 회원가입, 접근 거부 페이지 |
| `rest/` | REST API 클라이언트용 대시보드 및 로그인 페이지 |

## For AI Agents

### Working In This Directory
- `th:replace` / `th:insert`로 레이아웃 프래그먼트 조합
- `sec:authorize="hasRole('ROLE_ADMIN')"` 등으로 권한별 UI 제어
- XSS 방지: `th:text` 사용 (인라인 `th:utext` 최소화)

### Common Patterns
- 공통 헤더/푸터: `layout/header.html`, `layout/footer.html`
- 페이지 본문: `content/` 하위 프래그먼트를 부모 템플릿에 삽입

<!-- MANUAL: -->
