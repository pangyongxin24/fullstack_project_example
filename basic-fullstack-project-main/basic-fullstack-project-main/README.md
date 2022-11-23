
# Basic Fullstack Project

This repo contains a basic example of a fullstack java project.

## How to build and execute the project.

```shell
 $ cd ~/java-fullstack-sample

 # Content of the project.
 $ ls
pom.xml  README.md  src

 # Build the project and include the dependencies such as the mariadb driver.
 $ mvn assembly:assembly -DdescriptorId=jar-with-dependencies

 # If the build succeeded, there will be a target directory.
 $ ls target/
 archive-tmp  fullstackproject-0.0.1-SNAPSHOT.jar   generated- sources
 classes      fullstackproject-0.0.1-SNAPSHOT-jar-with-dependencies.jar
 maven-archiver  surefire-reports generated-test-sources  maven-status    test-classes

> Note that there are 2 jar generated, one contains the dependencies and the other just
> the classes of the project.

 # To see the content of the jars, run:
 $ jar tf ./target/fullstackproject-0.0.1-SNAPSHOT.jar
 $ jar tf ./target/fullstackproject-0.0.1-SNAPSHOT-jar-with-dependencies.jar


 # Execute the class that contains the main method.
 $ java -cp target/fullstackproject-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.fullstack.project.Main
 Basic fullstack project
 Product name: MariaDB
 Database version: 10
 Driver name: MariaDB Connector/J
 Driver version: 3.0.7

 # Clean the project
 $ mvn clean
```

> For more information about maven see:
> https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

## How to run the test cases

```shell
 $ mvn test
```

## How to connect to MariaDb

```shell
 $ mysql -u root  -p

MariaDB [(none)]> show databases;
+------------------------+
| Database               |
+------------------------+
| information_schema     |
| mysql                  |
| people_db              |
| performance_schema     |
+------------------------+
5 rows in set (0.000 sec)

MariaDB [(none)]> use people_db;

Database changed

MariaDB [people_db]> show tables;
+---------------------+
| Tables_in_people_db |
+---------------------+
| country             |
| person              |
+---------------------+
2 rows in set (0.000 sec)
```
