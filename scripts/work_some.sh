curl -X 'POST' \
  'http://localhost:8080/api/workLogs' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "uuid": "first shift manual uuid",
  "workDay": "2022-04-09",
  "startTime": "14:30",
  "stopTime": "18:30",
  "department": "CLOTHING",
  "whenPeopleWorked": 10001
}'
