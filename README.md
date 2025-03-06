# nba_stat

How to run:
1. Download the project
2. Build the project with maven with the command:  mvn clean package -DskipTests
3. Run docker-compose with the command:  docker-compose up --build

API:
http://localhost:8080/api/teams
POST: create team
  Body: {
            "name": "NYN"
        }
GET(/{id}) : get team by Id
GET: get all teams
PUT(/{id}): update by id with body
DELETE(/{id}): delete by id

http://localhost:8080/api/players
POST: create player
  Body: {
        "name": "Adan M",
        "teamId" : 1
        }
GET(/{id}) : get player by Id
GET: get all players
PUT(/{id}): update by id with body
DELETE(/{id}): delete by id

http://localhost:8080/api/statistics
POST(/log) log players game statistic
gets list of: 
    [
        {
        "playerId": 1,
        "gameDate": "2025-03-03",
        "points": 30,
        "rebounds": 9,
        "assists": 4,
        "steals": 2,
        "blocks": 1,
        "fouls": 3,
        "turnovers": 2,
        "minutesPlayed": 30.5
        }
    ]

GET(/player/{playerId}): Get the aggregate statistic by player id

GET(/team/{teamId}): Get team aggregate statistic by team id

API call example:
Create a new team:
curl --location 'http://localhost:8080/api/teams' \
--header 'Content-Type: application/json' \
--data '{
  "name": "NYN"
}'

Create new player:
curl --location 'http://localhost:8080/api/players' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Romi",
  "teamId" : 1
}'

Log player statistics:
curl --location 'http://localhost:8080/api/statistics/log' \
--header 'Content-Type: application/json' \
--data '[
    {
    "playerId": 1,
    "gameDate": "2025-03-03",
    "points": 30,
    "rebounds": 9,
    "assists": 4,
    "steals": 2,
    "blocks": 1,
    "fouls": 3,
    "turnovers": 2,
    "minutesPlayed": 30.5
    }
]
'

Get player aggregate statistics:
curl --location 'http://localhost:8080/api/statistics/player/1' \
--data ''

Get team aggregate statistics:
curl --location 'http://localhost:8080/api/statistics/team/1' \
--data ''

Explanation:
After reading the task documentation and understanding the process, I decided to create a small spring boot application to handle:
Controller Layer: Handles HTTP requests, uses @RestController for RESTful endpoints.
Service Layer: Contains business logic, annotated with @Service.
Repository Layer: Manages data access and uses Spring Data JPA with @Repository.
Entity Layer: Represents data models and uses JPA annotations.

DB:
I chose PostgreSQL for its robustness and support for complex queries. For caching, I used Redis to improve response times and reduce database load1.

Considerations:
To support full Scalability, I would also add a message broker like Kafka or Artemis to handle async messages.


AWS deployment:
1. Containerization: Package the application as a Docker image.
2. ECR: Push the Docker image to Amazon Elastic Container Registry.
3. ECS/Fargate: Deploy the containerized application using Amazon ECS with Fargate for serverless container management.
4. RDS: Use Amazon RDS for the PostgreSQL database.
5. ElastiCache: Implement Redis caching with Amazon ElastiCache.
6. Load Balancing: Use Application Load Balancer for distributing traffic.
7. Auto Scaling: Configure Auto Scaling groups for handling varying loads
