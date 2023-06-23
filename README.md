# Spring Email Handler

This is a simple email handler for Spring Boot. It uses the JavaMailSender interface to send emails.

To setup the email handler, you need to add .env file in root
```
SPRING_MAIL_USERNAME=
SPRING_MAIL_PASSWORD=
JWT_SECRET_KEY=
```

Endpoints:
```
POST /api/v1.0.0/email/send
GET /api/v1.0.0/email/status
```

To send an email, you need to send a POST request to /api/v1.0.0/email/send with the following body:
```json
{
    "emailTo": "",
    "emailFrom": "",
    "subject": "",
    "body": ""
}
```
You will also need to provide a token in the header of the request. The token is generated using the JWT_SECRET_KEY in the .env file. The token is valid for 1 hour.
