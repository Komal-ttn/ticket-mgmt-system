# API Contract

`POST /api/tickets`
- body: `{title, description, priority, assignee?}`
- 201 on success
- 400 on validation failure

`GET /api/tickets?q=&status=`
- returns array of ticket summaries
- q searches title and description
- status filters exact status

`GET /api/tickets/{id}`
- 200 or 404

`PATCH /api/tickets/{id}`
- editable fields only: title, description, priority, assignee
- 200 or 400/404

`POST /api/tickets/{id}/comments`
- body: `{author, body}`
- 201 or 400/404

`POST /api/tickets/{id}/transitions`
- body: `{status}`
- 200 for allowed transition
- 409 for forbidden transition
- 400 for malformed/unknown status
- 404 if ticket does not exist
