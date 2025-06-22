## Kubernetes Workout — Hands-on Kubernetes for Application Developers

Welcome to the official GitHub repository for the book **Kubernetes Workout**.

This book is designed for developers who want to **learn Kubernetes by doing** — not by reading abstract theory or working with outdated sample apps. Whether you're building microservices in **Java**, **Quarkus**, or any other modern framework, this book shows you how to deploy, manage, and troubleshoot real applications in Kubernetes.

---

### Who This Book Is For

This book is for you if you’re a **front-end**, **back-end**, or **full-stack developer**. It teaches Kubernetes from scratch. No prior experience required.     
While the examples use **Java, Spring Boot, and Quarkus**, the concepts are explained in a way that any developer can follow, regardless of language or framework. You’ll learn how to deploy and manage real applications in Kubernetes, step by step.

This book is a good fit if you’re:

- An **Application** developer curious about running your apps in Kubernetes
- A **DevOps beginner** looking to bridge the gap between development and operations
- A **Test Automation engineer** who wants to troubleshoot test environments in Kubernetes
- A **Product Owner** or **Engineering Manager** trying to understand Kubernetes' role in the delivery pipeline

---

### What You’ll Learn

You’ll learn how to:

- Containerize Spring Boot and Quarkus applications
- Run apps as Pods and scale them using Deployments
- Use Services to expose your app
- Store configuration in ConfigMaps and Secrets
- Manage logs, inspect pods, and debug problems
- Use Volumes for app data
- Schedule Jobs and CronJobs
- Use Helm for packaging your apps
- Build event-driven apps with Kafka and MongoDB

---

### Folder Structure

Each lab in the book has a matching folder in this repo.  
The structure mirrors the chapters for easy navigation.

```
.
├── Chapter02/
│   └── Docker image creation and container basics
├── Chapter03/
│   ├── lab3/   ← Running pods in Kubernetes (lab3.yaml)
│   └── lab4/   ← Reader exercise solutions
├── Chapter04/
│   ├── lab5/   ← Deployment, scaling, management
│   └── lab6/   ← Exercise for readers with solutions
├── Chapter05/
│   └── ConfigMaps and configuration management
````

Each folder contains:

- **Kubernetes manifests** used in the chapter
- **Code samples** (where applicable)
- **Solutions** to book exercises

---

### Getting Started

Clone the repository:

```bash
git clone https://github.com/tauseef-ameen/KubernetesWorkout.git
cd kubernetes-workout/<chapter-name>
````

Suppose, you are working with Chapter03 then
```bash
cd kubernetes-workout/Chapter03
````

Start with the folder for **Chapter 2**, which helps you containerize the app.
Then follow along chapter by chapter.

---

### Labs You Can Actually Run

All labs are built around **real microservices** — not dummy NGINX or BusyBox pods.
You’ll run, scale, update, and debug applications just like you would in a real job.

Each chapter mixes **learning + doing**:
You’ll read about a concept, see it explained visually, then run it using the exact steps shown.

---

### Book Purchase

Find the book on [Manning Publications](https://www.manning.com) or other major online retailers.
Includes diagrams, background explanations, and exercises tied to this repo.

---

### Contributing or Feedback

Spotted a bug in the code or something that didn’t work for your setup?
Feel free to open an issue or pull request. You can also share your feedback to improve future editions.

---

Happy learning — and enjoy your Kubernetes workout!

```
