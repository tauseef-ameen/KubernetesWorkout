## Lab 9 - Protecting sensitive data using Kubernetes secret

This document lists all commands used in **Lab 9**.

---
### Listing 6.5 Build & Push Image
build image using maven
```bash
mvn clean install -P createimage
```
tag image
```bash
docker tag manning/room-booking-system-jre:1.0.0-SNAPSHOT k8sworkout/ch06:lab9
```
push image
```bash
docker push k8sworkout/ch06:lab9
```
---
### Listing 6.6 Start Minikube cluster
```bash
minikube start
```
---

### Listing 6.7 Create Namespace
```bash
kubectl create namespace chapter6
 ```
---
### Listing 6.8  Create Secret (docker-registry)
```bash
  kubectl create secret docker-registry {my-registry-secret} --docker-username=user --docker-password=pass --docker-server=registry.example.com
```
---
### Listing 6.9  Create Secret (generic: API Token)
Prepare an env file:
```bash
echo -n 'token=<your-token>' > token.env
```
Create secret:
```bash
kubectl create secret generic currency-token \
--from-env-file=token.env \
-n chapter6
```
---
### Listing 6.10 Create ConfigMap (API URL)
```bash
kubectl create cm currency-api \
--from-literal=API_URL=https://v6.exchangerate-api.com/v6/%s/latest/%s \
-n chapter6
```
---
### Listing 6.11 List secret
```bash
kubectl get secret -n chapter6
```
---
### Listing 6.12 Generate Pod Manifest
```bash
kubectl -n chapter6 run lab9 \
--image=k8sworkout/ch06:lab9 \
--dry-run=client -o yaml > lab9.yaml
```
---
### Listing 6.13 Reference of secret
Edit `lab9.yaml` to inject:
```yaml
env:
  - name: CURRENCY_TOKEN
    valueFrom:
      secretKeyRef:
        name: currency-token
        key: token
```
---
### Listing 6.14 Reference of configMap
Edit `lab9.yaml` to inject:
```yaml
spec:
  containers:
    - image: k8sworkout/ch06:lab9
      name: lab9
      envFrom:
        - configMapRef:
            name: currency-api
 ```
---
### Listing 6.16 Create Pod
Apply:
```bash
kubectl create -f lab9.yaml
 ```
Verify:
```bash
kubectl -n chapter6 get pods
```
---
### Listing 6.17 Optional Secret
```yaml
env:
  - name: CURRENCY_TOKEN
    valueFrom:
      secretKeyRef:
        name: currency-token
        key: token
        optional: true
```
---
### Listing 6.18 Verify secret injection (access API endpoint)
Forward port:
```bash
  kubectl port-forward --address localhost pod/lab9 5005:8081 -n chapter6
```
---
### Listing 6.20 Test:
```bash
  curl -s http://localhost:5005/convert/EUR/USD
```
---
### Listing 6.21 Verify secret injection (peek inside container's shell)
```bash
  kubectl -n chapter6 exec lab9 -- printenv | grep TOKEN
```
---
### Listing 6.22 Cleanup
```bash
kubectl delete namespace chapter6
```
---
### Security Note

* Kubernetes Secrets are **Base64-encoded, not encrypted**.
* Use **RBAC**, **encryption at rest**, and external secret managers for real security.
* Never expose tokens in API responses in production.
---
### Reference

This example is part of **Chapter 6 - Handling Sensitive Data using Secrets** in the [Kubernetes Workout](https://github.com/tauseef-ameen/KubernetesWorkout) repository.

---