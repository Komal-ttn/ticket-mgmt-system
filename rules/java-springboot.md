# Java Spring Boot Guidelines

- Use Java 21 language features only where they improve clarity.
- Prefer constructor injection.
- Keep controllers thin; put business rules in services.
- Do not expose JPA entities directly from REST endpoints; use DTOs.
- Use `@Transactional` for state-changing service operations.
- Never bypass domain invariants with setters from controllers.
- Prefer explicit domain methods such as `transitionTo(...)` when a state machine exists.
- Validate request DTOs with Jakarta Bean Validation.
- Use `ProblemDetail` for API errors.
- Keep repository methods intention-revealing.
- Avoid speculative abstractions and premature generic frameworks.
- Add tests for every business rule and regression.
