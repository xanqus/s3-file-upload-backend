pipeline {
    agent any

    stages {
        stage("Pull Codes from Github") {
            steps{
                checkout scm
            }
        }
        stage("Connect test") {
            steps{
                sh """
                echo connect test
                """
            }
        }
        stage("Build Codes by Gradle") {
            steps{
                sh """
                chmod +x gradlew
                ./gradlew clean build
                """
            }
        }
        stage("Build Docker image") {
            steps {
                sh """
                docker build -t sbs-community-backend:latest .
                """
            }
        }
        stage("Docker run") {
            steps {
                sh 'docker ps -f sbs-community-backend -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -f sbs-community-backend -q | xargs -r docker container rm -f'
                sh 'docker images --no-trunc --all --quiet --filter="dangling=true" | xargs --no-run-if-empty docker rmi -f'
                sh 'docker run -d --name sbs-community-backend-dev -v /home/ubuntu/.aws:/root/.aws -p 8182:8089 --net sbs-community-net --restart unless-stopped sbs-community-backend:latest'
            }
        }
    }
}