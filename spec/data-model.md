# Data Model

## Ticket
- id: Long
- title: String, required, 1–200 chars
- description: String, required, 1–5000 chars
- priority: LOW | MEDIUM | HIGH | URGENT
- assignee: optional String, max 120 chars
- status: OPEN | IN_PROGRESS | RESOLVED | CLOSED | CANCELLED
- createdAt: Instant
- updatedAt: Instant

## Comment
- id: Long
- ticketId: foreign key
- author: required, max 120 chars
- body: required, 1–2000 chars
- createdAt: Instant
