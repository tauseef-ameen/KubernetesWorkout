
## Solution of Lab 6 - Deploy and scale a Java Quarkus Application

### Step 1: Pull image to confirm availability

```sh
docker pull k8sworkout/ch04:lab6
```
Check: No error means the image is available.

---

### Step 2: Generate deployment YAML with 3 replicas

```sh
kubectl create deployment quarkus-app \
  --image=k8sworkout/ch04:lab6 \
  --replicas=3 \
  --dry-run=client -o yaml > lab6-quarkus-deployment.yaml
```

Then apply it:

```sh
kubectl apply -f lab6-quarkus-deployment.yaml -n chapter4
```

---

### Step 3: Check if it's running

```sh
kubectl get deployment -n chapter4
kubectl get rs -n chapter4
kubectl get pods -n chapter4
```

You should see:

* 1 Deployment
* 1 ReplicaSet
* 3 Pods

---

### Step 4: Expose the app and access it

Forward the port:

```sh
kubectl port-forward deploy/quarkus-app 5050:8082 -n chapter4
```

Open swagger ui in browser:

```
http://localhost:5050/q/swagger-ui/
```

Confirm the app is running.

---

### Step 5: Roll out new image using Recreate strategy

First, change the deployment strategy to `Recreate` (you’ll only need to do this once):

```sh
kubectl patch deployment quarkus-app \
  -n chapter4 \
  -p '{"spec":{"strategy":{"type":"Recreate"}}}'
```
This updates only the strategy without editing the full YAML.

Then roll out a new version using `kubectl set image`:

```sh
kubectl set image deployment/quarkus-app \
  quarkus=k8sworkout/ch04:lab6v2 \
  -n chapter4
```

Breakdown:

* `quarkus` is the container name (matches `name:` in the YAML under `containers`)
* `k8sworkout/ch04:lab6v2` is the new image

Check rollout status:

```sh
kubectl rollout status deployment/quarkus-app -n chapter4
```

Watch pods during update:

```sh
kubectl get pods -n chapter4 -w
```

You’ll see all old pods stop, then new pods start (since it's a Recreate strategy).

---

Would you like me to update the full beginner-friendly lab flow with this version too?


### Step 6: Scale down to 2 replicas

```sh
kubectl scale deployment quarkus-app --replicas=2 -n chapter4
```

Check:

```sh
kubectl get pods -n chapter4
```

---

### Step 7: See application logs

List pods:

```sh
kubectl get pods -n chapter4
```

Pick one pod:

```sh
kubectl logs <pod-name> -n chapter4
```

Or stream logs:

```sh
kubectl logs -f <pod-name> -n chapter4
```

---
