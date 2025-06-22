# Kubernetes Workout - Chapter 4

This GitHub directory is according to **Chapter 4** of the book *Kubernetes Workout*.  
This chapter has two subfolders: `lab5` and `lab6`.

- **`lab5`** contains all the code listings discussed in the book.  
  It includes `lab5_RecreateStrategy.yaml`, which is the final Kubernetes deployment manifest  with recreate strategy.   
  It also includes `lab5_RollingUpdateStrategy.yaml`, which is the final Kubernetes deployment manifest  with rolling update strategy.

- **`lab6`** is an exercise for the reader.  
  It contains solutions to the practice problems described at the end of the chapter.

---

## Folder Structure

```
├── lab5/codelistings
│   ├── lab5_RecreateStrategy.yaml
│   ├── lab5_RollingUpdateStrategy.yaml
│   ├── Listings.md
│   └── other code listings...
├── lab6/
│   ├── Commands.md
│   ├── lab6-quarkus-deployment.yaml
│   └── ...

````

---


## Notes

* All manifests are tested with `minikube` but should work in any conformant Kubernetes cluster.
* Namespace `chapter4` is used consistently to avoid interference with other labs.
* Use `lab6/` to cross-check your answers for the chapter-end exercises.

---

