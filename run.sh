#!/bin/bash

# Navigate to the project directory
cd remidi-kelola-kerajaan

# Clean and build the project, and create a single jar with all dependencies
mvn clean package assembly:single

# Execute the jar
java -jar backend/target/backend-1.0-SNAPSHOT-jar-with-dependencies.jar

# Clean the project after execution

