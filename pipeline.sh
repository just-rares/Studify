#!/bin/bash

# Run Maven compile and test commands
mvn compile -pl server
mvn compile -pl client
mvn test -pl server
mvn test -pl client
