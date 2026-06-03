<!-- Parent: ../../../../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# test/springsecuritymaster

## Purpose
JUnit 5 기반 테스트 클래스. 현재 애플리케이션 컨텍스트 로드 테스트만 포함되어 있으며, 향후 인증·인가 단위/통합 테스트 추가 예정.

## Key Files

| File | Description |
|------|-------------|
| `SpringSecurityMasterApplicationTests.java` | `@SpringBootTest` 컨텍스트 로드 smoke test |

## For AI Agents

### Testing Requirements
- 실행: `./gradlew test` (MySQL 연결 필요)
- 인증 테스트: `@WithMockUser`, `SecurityMockMvcRequestPostProcessors.csrf()`
- REST 인증 테스트: `MockMvc` + JSON body + `RestAuthenticationToken`

### Common Patterns
```java
// Form 인증 테스트 예시
@WithMockUser(username="admin", roles={"ADMIN"})
mockMvc.perform(get("/admin")).andExpect(status().isOk());

// CSRF 포함 POST 테스트
mockMvc.perform(post("/login").with(csrf()).param("username","admin").param("password","pass"));
```

### Dependencies

#### External
- `spring-boot-starter-test`
- `spring-security-test`

<!-- MANUAL: -->
