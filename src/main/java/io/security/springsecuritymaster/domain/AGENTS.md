<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# domain

## Purpose
애플리케이션 핵심 도메인 모델. JPA 엔티티(`entity/`)와 전송 객체(`dto/`)를 분리하여 관리한다.

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `entity/` | JPA 엔티티 — Account, Role, Resources, RoleHierarchy (see `entity/AGENTS.md`) |
| `dto/` | DTO / Context 클래스 — AccountDto, RoleDto, ResourcesDto, AccountContext (see `dto/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- 엔티티는 `@Entity`, DTO는 POJO/Lombok `@Data`
- Entity ↔ DTO 변환은 `ModelMapper` (서비스 레이어에서 처리)
- 엔티티에 비즈니스 로직 추가 금지 — 순수 데이터 모델 유지

<!-- MANUAL: -->
