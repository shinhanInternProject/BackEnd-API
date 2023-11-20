pipeline {
    agent any

    environment {
        // 환경 변수 설정
        DOCKERHUB_CREDENTIALS_ID = 'dockerHub'
        DOCKERHUB_USERNAME = 'heebin00'
        BACKEND_IMAGE_TAG = 'v1.0' // 백엔드 이미지 태그
    }

    stages {
        stage('Checkout Backend') {
            steps {
                // GitHub 저장소에서 백엔드 소스 코드 체크아웃
                git branch: 'deployTest', url: 'https://github.com/shinhanInternProject/BackEnd-API.git'
            }
        }

        stage('Prepare') {
        steps {
            sh 'chmod +x ./gradlew'
            }
        }

            stage('Build') {
            steps {
                sh './gradlew build'
            }
        }



        stage('Build & Push Backend Docker Image') {
            steps {
                script {
                    // Docker Hub에 로그인
                    withCredentials([usernamePassword(credentialsId: "${env.DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASS')]) {
                        sh 'echo $DOCKERHUB_PASS | docker login -u $DOCKERHUB_USER --password-stdin'
                    }
                    // 백엔드 이미지 빌드 및 푸시
                    sh 'docker build -t $DOCKERHUB_USERNAME/spring:$BACKEND_IMAGE_TAG ./'
                    sh 'docker push $DOCKERHUB_USERNAME/spring:$BACKEND_IMAGE_TAG'
                }
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
        }
        failure {
            echo '이 작업은 빌드가 실패하면 실행됩니다.'
        }
    }
}
