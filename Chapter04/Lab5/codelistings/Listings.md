## Lab 5 - Deploy, Scale, and Manage a Java Spring Boot Application

This document lists all commands used in **Lab 5** with general examples followed by lab-specific commands.

---

### Listing 4.2 - Create a Namespace

```bash
kubectl create namespace <name-of-namespace>
kubectl create namespace chapter4
```

> Creates a namespace. Here, `chapter4` is used to isolate lab resources.

---

### Listing 4.2 - List All Namespaces

```bash
kubectl get namespaces
```

> Lists all namespaces in the cluster.

---

### Listing 4.3 - Tag and Push the Docker Image

```bash
docker tag <source-image> <target-image>
docker tag k8sworkout/ch02:lab1 k8sworkout/ch04:lab5

docker push <target-image>
docker push k8sworkout/ch04:lab5
```

> Tags an image and pushes it to Docker Hub with a new name.

---

### Listing 4.4 - Generate Deployment YAML

```bash
kubectl create deployment <name> --image=<image> --replicas=<count> -n <namespace> --dry-run=client -o yaml > <file>.yaml
kubectl create deployment lab5deploy --image=k8sworkout/ch04:lab5 --replicas=2 -n chapter4 --dry-run=client -o yaml > lab5.yaml
```

> Creates a YAML for deployment without applying it.

---

### Listing 4.8 - Create Deployment from YAML

```bash
kubectl create -f <path-of-file>.yaml
kubectl create -f lab5.yaml
```

> Creates resources defined in the YAML file.

---

### Listing 4.8 - List Deployments

```bash
kubectl get deployments -n <namespace>
kubectl get deploy -n chapter4
```

> Lists all deployments in the specified namespace.

---

### Listing 4.8 - List ReplicaSets

```bash
kubectl get rs -n <namespace>
kubectl get rs -n chapter4
```

> Lists ReplicaSets in the given namespace.

---

### Listing 4.11 - View Deployment Strategy

```bash
kubectl describe deploy <deployment-name> -n <namespace> | grep -E 'StrategyType|RollingUpdateStrategy'
kubectl describe deploy lab5deploy -n chapter4 | grep -E 'StrategyType|RollingUpdateStrategy'
```

> Displays rollout strategy used by the deployment.

---

### Listing 4.12 - Port Forward to Access App

```bash
kubectl port-forward --address localhost deployment/<deployment-name> <local-port>:<container-port> -n <namespace>
kubectl port-forward --address localhost deployment/lab5deploy 5000:8081 -n chapter4
```

> Forwards a pod port to your local machine.

---

### Listing 4.13 - Scale Deployment Replicas

```bash
kubectl scale deployment <name> --replicas=<count> -n <namespace>
kubectl scale deployment lab5deploy --replicas=4 -n chapter4
```

> Increases or decreases the number of replicas.

---

### Listing 4.14 - Update Deployment Image

```bash
kubectl set image deployment/<deployment-name> <container>=<new-image> -n <namespace>
kubectl set image deploy lab5deploy ch04=k8sworkout/ch04:test -n chapter4
```

> Updates container image of a deployment.

---

### Listing 4.14 - View Deployment Summary

```bash
kubectl describe deployment -n <namespace> | grep -E "Name:|Image:|Selector:"
kubectl describe deploy -n chapter4 | grep -E "Name:|Image:|Selector:"
```

> Shows name, image, and label selector for deployments.

---

### Listing 4.15 - Check Rollout Status

```bash
kubectl rollout status deployment <name> -n <namespace>
kubectl rollout status deployment lab5deploy -n chapter4
```

> Verifies if the deployment rollout is complete.

---

### Listing 4.16 - Rollback Deployment

```bash
kubectl rollout undo deployment <name> -n <namespace>
kubectl rollout undo deployment lab5deploy -n chapter4
```

> Rolls back to the previous version of deployment.

---

### Listing 4.17 - Delete Specific Pods

```bash
kubectl delete pod <pod1> <pod2> -n <namespace>
kubectl delete pod lab5deploy-5cb795cd64-mgj7t lab5deploy-5cb795cd64-r7bjj -n chapter4
```

> Deletes specific pods; ReplicaSet will recreate them.

---

### Listing 4.17 - Get Pod Details in Table Format

```bash
kubectl get pods -n <namespace> -o custom-columns="NAME:.metadata.name, STATUS:.status.phase, NODE:.spec.nodeName"
kubectl -n chapter4 get pods -o custom-columns="NAME:.metadata.name, STATUS:.status.phase, NODE:.spec.nodeName"
```

> Displays pod name, status, and node in custom columns.

---

### Listing 4.18 - Mark Node as Unschedulable

```bash
kubectl cordon <node-name>
kubectl cordon minikube
```

> Stops new pods from being scheduled on the node.

---

### Listing 4.19 - Drain Node

```bash
kubectl drain <node-name> --ignore-daemonsets --force
kubectl drain minikube --ignore-daemonsets --force
```

> Evicts pods and prepares the node for maintenance.

---

### Notes

* Use `chapter4` as the namespace throughout this lab.
* Replace names and ports when adapting for your own app.
* Make sure the image is pushed before creating deployments.

---
