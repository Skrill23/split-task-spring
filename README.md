# split-task-spring

## Description

This Spring Boot Project produces solution for cash splitting into bills and coins over REST-API. For multiple request it shows the differnces between the last and current requests.

## API-Endpoints
GET `api/split/{money}`

Example:
```
GET  http://localhost:8080/api/split/234.23
```

Response:
```
{
  "splitInfo": {
    "200.0": 1,
    "20.0": 1,
    "10.0": 1,
    "2.0": 2,
    "0.2": 1,
    "0.02": 1,
    "0.01": 1
  },
  "resultType": "Anzahl"
}
```