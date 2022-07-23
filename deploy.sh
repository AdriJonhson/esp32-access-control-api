export JAVA_HOME=/root/.sdkman/candidates/java/current

echo $DATABASE_USERNAME
echo $DATABASE_PASSWORD
echo $DATABASE_JDBC_URL

git pull origin main
./mvnw clean package -Dmaven.test.skip
docker compose up --build -d