# easy-rest-api

Easy Rest APi is an example api rest project that use springboot-data-rest to implement it.
It uses spring-data-jpa to implement repositories and in-memory database H2 to persist de data.

For this example there was created 4 endpoint to manage 'Subsidiaries':

### Get Subsidiaries: 
* Method: GET
* endpoint: /subsidiaries
* Response: 
  ```
  [   
    {
      "id":1,
      "created":"2024-05-24T19:27:16.983005",
      "modified":"2024-05-24T19:27:16.983005",
      "code":"ADF1321",
      "name":"Florida 1",
      "country":"USA",
      "address":"Av Florida 1212, Florida",
      "manager":"Juan Perez",
      "phoneNumber":"+1 1245622655"
    },
    {
      "id":2,
      "created":"2024-05-24T19:27:16.983009",
      "modified":"2024-05-24T19:27:16.983009",
      "code":"ADF1399",
      "name":"Florida 2",
      "country":"USA",
      "address":"Av Florida 1212, Florida",
      "manager":"Juan Perez",
      "phoneNumber":"+1 1211122655"
    },
    {
      "id":3,
      "created":"2024-05-24T19:27:16.983009",
      "modified":"2024-05-24T19:27:16.983009",
      "code":"ADF1355",
      "name":"Atlanta 1",
      "country":"USA",
      "address":"Av Atlanta 1212, Atlanta",
      "manager":"Juan Perez",
      "phoneNumber":"+1 1211444655"
    }
  ]
  ```

### Get Subsidiary by CODE:
* Method: GET
* endpoint: /subsidiary/ADF1321
* input: 'code'
* Response: 

```
{
"id":1,
"created":"2024-05-24T19:27:16.983005",
"modified":"2024-05-24T19:27:16.983005",
"code":"ADF1321",
"name":"Florida 1",
"country":"USA",
"address":"Av Florida 1212, Florida",
"manager":"Juan Perez",
"phoneNumber":"+1 1245622655"
}
```

### Get Subsidiaries by NAME:
* Method: GET
* endpoint: /subsidiaries?name=Florida
* Response:

  ```
  [   
    {
      "id":1,
      "created":"2024-05-24T19:27:16.983005",
      "modified":"2024-05-24T19:27:16.983005",
      "code":"ADF1321",
      "name":"Florida 1",
      "country":"USA",
      "address":"Av Florida 1212, Florida",
      "manager":"Juan Perez",
      "phoneNumber":"+1 1245622655"
    },
    {
      "id":2,
      "created":"2024-05-24T19:27:16.983009",
      "modified":"2024-05-24T19:27:16.983009",
      "code":"ADF1399",
      "name":"Florida 2",
      "country":"USA",
      "address":"Av Florida 1212, Florida",
      "manager":"Juan Perez",
      "phoneNumber":"+1 1211122655"
    }
  ]
  ```

### Create Subsidiary:
* Method: POST
* endpoint: /subsidiary
* Request:

```
{
"code":"ADF1321",
"name":"Florida 1",
"country":"USA",
"address":"Av Florida 1212, Florida",
"manager":"Juan Perez",
"phoneNumber":"+1 1245622655"
}
```

* Response:
```
{
"id":1,
"created":"2024-05-24T19:27:16.983005",
"modified":"2024-05-24T19:27:16.983005",
"code":"ADF1321",
"name":"Florida 1",
"country":"USA",
"address":"Av Florida 1212, Florida",
"manager":"Juan Perez",
"phoneNumber":"+1 1245622655"
}
```
