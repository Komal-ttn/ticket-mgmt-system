# Review Code

Review the changed code against `spec/`, `rules/java-springboot.md`, `rules/testing.md`, and `rules/api-standards.md`.

Check:
- domain invariants
- state transitions
- validation
- API semantics
- transaction boundaries
- null/empty handling
- security/secrets
- tests and regression coverage
- unnecessary complexity

Return findings ordered by severity, with file and line references where possible.
Do not rewrite code until findings have been reviewed.
