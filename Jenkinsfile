pipeline {
    agent any
    tools {
        maven 'maven_3_5_3'
        jdk 'jdk8'

    }
    stages {

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {

                sh 'mvn clean test'
             }
         }

        stage('Test Coverage') {
            steps {
                sh 'mvn cobertura:cobertura'
             }
         }

         stage('Deploy') {
            when { branch 'master'}
            steps {
                sh 'mvn -Dmaven.test.skip=true deploy'
             }
          }
    }

    post {
        success {
            sh 'curl -s https://codecov.io/bash | bash -s - -t 53635fef-7a13-4dca-960c-87d10788bfcf'
        }

    }

}
