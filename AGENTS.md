<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# spring-security-master

## Purpose
Spring Boot 3.2 기반 Spring Security 학습/실습 프로젝트. Form 인증과 REST API 인증을 병렬 구성하며, DB 기반 동적 인가(URL-Role 매핑)를 구현한다. 역할 계층(Role Hierarchy), 커스텀 AuthorizationManager, 커스텀 DSL 등 Spring Security 심화 주제를 다룬다.

## Key Files

| File | Description |
|------|-------------|
| `build.gradle` | Gradle 빌드 설정 — Spring Boot 3.2.2, Security, JPA, Thymeleaf, MySQL |
| `settings.gradle` | 프로젝트 이름 설정 |
| `gradlew` / `gradlew.bat` | Gradle Wrapper 실행 스크립트 |
| `README.md` | 프로젝트 개요 |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `src/` | 애플리케이션 소스 코드 및 테스트 (see `src/AGENTS.md`) |
| `docs/` | 아키텍처/개념 문서 (see `docs/AGENTS.md`) |
| `gradle/` | Gradle Wrapper 바이너리 및 설정 |

## For AI Agents

### 기술 스택
- Java 17, Spring Boot 3.2.2
- Spring Security 6.x (jakarta.* 네임스페이스)
- Spring Data JPA + Hibernate + MySQL
- Thymeleaf 3.x + thymeleaf-extras-springsecurity6
- Lombok, ModelMapper
- JUnit 5 + spring-security-test

### Working In This Directory
- 빌드: `./gradlew build`
- 실행: `./gradlew bootRun` (MySQL `securitydb` DB 필요)
- 테스트: `./gradlew test`

### Architecture Overview
```
Form 인증 흐름:  /login → FormAuthenticationProvider → SecurityFilterChain
REST 인증 흐름:  /api/login → RestAuthenticationFilter → RestAuthenticationProvider → @Order(1) FilterChain
동적 인가:       CustomDynamicAuthorizationManager → DB(Resources 테이블) → URL-Role 매핑
```

### Common Patterns
- 두 개의 SecurityFilterChain: REST(`@Order(1)`, `/api/**`) + Form(기본)
- 커스텀 DSL `RestApiDsl`로 REST 필터 체인 구성
- `SetupDataLoader`가 앱 시작 시 admin 계정/ROLE_ADMIN 자동 생성
- `CustomDynamicAuthorizationManager.reload()`로 인가 규칙 실시간 반영

## Dependencies

### External
- `org.springframework.boot:spring-boot-starter-security` — Spring Security 코어
- `org.springframework.boot:spring-boot-starter-data-jpa` — JPA/Hibernate
- `com.mysql:mysql-connector-j` — MySQL 드라이버
- `org.thymeleaf.extras:thymeleaf-extras-springsecurity6` — Thymeleaf Security 방언
- `org.modelmapper:modelmapper` — Entity↔DTO 변환
- `org.projectlombok:lombok` — 보일러플레이트 제거

<!-- MANUAL: -->