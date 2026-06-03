<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# src

## Purpose
애플리케이션 전체 소스 루트. 프로덕션 코드(`main`)와 테스트 코드(`test`)를 분리한다.

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `main/` | 프로덕션 소스 — Java 코드, 리소스, 템플릿 (see `main/AGENTS.md`) |
| `test/` | 테스트 소스 — JUnit 5 테스트 클래스 (see `test/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- 새 기능은 `main/java` 아래 해당 패키지에 추가
- 테스트는 동일 패키지 구조로 `test/java` 아래 작성

<!-- MANUAL: -->