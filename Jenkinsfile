pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    environment {
        IMAGE_NAME = "devops-capstone-app"
        IMAGE_TAG  = "v1"
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
                    sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
                }
            }
        }

    }

    post {
        success {
            echo 'Docker image built successfully!'
        }

        failure {
            echo 'Pipeline failed!'
        }

        always {
            cleanWs()
        }
    }
}
