# AI Review Findings

The following are meaningful mistakes found in the initial AI-generated design/implementation approach. They are recorded deliberately to demonstrate engineering review rather than blind acceptance.

## 1. State could be mutated directly

**Problem:** An early implementation pattern exposed a generic `setStatus(...)` path. That makes it too easy for a controller or future caller to set `CLOSED` from `OPEN`, bypassing the transition rules.

**Why it matters:** The state machine is a backend invariant. UI restrictions are not sufficient.

**Fix:** Status changes now go through `Ticket.transitionTo(...)`, which checks the explicit transition table. The REST API exposes a dedicated `/transitions` operation rather than accepting `status` in the generic ticket patch.

## 2. Field update and workflow transition were conflated

**Problem:** A generic PATCH model initially included status alongside editable fields.

**Why it matters:** It creates an accidental second state-transition API and makes authorization/auditing harder later.

**Fix:** PATCH updates title, description, priority, and assignee only. Status changes use the dedicated transition endpoint.

## 3. Tests that only asserted HTTP success were insufficient

**Problem:** A generated-test approach checked only `200 OK` for transition requests.

**Why it matters:** An endpoint can return 200 while persisting the wrong state. State-machine tests must verify the resulting state and rejected transitions.

**Fix:** Integration tests assert both the HTTP status and the persisted ticket status for allowed and forbidden transitions.

## 4. UI-only validation was insufficient

**Problem:** Relying on required form fields in React would not protect direct API callers.

**Fix:** Jakarta Bean Validation is enforced at the REST boundary, with Problem Details returned and rendered by the UI.
