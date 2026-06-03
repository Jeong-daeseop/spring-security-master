<!-- Parent: ../../../../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# springsecuritymaster (root package)

## Purpose
애플리케이션 루트 패키지 `io.security.springsecuritymaster`. Spring Boot 진입점과 도메인별 하위 패키지로 구성된다.

## Key Files

| File | Description |
|------|-------------|
| `SpringSecurityMasterApplication.java` | `@SpringBootApplication` + `@EnableJpaRepositories` 진입점 |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `admin/` | 관리자 기능 — 사용자/역할/리소스 CRUD (see `admin/AGENTS.md`) |
| `controller/` | 공통 컨트롤러 — 홈, 대시보드 등 (see `controller/AGENTS.md`) |
| `domain/` | 도메인 엔티티 및 DTO (see `domain/AGENTS.md`) |
| `security/` | Spring Security 전체 구성 (see `security/AGENTS.md`) |
| `users/` | 일반 사용자 기능 — 로그인, 회원가입, REST API (see `users/AGENTS.md`) |
| `util/` | 공통 유틸리티 |

## For AI Agents

### Package Convention
- 패키지 prefix: `io.security.springsecuritymaster`
- 인증/인가 관련 코드는 `security/` 하위에만 작성
- 도메인 엔티티는 `domain/entity/`, DTO는 `domain/dto/`

### Testing Requirements
- 테스트: `src/test/java/io/security/springsecuritymaster/` 동일 구조

<!-- MANUAL: -->
