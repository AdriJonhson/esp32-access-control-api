userName=$1
password=$2
jdbcUrl=$3

echo $userName
echo $password
echo $jdbcUrl

export DATABASE_USERNAME=$userName
export DATABASE_PASSWORD=$password
export DATABASE_JDBC_URL=$jdbcUrl
export JAVA_HOME=/root/.sdkman/candidates/java/current

git pull origin main
./mvnw clean package -Dmaven.test.skip
docker compose up --build -d