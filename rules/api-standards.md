# API Standards

Base path: `/api`

- REST resource names are plural nouns.
- Use `POST /tickets` to create a ticket.
- Use `GET /tickets` for listing/search/filtering.
- Use `GET /tickets/{id}` for details.
- Use `PATCH /tickets/{id}` for editable fields.
- Use `POST /tickets/{id}/comments` for comments.
- Use `POST /tickets/{id}/transitions` for status changes.
- Invalid transitions return HTTP 409.
- Validation failures return HTTP 400 with Problem Details.
- Missing tickets return HTTP 404.
- JSON uses camelCase.
- Do not leak database implementation details in API responses.
