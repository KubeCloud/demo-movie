kubectl config set-cluster cluster51 --server=http://192.168.1.51:8080
kubectl config set-context cluster51 --cluster=cluster51
kubectl config use-context cluster51

kubectl create -f kubernetes-yaml/preference-service-deployment.yaml
kubectl create -f kubernetes-yaml/preference-service-svc.yaml

sleep 20s
kubectl create -f kubernetes-yaml/movie-service-deployment.yaml
kubectl create -f kubernetes-yaml/movie-service-svc.yaml

sleep 20s
kubectl create -f kubernetes-yaml/ui-deployment.yaml
kubectl create -f kubernetes-yaml/ui-svc.yaml