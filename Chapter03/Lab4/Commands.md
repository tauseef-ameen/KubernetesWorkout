# Lab 4 - Running a Quarkus Application in Kubernetes Pod

This document lists all the commands used in **Lab 4**, along with a brief explanation of what each command does.

---

### Command 1 - Pull the Docker Image
```bash
docker pull k8sworkout/ch03:lab4
```
> Pulls the `lab4` Docker image (`k8sworkout/ch03:lab4`) from the Docker registry onto your local machine.

---

### Command 2 - Generate Deployment YAML
```bash
kubectl run lab4 --image=k8sworkout/ch03:lab4 -n chapter3 --dry-run=client -o yaml > lab4.yaml
```
> Creates a YAML manifest (`lab4.yaml`) for the `lab4` pod without actually creating the pod.

---

### Command 3 - Create the Pod from YAML
```bash
kubectl create -f lab4.yaml
```
> Deploys the pod by applying the `lab4.yaml` file.

---

### Command 4 - Get List of Pods
```bash
kubectl get pods -n chapter3
```
> Lists all pods in the `chapter3` namespace to verify if the `lab4` pod is running.

---

### Command 5 - Describe the Pod
```bash
kubectl describe pod lab4 -n chapter3
```
> Provides detailed information about the `lab4` pod, including status, events, and resource usage.

---

### Command 6 - View Logs of a Specific Container
```bash
kubectl logs -c lab4container lab4 -n chapter3
```
> Displays logs specifically from the container named `lab4container` inside the `lab4` pod.

---

### Command 7 - Port Forward to Access Application
```bash
kubectl port-forward --address localhost pod/lab4 5005:8082 -n chapter3
```
> Forwards port `8082` from the `lab4` pod to your local machine's `5005` port, allowing you to access the application locally.

---

### Command 8 - Verify Swagger UI in Browser
Open your browser and navigate to:
```
http://localhost:5005/q/swagger-ui/
```
> Verifies that the Swagger UI is accessible and the application's API documentation is available.

---

### Command 9 - Test Application Endpoint
Open your browser or use a tool like `curl` to access:
```
http://localhost:5005/state/22
```
> Calls the `/state/22` API endpoint to verify that the application is responding correctly.

---

### Notes:
- Always ensure you are using the correct namespace (`chapter3`) for all operations.
- If the pod is not running, use the `kubectl describe` output to troubleshoot issues.
- Port forwarding is useful for testing the pod’s service without exposing it externally.
- Swagger UI (`/q/swagger-ui/`) provides a visual way to explore and test APIs.
- Application endpoints like `/state/22` should respond with state of conference room.

---