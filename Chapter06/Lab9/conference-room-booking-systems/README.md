# Lab 9: Currency Converter API with Kubernetes Secrets

This example demonstrates how to integrate **Kubernetes Secrets** into an application for securely injecting sensitive data like API tokens. It is part of Chapter 6 of the *Kubernetes Workout* book.

---

## Overview

In this project, we extend a **Spring Boot microservice (Conference Room Booking System)** to include a new endpoint for currency conversion using a third-party API that requires a token.  
The token is **not hardcoded**; it is injected via a **Kubernetes Secret** at runtime.

---

## Features

- Build a REST API endpoint: `/convert/{fromCurrency}/{toCurrency}`
- Fetch live exchange rates from [ExchangeRate API](https://www.exchangerate-api.com/)
- Securely inject API tokens using **Kubernetes Secrets**
- Configure API URL using **Kubernetes ConfigMap**
- Run the application in **Kubernetes** (Minikube or any cluster)

---
## Key Configurations

Your Spring Boot application reads both values from environment variables at runtime:

**`application.properties`**
```properties
manning.workout.token=${CURRENCY_TOKEN:}   # Secret
manning.workout.url=${API_URL:https://v6.exchangerate-api.com/v6/%s/latest/%s}   # ConfigMap
```
* **CURRENCY_TOKEN** → Injected from Kubernetes Secret
* **API_URL** → Injected from Kubernetes ConfigMap
---

## Tech Stack

- **Java 17**
- **Spring Boot**
- **Docker**
- **Kubernetes**
---


### Steps to Run Locally Using Docker (Without Kubernetes)

1. Clone this repository:
   ```bash
   git clone https://github.com/tauseef-ameen/KubernetesWorkout.git
    ````
   and navigate to Lab 9
   ```bash
   cd KubernetesWorkout/Chapter06/Lab9/conference-room-booking-systems
    ````

2. Build and create spring boot application image:

   ```bash
   mvn clean install -P createimage
   ```
   This creates the image manning/room-booking-system-jre:1.0.0-SNAPSHOT locally.

3. Run the image with Docker, passing the API token as an environment variable.   
*Note:* Without Kubernetes, set the environment variable manually.
    ```bash
   docker run -p 8080:8080 -e CURRENCY_TOKEN=<your-token> manning/room-booking-system-jre:1.0.0-SNAPSHOT
   ```
4. Test the API:
   ```bash
   http://localhost:8080/convert/EUR/USD
   ```

