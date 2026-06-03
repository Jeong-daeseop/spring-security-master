<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# resources

## Purpose
애플리케이션 설정 파일, Thymeleaf 템플릿, 정적 리소스를 포함한다.

## Key Files

| File | Description |
|------|-------------|
| `application.yml` | DB 연결(MySQL securitydb), JPA/Hibernate 설정, Thymeleaf 캐시 비활성화 |
| `insert.sql` | 초기 데이터 삽입 SQL (개발/테스트용) |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `templates/` | Thymeleaf HTML 템플릿 (see `templates/AGENTS.md`) |
| `static/` | 정적 파일 — 이미지 등 |

## For AI Agents

### Working In This Directory
- DB: MySQL `securitydb` (localhost:3306), `ddl-auto: update`
- Thymeleaf 캐시 비활성화(`cache: false`) — 개발 중 템플릿 즉시 반영
- 새 설정 항목은 `application.yml`에 추가

### Common Patterns
- Thymeleaf 보안 표현식: `sec:authorize`, `sec:authentication` (thymeleaf-extras-springsecurity6)

<!-- MANUAL: -->
