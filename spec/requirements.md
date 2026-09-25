# Requirements

## Functional
1. Create a ticket.
2. List tickets.
3. View ticket details.
4. Update title, description, priority, and assignee.
5. Add comments.
6. Search tickets by keyword.
7. Filter tickets by status.
8. Persist data.
9. Validate backend input.
10. Display meaningful UI errors.

## State machine
- OPEN → IN_PROGRESS
- IN_PROGRESS → RESOLVED
- RESOLVED → CLOSED
- OPEN → CANCELLED
- IN_PROGRESS → CANCELLED
- All other transitions are rejected.

## Acceptance criteria
- All CRUD-like flows work from the UI.
- Search and status filter work.
- Valid transitions work.
- Invalid transitions are rejected by backend.
- Data survives restart.
- Validation works.
- UI displays meaningful errors.
- State-machine integration tests pass.
- No secrets are committed.
