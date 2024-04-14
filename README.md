<h1 align="center">Alura Forum</h1>
<h2 align="center">register and queries for users, topics and answers</h2>

<p align="center">
    <img src="https://img.shields.io/badge/Alura_ONE-Challenge%234-orange">
    <img src="https://img.shields.io/badge/status-finished-blue"><br>
    <img src="https://img.shields.io/badge/JRE-17-red">
    <img src="https://img.shields.io/badge/Spring_Boot-3.1.8-green">
</p>

<p align="center">
    <img width="300" heigth="300" src="https://user-images.githubusercontent.com/91544872/209678377-70b50b21-33de-424c-bed8-6a71ef3406ff.png">
</p>

## Technologies used
* Java
* [Maven](https://maven.apache.org) - Dependency handler
* [Spring Initializer](https://start.spring.io)
* [Lombok](https://projectlombok.org) - Code automator
* [FlyWay](https://flywaydb.org) - Database versioning
* [MySQL](https://mysql.com) - Database
* [JWT Token](https://jwt.io) - Login authentication
* [OpenAPI](https://springdoc.org) - Spring documentation

## Introduction
<p>This project is part of a course given by Alura and Oracle Next Education</p>
<p>This API allows communication between the front-end and the database. By means of diffeerent methods,
it is possible to create, read, update and delete (CRUD) users, topics, categories and answers. Each
user must access via his username and password, and use the token obtained to make the various request.</p>

## API methods
<img src="swagger-ui.jpeg">

## Main features
* Users
    * username and unique emails
    * roles: ADMIN, USER, VIEWER
* Topics
    * linked to the user who creates it and the category it belongs to
    * creation and update date record
    * status: OPEN, CLOSED, DELETED
    * when an answer is marked, the status change to CLOSED
* Anwers
    * linked to the user who creates it and the topic it belongs to
    * creation and update date record
    * only one answer could be mark as a solution

## Posible improves
* Every user has a role asigned, this roles should limit the access to different specific methods.
So, an ADMIN would have access to all methods, an USER would only could edit his own username, own topic, anwers and have
access to view others topic. And a VIEWER would have access to the consulting methods.

## Acknowledgments
🧡 <strong>Oracle</strong><br>
<a href="https://www.linkedin.com/company/oracle/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>

💙 <strong>Alura Latam</strong></br>
<a href="https://www.linkedin.com/company/alura-latam/mycompany/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>

[def]: wagge