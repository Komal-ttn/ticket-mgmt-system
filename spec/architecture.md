# Architecture

Frontend: React + Vite → REST API → Spring Boot service → Spring Data JPA → H2/PostgreSQL.

Layers:
- Controller: HTTP mapping and DTO validation.
- Service: business rules, transactions, state machine.
- Repository: persistence.
- Model: JPA entities and domain state.
- DTO: API input/output boundaries.

Status mutation is deliberately isolated behind `transitionTo` and the transition endpoint.
