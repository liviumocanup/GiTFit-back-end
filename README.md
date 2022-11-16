# PBL DAS GiTFit Back-end side

## Abstract
Project made under tutoring from Salt Edge [Autumn 2022].

Technologies: Spring Boot with Maven and MySql Database.

## Configuration
### This readme file is a guide to run 'gitfit-be' project using command line

1. Download the project 'gitfit-be' as an archived file (ex:rar) and unzip it

2. Download Docker Desktop for Windows from the link : https://docs.docker.com/desktop/install/windows-install/

### IMPORTANT!!  Check if WSL was installed using: wsl -l -v, if you have some error using wsl --install for installing wsl

3. After installing Docker, run it

4. Open command line : Win+r , in opened tab type : cmd , after that press enter

5. In command line open the pathway to the project 'gitfit-be' : cd file-path (ex: cd C:\Users\user\OneDrive\Desktop\gitfit-be)

6. When we are in project directory enter this command: cd docker

7. After that, enter this command : docker-compose up -d

8. After we entered those commands, we should wait until the installation ends
   And check our docker if in tab 'Containers' is present the 'docker' container with 'Running(1/1)' status

9. Now we should go back to our project directory from the step number 5 : cd file-path

10. When we are back to our project directory enter this command : mvn flyway:migrate

11. After that enter this command : mvn clean install

12. Wait until the end and run the project using this command: java -jar target/GiTFit-0.0.1-SNAPSHOT.jar

13. For checking if project works you can do a get request in postman using: http://localhost:8080/clients/save

### It should work now, and will be on while the command line is open.
