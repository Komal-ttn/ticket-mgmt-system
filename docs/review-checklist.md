# Final Review Checklist

- [x] Requirement traceability exists.
- [x] State machine exists as a single explicit domain rule.
- [x] Status cannot be changed through PATCH.
- [x] Invalid transitions return 409.
- [x] Backend validation exists.
- [x] UI handles API errors.
- [x] H2 file persistence is enabled by default.
- [x] PostgreSQL uses environment-provided credentials.
- [x] No committed production credentials or API keys.
- [x] State-machine tests cover allowed and representative invalid transitions.
- [x] Prompt history is committed.
- [x] AI review mistakes are documented.
