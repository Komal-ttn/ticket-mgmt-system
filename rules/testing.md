# Testing Guidelines

- Unit test business rules and validation boundaries.
- Integration test every allowed and forbidden state transition.
- Test persistence behavior using H2 for fast tests.
- Test REST validation/error contracts.
- Include happy path, boundary, malformed input, not-found, and conflict scenarios.
- Do not weaken assertions merely to make generated tests pass.
- A test that only checks HTTP 200 without verifying state is insufficient for state-machine behavior.
