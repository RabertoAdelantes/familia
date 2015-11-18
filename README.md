# Build application
 mvn clean install 
# Check source codes
mvn pmd:check checkstyle:checkstyle
# Check if any new denenies libraries are available 
mvn versions:display-dependency-updates