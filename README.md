# E-Commerce Microservices Application

## Overview

This project is a microservices-based e-commerce application designed to demonstrate the use of modern technologies and architectural patterns. The application handles key functionalities such as user authentication, product management, order processing, notifications, and payments.

## Key Features

Service Discovery: Eureka Server is used for microservices registration and discovery.

Asynchronous Communication: Apache Kafka is utilized for notifications and payment event handling.

Email Notifications: A mailing service ensures timely delivery of emails to users.

User Authentication and Authorization: Keycloak provides a robust security solution with role-based access control.

Frontend Rendering: Thymeleaf is used for server-side rendering of HTML templates.

Database Support:

MongoDB for document-based data storage.

PostgreSQL for relational data storage.

## Technologies Used

Java 21: The primary programming language for all microservices.

Spring Boot: Provides a framework for building scalable and production-ready services.

Eureka Server: Handles service registration and discovery.

Apache Kafka: Facilitates message-driven architecture for notifications and payments.

Keycloak: Manages security, authentication, and authorization.

MongoDB: Used for services requiring NoSQL data handling.

PostgreSQL: Relational database for structured data.

Thymeleaf: HTML template engine for dynamic front-end content.

Docker: Used for containerization and environment consistency.

Microservices

Eureka Server

Manages service registry and ensures seamless communication between microservices.

Notification Service

Sends notifications to users.

Utilizes Apache Kafka for event-driven messaging.

Payment Service

Handles payment processing.

Uses Kafka for event handling and PostgreSQL for transaction data.

Mailing Service

Sends confirmation and promotional emails to users.

Integrates with an SMTP provider.

Product Service

Manages product catalog.

Uses MongoDB for product data.

Order Service

Handles order processing and management.

Uses PostgreSQL for relational data.

## Prerequisites

Java 21

Docker

Kafka

Keycloak

MongoDB



