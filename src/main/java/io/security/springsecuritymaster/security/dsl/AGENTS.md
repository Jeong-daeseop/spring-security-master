<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# security/dsl

## Purpose
REST API 인증을 위한 커스텀 Spring Security DSL.

## Key Files

| File | Description |
|------|-------------|
| `RestApiDsl.java` | `AbstractHttpConfigurer<RestApiDsl<H>, H>` 확장 — `RestAuthenticationFilter`를 생성하고 필터 체인에 등록하는 선언형 설정 API |

## For AI Agents

### Working In This Directory
- `SecurityConfig`에서 `.with(new RestApiDsl<>(), dsl -> dsl.loginPage(...).loginProcessingUrl(...))` 형태로 사용
- `init()` / `configure()` 오버라이드로 필터 생성 및 배치
- DSL 메서드 체이닝으로 핸들러, URL 설정 가능

### Dependencies

#### Internal
- `security/filters/RestAuthenticationFilter`
- `security/handler/RestAuthenticationSuccessHandler`, `RestAuthenticationFailureHandler`

<!-- MANUAL: -->
