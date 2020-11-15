This is a simple implementation of a console CRUD application, which
interacts with mySQL and migrates DB using liquibase.

Run Project:
1) log in as root to mysql
1.1) mysql> CREATE database PostService;
1.2) mysql> CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
     mysql> GRANT ALL PRIVILEGES ON PostService.* TO 'admin'@'localhost';

2)   Execute the project by running :
2.1) $mvn clean install
2.2) $mvn liquibase:update
2.3) $java -jar CrudConsoleProject-1.0-SNAPSHOT-jar-with-dependencies.jar

