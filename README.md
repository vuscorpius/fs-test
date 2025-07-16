# fs-test

## Overview
`fs-test` is a sample **Spring Boot + Kotlin** project integrated with **PostgreSQL** and **Flyway** and **webawesome**.  
It demonstrates how to manage products (CRUD) and exposes simple REST APIs that can be consumed by a lightweight HTML frontend (with HTMX and WebAwesome components).

Main features:
- Manage products with fields like `title`, `price`, `vendor`, `handle`, `body_html`, and `variants`.
- REST API endpoints for listing and creating products.
- PostgreSQL as the database, managed via `docker-compose`.
- Database migrations handled by Flyway.
- A simple UI (`index.html`) using HTMX to load and display products with pagination and styling.

---

## 🚀 How to run project?

### 1️Run PostgreSQL using Docker Compose
Make sure Docker is installed, then start the database:
```bash
docker-compose up -d
