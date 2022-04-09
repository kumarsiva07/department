curl -X 'POST' \
  'http://localhost:8080/api/peoples' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "uuid": "Matt UUID",
  "firstName": "Matt",
  "lastName": "Payne",
  "email": "fake@email.com",
  "peopleWorkInDepartmentss": [
    10000
  ]
}'
