
Run & Start: ./buildk8s.bat
---------------------------------------------------------------------------------------------
Swagger Doc Visit:
    http://localhost:30080/springdoc/docs.html
    http://localhost:30080/springdoc/api-docs.yaml

H2 memory Database:
    http://localhost:30080/h2-console/login.jsp?jsessionid=8e0263a9893eafe9d84150e2e8ea4614
        JDBC URL: jdbc:h2:mem:testdb
        UserName: sa
        Password:


---------------------------------------------------------------------------------------------
Stop: kubectl delete namespace app