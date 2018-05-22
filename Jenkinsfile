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

         stage('Deploy frontend') {
            when { anyOf { branch 'master' ; branch 'frontend' } }
            steps {
                sh 'chmod u+x dplfrnt.sh'
                sh dplfrnt.sh
             }
          }
    }
/*
    post {
        success {
            sh 'curl -s https://codecov.io/bash | bash -s - -t token'
        }

    }
*/
}