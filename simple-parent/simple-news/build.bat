call mvn clean package
if %errorlevel% neq 0 exit /b %errorlevel%

REM call docker login xxxurl -username user -password passwd
set "currentDirectory=%cd%"
cd ../..
call dockerlogin.bat
if %errorlevel% neq 0 exit /b %errorlevel%
cd %currentDirectory%

call docker build -t spring-initiallizer-demo:v1 .
if %errorlevel% neq 0 exit /b %errorlevel%

REM call docker push xxxurl/com/example/springinitiallizerdemo/spring-initiallizer-demo:v1

echo 'docker run -p 80:8080 -itd -e spring.profiles.active=dev spring-initiallizer-demo:v1'
echo '$ sudo docker exec -it CONTAINERID /bin/bash'
echo '$ sudo docker exec -it CONTAINERID /bin/sh'
echo '$ sudo docker attach CONTAINERID'
echo '$ sudo docker run -itd spring-initiallizer-demo:v1 /bin/bash'

call kubectl delete services spring-initiallizer-demo-service
call kubectl delete secrets spring-initiallizer-demo-key
call kubectl delete deployments spring-initiallizer-demo-web

call kubectl create -f k8s/app-service.yaml
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%
call kubectl create -f k8s/app-secret.yaml
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%
call kubectl create -f k8s/web-deployment.yaml
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

echo 'kubectl get secret spring-initiallizer-demo-key -o yaml'
echo 'kubectl exec -it spring-initiallizer-demo-web-7f9699c8b7-m6dcx -n mynamespace -- /bin/sh'
echo 'http://localhost:30080/swagger-ui/index.html'
echo 'http://localhost:30080/v3/api-docs'
echo 'http://localhost:30080/v3/api-docs.yaml'
