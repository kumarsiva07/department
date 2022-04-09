curl -X 'POST' \
  'http://localhost:8080/api/departments' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "department": "CLOTHING",
  "name": "Fav Threads for Fab People"
}'
