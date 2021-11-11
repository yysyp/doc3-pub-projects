call mvn clean package
if %errorlevel% neq 0 exit /b %errorlevel%

REM call docker login xxxurl -username user -password passwd
REM set "currentDirectory=%cd%"
REM cd ../..
REM call dockerlogin.bat
REM if %errorlevel% neq 0 exit /b %errorlevel%
REM cd %currentDirectory%

call docker build -t simple-news:v1 .
if %errorlevel% neq 0 exit /b %errorlevel%

REM call docker push xxxurl/com/example/springinitiallizerdemo/simple-news:v1

echo 'docker run -p 80:8080 -itd -e spring.profiles.active=dev simple-news:v1'
echo '$ sudo docker exec -it CONTAINERID /bin/bash'
echo '$ sudo docker exec -it CONTAINERID /bin/sh'
echo '$ sudo docker attach CONTAINERID'
echo '$ sudo docker run -itd simple-news:v1 /bin/bash'

call kubectl delete services simple-news-service
call kubectl delete secrets simple-news-key
call kubectl delete deployments simple-news-web

call kubectl create -f k8s.yaml
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

echo 'kubectl get secret simple-news-key -o yaml'
echo 'kubectl exec -it simple-news-web-7f9699c8b7-m6dcx -n mynamespace -- /bin/sh'
echo 'http://localhost:30080/springdoc/docs.html'
echo 'http://localhost:30080/springdoc/api-docs'
echo 'http://localhost:30080/springdoc/api-docs.yaml'
