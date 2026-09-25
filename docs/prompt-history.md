# Prompt History

All prompts used for this exercise are recorded here and mirrored under `.specstory/history/`.

## 001 — Requirements analysis

Analyze the Support Ticket Management System requirements. Extract functional requirements, non-functional constraints, domain entities, API operations, validation rules, and the exact ticket state machine. Do not implement code. Identify ambiguities and propose testable acceptance criteria.

## 002 — Architecture

Using the approved requirements, design a Java 21 Spring Boot REST backend with H2/PostgreSQL and a React frontend. Keep controllers thin, enforce business rules in services/domain methods, use DTOs, and isolate status transitions behind a dedicated operation. Produce architecture and data-model decisions only.

## 003 — Implementation

Implement the approved specification. Do not add features outside scope. Enforce the state machine on the backend. Use Jakarta validation and Problem Details for API errors. Add tests for every valid and invalid transition and for validation/error behavior.

## 004 — Review

Review the implementation against every file in `spec/` and the reusable rules. Look specifically for AI-generated code that bypasses domain invariants, conflates field updates with state transitions, weakens validation, or creates API/UI behavior inconsistent with the specification. Report findings before proposing fixes.

## 005 — Test generation

Generate tests directly from the requirements and state-machine transition table. Include all allowed transitions, representative forbidden transitions, not-found behavior, validation failures, search/filter behavior, and persistence.

## 006 — Fix

Apply only the review findings that are supported by the specification. Re-run the relevant tests and update documentation for any behavioral change.
