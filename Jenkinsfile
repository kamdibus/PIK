pipeline {
    agent any
    tools {
        maven 'maven_3_5_3'
        jdk 'jdk8'

    }

         stage('Deploy frontend') {
            when { anyOf { branch 'master' ; branch 'frontend' } }
            steps {
                sh './dplfrnt.sh'
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