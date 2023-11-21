pipeline {
    agent any

    environment {
        // 환경 변수 설정
        DOCKERHUB_CREDENTIALS_ID = 'dockerHub'
        DOCKERHUB_USERNAME = 'heebin00'
    }

    stages {
        stage('Checkout Backend') {
            steps {
                // GitHub 저장소에서 백엔드 소스 코드 체크아웃
                git branch: 'deployTest02', url: 'https://github.com/shinhanInternProject/BackEnd-API.git'
            }
        }

        stage('Deploy Backend to Kubernetes') {
            steps {
                script {
                    // Jenkins 서버에 저장된 kubeconfig 파일의 경로를 설정
                    def kubeconfigPath = '/var/jenkins_home/.kube/config'

                    // KUBECONFIG 환경 변수 설정
                    withEnv(["KUBECONFIG=$kubeconfigPath"]) {
                        // Kubernetes 클러스터에 백엔드 Deployment 적용
                        sh 'kubectl apply -f backend-deployment.yml'
                    }
                }
            }
        }
    }

       post {
           always {
               echo '이 작업은 실행 결과에 상관없이 항상 실행됩니다.'
           }
           success {
               echo '이 작업은 빌드가 성공하면 실행됩니다.'
               slackSend(channel: '#deploy-noti', message: "빌드 성공: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
           }
           failure {
               echo '이 작업은 빌드가 실패하면 실행됩니다.'
               slackSend(channel: '#deploy-noti', message: "빌드 실패: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
           }
       }
}
