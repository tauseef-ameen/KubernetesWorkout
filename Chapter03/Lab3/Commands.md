# Lab 3 - Step-by-Step Command Guide

This document lists all the commands used in **Lab 3** along with a brief explanation of what each command does.

---

#### Step 1 - Create a Namespace
```bash
kubectl create namespace chapter3
```
> Creates a new namespace called `chapter3` to isolate the resources for this lab.

---

#### Step 2 - Tag and Push the Docker Image
```bash
docker tag k8sworkout/ch02:lab1 k8sworkout/ch03:lab3
docker push k8sworkout/ch03:lab3
```
> Tags the existing Docker image (`ch02:lab1`) as a new image (`ch03:lab3`) and pushes it to the Docker registry.
> **Note:**  
> In Chapter 2 Lab 1, two images were created — one based on **JDK** and another based on **JRE**.  
> **Make sure you are tagging and pushing the JRE version of the image** for this lab.

---

#### Step 3 - Generate Deployment YAML
```bash
kubectl run lab3 --image=k8sworkout/ch03:lab3 -n chapter3 --dry-run=client -o yaml > lab3.yaml
```
> Creates a YAML manifest (`lab3.yaml`) for the `lab3` pod based on the image, without actually creating it yet.

---

### Step 4 - Create the Pod from YAML
```bash
kubectl create -f lab3.yaml
```
> Deploys the pod by applying the `lab3.yaml` file.

---

### Step 5 - Get List of Pods
```bash
kubectl get pods -n chapter3
```
> Lists all pods in the current namespace (default unless otherwise set).

---

### Step 6 - Describe the Pod
```bash
kubectl describe pod lab3 -n chapter3
```
> Provides detailed information about the `lab3` pod, including events, container states, and resource usage.

---

### Step 7 - Port Forward to Access Application
```bash
kubectl port-forward --address localhost pod/lab3 5000:8081 -n chapter3
```
> Forwards port `8081` from the `lab3` pod to your local machine's `5000` port, making it accessible locally.

---

### Step 8 - View Pod Logs
```bash
kubectl -n chapter3 logs lab3
```
> Fetches and displays logs from the `lab3` pod.

---

### Step 9 - View Logs of a Specific Container
```bash
kubectl logs -c lab3container lab3 -n chapter3
```
> Displays logs specifically from the container named `lab3container` inside the `lab3` pod.

---

### Step 10 - Follow Logs in Real Time
```bash
kubectl -n chapter3 logs lab3 --follow
```
> Continuously streams (`--follow`) logs from the `lab3` pod.

---

### Step 11 - View Logs Based on Labels
```bash
kubectl -n chapter3 logs -l run=lab3
```
> Fetches logs from pods that match the label `run=lab3` in the `chapter3` namespace.

---

### Step 12 - Execute a Bash Shell in the Pod
```bash
kubectl -n chapter3 exec -it lab3 -- bash
```
> Opens an interactive bash shell inside the running `lab3` pod.

---

### Step 13 -  Copy a File into the Pod
```bash
kubectl -n chapter3 cp index.html lab3:/opt/kubernetesWorkoutJRE/classes/BOOT-INF/classes/static/index.html
```
> Copies your local index.html file into the pod's file system, replacing or updating the existing file inside the container..

---

### Step 14 - Read a File from the Pod
```bash
kubectl -n chapter3 exec -it lab3 -- cat /opt/kubernetesWorkoutJRE/classes/BOOT-INF/classes/static/index.html
```
> Executes a `cat` command inside the `lab3` pod to display the content of `index.html` file.

---

## Notes:
- Make sure Docker image is pushed correctly before deploying.
- Ensure the correct namespace is being used when accessing pods and logs (`-n chapter3`).
- Port forwarding allows you to access internal pod services locally.
- Using `--follow` helps monitor logs live as the application runs.

---