1) Create a database and a user on your machine.
2) liquibase.properties-file, use the same db and credentials

3) log in as root to mysql
3.1) mysql> CREATE database PostService;
3.2) mysql> GRANT ALL PRIVILEGES ON PostService.* To 'admin'@'localhost' IDENTIFIED BY 'admin';

4)   Execute the project by running :
4.1) $mvn clean install
4.2) $mvn liquibase:update
4.3) $java -jar CrudCosoleProject-1.0-SNAPSHOT-jar-with-dependencies.jar

5.0) PostgreSQL hints
sudo -u postgres createdb denmark
CREATE USER roger WITH PASSWORD 'roger';
psql -d postgres
postgres=# create database denmark_development;
postgres=# GRANT ALL PRIVILEGES ON DATABASE denmark_development to roger;
psql -d denmark