pipeline {
    agent any
    tools {
        maven 'maven_3_5_3'
        jdk 'jdk8'

    }
    environment {
            HEROKU_API_KEY = credentials('heroku_api_key')
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

         stage('Package') {
             steps {
                sh 'mvn -Pheroku -Dmaven.test.skip=true package'
             }
         }

          stage('Deploy') {
              steps {
                 sh 'mvn -Pheroku -Dmaven.test.skip=true deploy'
              }
          }

         stage('Deploy Backend Heroku') {
            when { anyOf { branch 'master' ; branch 'development' } }
            steps {
                sh 'chmod u+x dplbcnd.sh'
                sh 'sh dplbcnd.sh'
             }
          }

          stage('Deploy Frontend Heroku') {
                      when { anyOf { branch 'master' ; branch 'development' } }
                      steps {
                        sh 'chmod u+x dplfrnt.sh'
                        sh 'sh dplfrnt.sh'
                       }
           }

    }
    post {
        success {
            sh 'curl -s https://codecov.io/bash | bash -s - -t 53635fef-7a13-4dca-960c-87d10788bfcf'
        }

    }
}