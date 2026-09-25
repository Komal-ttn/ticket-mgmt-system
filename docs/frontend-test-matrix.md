# Frontend Test Matrix

1. Create ticket with valid data → ticket appears and starts OPEN.
2. Submit blank title/description → backend error is shown.
3. Search by title and description → matching tickets remain.
4. Select status filter → only selected status appears.
5. Open ticket → title, description, priority and assignee are editable.
6. Save field changes → refresh and verify persisted values.
7. Add comment → comment appears after save.
8. Click valid transition → status changes.
9. Attempt an invalid transition using DevTools/fetch → UI shows the backend 409 message.
10. Restart backend → existing H2 file data remains.
