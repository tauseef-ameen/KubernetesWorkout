## Lab 3 - Running Java Spring Boot Application in a Kubernetes Pod

This document lists all the commands used in **Lab 3**.
Each listing shows a general version first, followed by the lab-specific command.

---

### Listing 3.1 - Create a Namespace

```bash
kubectl create namespace <name-of-namespace>
kubectl create namespace chapter3
```

> Creates a namespace for isolating lab resources.

---

### Listing 3.1- List All Namespaces

```bash
kubectl get namespaces
kubectl get namespace
```

> Lists all namespaces in the cluster.

---

### Listing 3.2 - Tag and Push the Docker Image

```bash
docker tag <source-image> <target-image>
docker tag k8sworkout/ch02:lab1 k8sworkout/ch03:lab3

docker push <target-image>
docker push k8sworkout/ch03:lab3
```

> Tags and pushes the correct Docker image (`JRE` version) to be used in the pod.

---

### Listing 3.3 - Generate Pod Manifest (YAML)

```bash
kubectl run <pod-name> --image=<image> -n <namespace> --dry-run=client -o yaml > <filename>.yaml
kubectl run lab3 --image=k8sworkout/ch03:lab3 -n chapter3 --dry-run=client -o yaml > lab3.yaml
```

> Generates a YAML manifest for a pod without applying it.

---

### Listing 3.5 - Create the Pod from YAML

```bash
kubectl create -f <filename>.yaml
kubectl create -f lab3.yaml
```

> Applies the manifest to create the pod.

---

### Listing 3.5- Get List of Pods

```bash
kubectl get pods -n <namespace>
kubectl get pods -n chapter3
```

> Lists pods in the specified namespace.

---

### Listing 3.6 - Describe the Pod

```bash
kubectl describe pod <pod-name> -n <namespace>
kubectl describe pod lab3 -n chapter3
```

> Shows detailed information about the pod.

---

### Listing 3.7 - Port Forward to Access Application

```bash
kubectl port-forward --address localhost pod/<pod-name> <local-port>:<container-port> -n <namespace>
kubectl port-forward --address localhost pod/lab3 5000:8081 -n chapter3
```

> Maps a container port to your local machine for access.

---

### Listing 3.9 - View Pod Logs

```bash
kubectl logs <pod-name> -n <namespace>
kubectl -n chapter3 logs lab3
```

> Shows logs from the specified pod.

---

### Listing 3.9- View Logs of a Specific Container

```bash
kubectl logs -c <container-name> <pod-name> -n <namespace>
kubectl logs -c lab3container lab3 -n chapter3
```

> Displays logs from a specific container within the pod.

---

### Listing 3.9- Follow Logs in Real Time

```bash
kubectl logs <pod-name> -n <namespace> --follow
kubectl -n chapter3 logs lab3 --follow
```

> Streams logs continuously from the pod.

---

### Listing 3.9- View Logs Based on Labels

```bash
kubectl logs -l <label-selector> -n <namespace>
kubectl -n chapter3 logs -l run=lab3
```

> Fetches logs from pods matching a label.

---

### Listing 3.10 - Execute a Bash Shell in the Pod

```bash
kubectl exec -it <pod-name> -n <namespace> -- bash
kubectl -n chapter3 exec -it lab3 -- bash
```

> Opens an interactive shell inside the pod.

---

### Listing 3.11 - Copy a File into the Pod

```bash
kubectl cp <local-path> <namespace>/<pod-name>:<destination-path>
kubectl -n chapter3 cp index.html lab3:/opt/kubernetesWorkoutJRE/classes/BOOT-INF/classes/static/index.html
```

> Copies a file into the pod's file system.

---

### Listing 3.11- Read a File from the Pod

```bash
kubectl exec -it <pod-name> -n <namespace> -- cat <file-path>
kubectl -n chapter3 exec -it lab3 -- cat /opt/kubernetesWorkoutJRE/classes/BOOT-INF/classes/static/index.html
```

> Reads and displays the content of a file inside the pod.

---

### Notes

* Tag and push the correct **JRE-based** image before running the pod.
* Use the namespace `chapter3` for all pod-related commands.
* Use `--follow` to monitor logs in real time.
* Port-forwarding helps access the service locally without exposing it.

---
