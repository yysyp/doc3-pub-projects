
echo '-----------------Initializing...-----------------'
set "curDir=%cd%"
set imgName=simple-news
set ver=v1
set ns=app

echo 'Please prepare the Dockerfile, k8s.yaml in currentDirectory!'
echo 'curDir=%curDir% - imgName=%imgName% - ver=%ver% - ns=%ns%'

echo '-----------------Maven build and package...-----------------'
call mvn clean package
if %errorlevel% neq 0 exit /b %errorlevel%

REM call docker login xxxurl -username user -password passwd
REM set "currentDirectory=%cd%"
REM cd ../..
REM call dockerlogin.bat
REM if %errorlevel% neq 0 exit /b %errorlevel%
REM cd %currentDirectory%

echo '-----------------Docker build...-----------------'
call docker build -t %imgName%:%ver% .
if %errorlevel% neq 0 exit /b %errorlevel%

REM call docker push xxxurl/com/example/springinitiallizerdemo/simple-news:v1
REM echo 'docker run -p 80:8080 -itd -e spring.profiles.active=dev simple-news:v1'
REM echo '$ sudo docker exec -it CONTAINERID /bin/bash'
REM echo '$ sudo docker exec -it CONTAINERID /bin/sh'
REM echo '$ sudo docker attach CONTAINERID'
REM echo '$ sudo docker run -itd simple-news:v1 /bin/bash'

echo '-----------------Kubernetes deploy...-----------------'
call kubectl delete namespace %ns%
call kubectl create namespace %ns%
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%
call kubectl apply -f k8s.yaml
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

REM echo 'kubectl get secret simple-news-key -o yaml'
REM echo 'kubectl exec -it simple-news-web-7f9699c8b7-m6dcx -n mynamespace -- /bin/sh'
REM echo 'http://localhost:30080/springdoc/docs.html'
REM echo 'Explore /springdoc/api-docs'
REM echo 'http://localhost:30080/springdoc/api-docs'
REM echo 'http://localhost:30080/springdoc/api-docs.yaml'
REM echo kubectl describe ingress nodeport-ingress -n app
