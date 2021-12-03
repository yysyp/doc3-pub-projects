This instruction works for mac and window as well:
https://kubernetes.github.io/ingress-nginx/deploy/#docker-for-mac

0) kubectl create ns test
1) kubectl apply -f cloud-deploy0.32.0.yaml
2) kubectl get pods --namespace=ingress-nginx
OR: kubectl wait --namespace ingress-nginx --for=condition=ready pod --selector=app.kubernetes.io/component=controller --timeout=120s
3) kubectl create deployment demo --image=httpd --port=80 -n test
4) kubectl expose deployment demo -n test
5) kubectl create ingress demo-localhost --class=nginx --rule=demo.localdev.me/*=demo:80 -n test
6) kubectl port-forward --namespace=ingress-nginx service/ingress-nginx-controller 8080:80
#At this point, if you access http://demo.localdev.me:8080/, you should see an HTML page telling you "It works!".

Clean UP:
kubectl delete namespace test
kubectl delete namespace ingress-nginx
