#!/bin/bash

# Clean and build the project, and create a single jar with all dependencies
mvn clean install

# Execute the jar
java -jar target/oop2-0.0.1.jar

# Clean the project after execution

