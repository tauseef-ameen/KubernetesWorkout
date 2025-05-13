## Lab 5 - Deploy, scale and manages Java Spring Boot Application

This document lists all commands used in **Lab 5** with brief, corrected explanations.

---

### Command 1 - Create a Namespace

```bash
kubectl create namespace chapter4
```

> Creates a new namespace `chapter4` to isolate lab resources.

---

### Command 2 - List All Namespaces

```bash
kubectl get namespace
```

> Lists all namespaces in the cluster.
---

### Command 3 - Tag and Push the Docker Image

```bash
docker tag k8sworkout/ch02:lab1 k8sworkout/ch04:lab5
docker push k8sworkout/ch04:lab5
```

> Tags the existing image as `ch04:lab5` and pushes it to the Docker registry.
---

### Command 4 - Generate Deployment YAML

```bash
kubectl create deployment lab5deploy --image=k8sworkout/ch04:lab5 --replicas=2 -n chapter4 --dry-run=client -o yaml > lab5.yaml
```

> Generates a YAML file for the deployment with 2 replicas, but does not apply it.

---

### Command 5 - Create the Deployment from YAML

```bash
kubectl create -f lab5.yaml
```

> Applies the `lab5.yaml` file to create the deployment.

---

### Command 6 - List Deployments

```bash
kubectl get deploy -n chapter4
```

> Lists deployments in the `chapter4` namespace.

---

### Command 7 - List ReplicaSets

```bash
kubectl get rs -n chapter4
```

> Lists ReplicaSets in the `chapter4` namespace.

---

### Command 8 - Port Forward to Access App

```bash
kubectl port-forward --address localhost deployment/lab5deploy 5000:8081 -n chapter4
```

> Forwards pod port `8081` to local port `5000`.

---

### Command 9 - View Deployment Strategy

```bash
kubectl describe deploy lab5deploy -n chapter4 | grep -E 'StrategyType|RollingUpdateStrategy'
```

> Shows the deployment strategy (e.g., RollingUpdate) used by `lab5deploy`.

---

### Command 10 - Scale Deployment Replicas

```bash
kubectl scale deployment lab5deploy --replicas=4 -n chapter4
```

> Scales the deployment to 4 replicas.

---

### Command 11 - Update Deployment Image

```bash
kubectl set image deploy lab5deploy ch04=k8sworkout/ch04:test -n chapter4
```

> Updates the image of the container named `ch04` in `lab5deploy`.

---

### Command 12 - View Deployment Summary

```bash
kubectl describe deploy -n chapter4 | grep -E "Name:|Image:|Selector:"
```

> Extracts key details like name, image, and selector from the deployment.

---

### Command 13 - Check Rollout Status

```bash
kubectl rollout status deployment lab5deploy -n chapter4
```

> Checks the rollout status of the deployment to see if it's complete.

---

### Command 14 - Rollback Deployment

```bash
kubectl rollout undo deployment lab5deploy -n chapter4
```

> Rolls back the deployment to the previous working version.

---

### Command 15 - Delete Specific Pods

```bash
kubectl delete pod lab5deploy-5cb795cd64-mgj7t lab5deploy-5cb795cd64-r7bjj -n chapter4 
```

> Deletes specific pods to force recreation by the ReplicaSet.

---

### Command 16 - Get Pod Details in Table Format

```bash
kubectl -n chapter4 get pods -o custom-columns="NAME:.metadata.name, STATUS:.status.phase, NODE:.spec.nodeName"
```

> Displays pod names, statuses, and node assignments in a custom column view.

---

### Command 17 - Mark Node as Unschedulable

```bash
kubectl cordon minikube
```

> Prevents new pods from being scheduled on the `minikube` node.

---

### Command 18 - Drain Node

```bash
kubectl drain minikube --ignore-daemonsets --force
```

> Evicts pods from the node and prepares it for maintenance.

---

## Notes

* Always verify the image is pushed before deployment.
* Use the correct namespace (`chapter4`) in all commands.
* Port-forwarding allows local access to cluster services.

---

