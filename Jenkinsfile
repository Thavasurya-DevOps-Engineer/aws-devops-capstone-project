pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    environment {
        IMAGE_NAME = "thavasurya/devops-capstone-app"
        IMAGE_TAG = "v1"
        CONTAINER_NAME = "devops-app"
        APP_PORT = "8081"
    }

    stages {

        stage('Checkout Source') {
            steps {
                checkout scm
            }
        }

        stage('Build Application') {
            steps {
                dir('app') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Run Tests') {
            steps {
                dir('app') {
                    sh 'mvn test'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('app') {
                    sh '''
                        docker build \
                          -f Dockerfile \
                          -t ${IMAGE_NAME}:${IMAGE_TAG} .
                    '''
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

                        docker push ${IMAGE_NAME}:${IMAGE_TAG}

                        docker logout
                    '''
                }
            }
        }

        stage('Deploy Application') {
            steps {
                sh '''
                    echo "Stopping old container..."

                    docker rm -f ${CONTAINER_NAME} || true

                    echo "Starting new container..."

                    docker run -d \
                        --name ${CONTAINER_NAME} \
                        --restart unless-stopped \
                        -p ${APP_PORT}:8081 \
                        ${IMAGE_NAME}:${IMAGE_TAG}
                '''
            }
        }

        stage('Health Check') {
            steps {
                sh '''
                    echo "Waiting for application to start..."

                    sleep 20

                    curl -f http://localhost:${APP_PORT}/actuator/health
                '''
            }
        }
    }

    post {

        success {
            echo '==========================================='
            echo 'CI/CD Pipeline completed successfully!'
            echo 'Application deployed successfully.'
            echo '==========================================='
        }

        failure {
            echo '==========================================='
            echo 'Pipeline failed!'
            echo 'Check the Console Output for details.'
            echo '==========================================='
        }

        always {
            cleanWs()
        }
    }
}