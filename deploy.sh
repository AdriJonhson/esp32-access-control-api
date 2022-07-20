git pull origin main
./mvnw clean package -Dmaven.test.skip
docker compose up --build -d