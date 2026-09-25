# State Machine

| Current | Allowed next states |
|---|---|
| OPEN | IN_PROGRESS, CANCELLED |
| IN_PROGRESS | RESOLVED, CANCELLED |
| RESOLVED | CLOSED |
| CLOSED | none |
| CANCELLED | none |

The backend must reject every transition not in this table. The UI may hide invalid actions, but backend enforcement is authoritative.
