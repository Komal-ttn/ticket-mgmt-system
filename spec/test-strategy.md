# Test Strategy

- Unit tests: transition rules and service behavior.
- Integration tests: repository + service + HTTP boundary for state changes.
- Validation tests: missing/blank/oversized fields.
- Search/filter tests: keyword, status, combined criteria.
- Persistence test: save, reload, verify fields.
- UI: component tests can be added with Vitest; current repository includes a lightweight manual test matrix and API contract.
