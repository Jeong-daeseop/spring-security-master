<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# domain/dto

## Purpose
데이터 전송 객체(DTO) 및 Spring Security UserDetails 컨텍스트 클래스.

## Key Files

| File | Description |
|------|-------------|
| `AccountDto.java` | Account 엔티티의 전송 객체 — 컨트롤러/서비스 간 데이터 전달 |
| `RoleDto.java` | Role 엔티티의 전송 객체 |
| `ResourcesDto.java` | Resources 엔티티의 전송 객체 (URL, httpMethod, orderNum, resourceType) |
| `AccountContext.java` | `UserDetails` 구현체 — `Account` + `GrantedAuthority` 목록 보유, Spring Security 인증 컨텍스트에 저장 |

## For AI Agents

### Working In This Directory
- `AccountContext`는 `FormUserDetailsService.loadUserByUsername()`에서 생성
- Entity ↔ DTO 변환은 서비스 레이어에서 `ModelMapper` 사용
- DTO에 비즈니스 로직 추가 금지

### Dependencies

#### Internal
- `domain/entity/` — 변환 대상 엔티티

<!-- MANUAL: -->
