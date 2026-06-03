<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-06-03 | Updated: 2026-06-03 -->

# admin/service

## Purpose
관리자 기능 서비스 레이어. 인터페이스와 구현체(`impl/`)를 분리한다.

## Key Files

| File | Description |
|------|-------------|
| `UserManagementService.java` | 사용자 관리 서비스 인터페이스 |
| `RoleService.java` | 역할 관리 서비스 인터페이스 |
| `RoleHierarchyService.java` | 역할 계층 관리 서비스 인터페이스 |
| `ResourcesService.java` | URL-Role 매핑 관리 서비스 인터페이스 |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `impl/` | 서비스 구현체 — ResourcesServiceImpl, RoleServiceImpl 등 |

## For AI Agents

### Working In This Directory
- 비즈니스 로직은 `impl/` 구현체에만 작성
- ModelMapper로 Entity ↔ DTO 변환
- 역할 계층 변경(`RoleHierarchyService`) 시 `RoleHierarchyImpl` 빈 갱신 필요

### Dependencies

#### Internal
- `admin/repository/` — 데이터 접근
- `domain/entity/` + `domain/dto/` — 데이터 모델

<!-- MANUAL: -->
