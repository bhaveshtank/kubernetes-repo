# kubernetesRepo
==============================
Docker Images
I have used httpd image from the Docker Hub for this test.
* All the docker images are pushed to gcr repository



##For deploying on kubernetes,
I have used GKE(Google Kubernetes Enginer) for kubernetes
I have created 3 different images.
Basically Why I have created 3 images instead of 1
* Each image has html page which says on environment it is depoloyed
    * dev.html denotes its a dev environment
Similarly I have done for qa and dev

* All of the containers are deployed using Kubernetes deployment
* These deployements are attached to service Objects which uses selectors to manage specific pods
* The Service object is exposed using loadbalaner and ingress with port 80


#Build and Deployment
Jenkins is used as the orchestrator
* Please find the Jenkinsfile for the further reference.


