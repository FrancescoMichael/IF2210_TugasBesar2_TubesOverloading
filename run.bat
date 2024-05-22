cd remidi-kelola-kerajaan
mvn clean package assembly:single
java -jar backend/target/backend-1.0-SNAPSHOT-jar-with-dependencies.jar
