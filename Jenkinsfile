#!groovy​

@Library('jenkins-shared-library')
def pom

pipeline {
    agent {
        label 'master'
    }

    tools {
        maven 'jenkins-maven-installation-3.5.4'
    }

    stages {
        stage('Build') {
            steps {
                timestamps {
                    script {
                        sh 'mvn clean install'
                    }
                }
            }
        }
    }
    post {
        failure {
            script {
                echo "FAILURE"
            }
        }
    }
}