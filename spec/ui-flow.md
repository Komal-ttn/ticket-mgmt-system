# UI Flow

1. Ticket list opens by default.
2. User can search by keyword and filter by status.
3. Create form submits to POST /tickets.
4. Clicking a ticket opens details.
5. Details page allows editing fields and adding comments.
6. Status buttons display only valid next states, but the UI still handles a 409 response gracefully.
7. API errors are shown near the relevant action and as a readable message.
