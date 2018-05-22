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
        /*
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
        */
         stage('Package') {
             steps {
                sh 'mvn -Pheroku package'
             }
         }

         stage('Deploy Backend') {
            when { anyOf { branch 'master' ; branch 'heroku' } }
            steps {
                sh 'chmod u+x dplbcnd.sh'
                sh 'pwd'
                sh -x dplbcnd.sh
             }
          }
            /*
          stage('Deploy Frontend') {
                      when { anyOf { branch 'master' ; branch 'frontend' } }
                      steps {
                        sh 'chmod u+x dplfrnt.sh'
                        sh 'sh dplfrnt.sh'
                       }
           }
           */
    }
/*
    post {
        success {
            sh 'curl -s https://codecov.io/bash | bash -s - -t token'
        }

    }
*/
}