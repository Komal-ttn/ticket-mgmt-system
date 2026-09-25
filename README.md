# Support Ticket Management System

A spec-driven Support Ticket Management System built with Java 21, Spring Boot, H2/PostgreSQL, REST, and React/Vite.

## Engineering workflow

Requirement → Specification → Plan / Tasks → Implementation → Testing → Review → Fix

The repository intentionally includes reusable AI steering artifacts under `rules/`, `skills/`, and `commands/`, plus specifications under `spec/` and prompt history under `.specstory/history/` and `docs/prompt-history.md`.

## Run backend

```bash
cd backend
./mvnw spring-boot:run
```

For PostgreSQL:

```bash
docker compose up -d postgres
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```

## Run frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend expects the API at `http://localhost:8080/api`.

## Test backend

```bash
cd backend
./mvnw test
```

## Key design decisions

- Status transitions are enforced in the backend service, not only by the UI.
- Updating editable fields is separate from changing status.
- Search and status filtering are handled by the backend.
- H2 is the default development/test database; PostgreSQL is supported through a profile.
- Validation errors use a consistent RFC 7807-style Problem Details response.
- No secrets are stored in source control.

## AI engineering evidence

See:
- `spec/` for specifications
- `docs/plan.md` for implementation tasks
- `docs/ai-review.md` for meaningful AI mistakes caught during review
- `docs/prompt-history.md` and `.specstory/history/` for prompts
- `rules/`, `skills/`, and `commands/` for reusable AI instructions
# ticket-mgmt-system
