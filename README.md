# MediConnect – Doctor Appointment Platform (Microservices + Nginx + JWT)

## Overview
MediConnect is a microservice-based doctor appointment platform built using Spring Boot and secured with JWT authentication.  
The architecture uses **Nginx** as a reverse proxy and central gateway to route requests to individual microservices.

## Architecture

Client (Postman / React UI)
|
v
NGINX Gateway (Port 80)
|

| | |
Auth-Service Doctor-Service Patient-Service
(8082) (8080) (8081)



## Tech Stack
- Java 17
- Spring Boot
- JWT Authentication
- Nginx (Reverse Proxy / Gateway)
- PostgreSQL
- Maven

## Microservices

### Auth-Service (Port 8082)
Handles:
- User registration and login
- JWT generation and validation
- `/auth/validate` endpoint for token verification used by Nginx
- Sends back user information through custom headers:
  - `X-User-Id`
  - `X-User-Role`
  - `X-User-Name`

### Doctor-Service (Port 8080)
Handles:
- Doctor registration and profile APIs
- Receives verified JWT data from Nginx as headers
- Example endpoints:
  - `POST /doctor/register`
  - `GET /doctor/profile`

### Patient-Service (Port 8081)
Handles:
- Patient registration and appointment management
- Similar JWT handling as doctor service

## Nginx Configuration
Acts as the entry point for all requests.  
Routes are defined as follows:
- `/auth/**` → Auth-Service (8082)
- `/doctor/**` → Doctor-Service (8080)
- `/patient/**` → Patient-Service (8081)
- `/auth/validate` → Internal endpoint for JWT validation

### File Location
`/nginx-1.28.0/conf/nginx.conf`

### Start or Reload Nginx
```bash
./nginx.exe
./nginx.exe -s reload
