git pull origin main
JAVA_HOME=/root/.sdkman/candidates/java/current
./mvnw clean package -Dmaven.test.skip
docker compose up --build -d