FROM openjdk:8
COPY ./target/EmployeeApplication-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch EmployeeApplication-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","EmployeeApplication-0.0.1-SNAPSHOT.jar"]