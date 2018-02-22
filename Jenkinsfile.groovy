node {
    stage('git-clone'){
      git credentialsId: '[credentailsId]', url: '[repoUrl]'
    }
    stage('build-docker-images'){
      def devImage = docker.build("gcr.io/[PROJECT_NAME]/httpd-dev", "./dev") 
      def qaImage = docker.build("gcr.io/[PROJECT_NAME]/httpd-qa", "./qa") 
      def prodImage = docker.build("gcr.io/[PROJECT_NAME]/httpd-prod", "./prod") 
    
  
    withDockerRegistry([gcr:['PORJECT_NAME'] , url: 'https://gcr.io']){
        devImage.dev()
	qaImage.push()
	prodImage.push()
     }
    }
    stage('deploy'){
           stage('deploy-dev'){  
              sh'''  
                  kubectl apply -f ./dev/httpd-deployment-dev.yml
                  kubectl apply -f ./dev/deployment-service-dev.yml
             '''
            }
            stage('deploy-qa'){
            sh'''  
                kubectl apply -f ./qa/httpd-deployment-qa.yml
                kubectl apply -f ./qa/deployment-service-qa.yml
            '''
            }
            stage('deploy-prod'){
            sh'''  
                kubectl apply -f ./prod/httpd-deployment-prod.yml
                kubectl apply -f ./prod/deployment-service-prod.yml
            '''
           }
        }
    }


