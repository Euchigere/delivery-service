# Delivery Service

## About
A service to process and receive Gorillas deliveries

## Getting Started
You need Java 11 to run this application

To start app, run the following command `./gradlew bootrun`

To run test `./gradlew test`

To build Application jar `./gradlew build`

## Usage
1. Go to the root folder of the project using the terminal
2. Start the app with the following command `./gradlew bootrun`
3. The GraphQL server will start up at `http://localhost:8080`
   - `/graphql` — GraphQL server endpoint used for processing queries and mutations
   - `/sdl` — convenience endpoint that returns current schema in Schema Definition Language format
   - `/playground` — Prisma Labs GraphQL Playground IDE endpoint

## Docker
1. Go to the root folder of the project using the terminal
2. Run the following command to build image `docker build . -t deliveryservice`
3. Then run `docker run -p 80:8080 deliveryservice` to run image 
or `docker run -d -p 80:8080 deliveryservice` to run image in detached mode
4. The GraphQL server will start up at `http://localhost:80`
   - `/graphql` — GraphQL server endpoint used for processing queries and mutations
   - `/sdl` — convenience endpoint that returns current schema in Schema Definition Language format
   - `/playground` — Prisma Labs GraphQL Playground IDE endpoint

## Further improvement
- Add functionality to limit or paginate values returned from query
- Add more meta-data to DeliveryStatus as needed
- Add gradle task to build docker image