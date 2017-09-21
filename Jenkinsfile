pipeline {
     
    agent any

    stages {
        stage('Build') {
            steps {
                //run your build
                sh 'mvn clean install'
            }
            post {
                always {
                    //generate cucumber reports
                    cucumber '**/*.json'
                }
            }
        }
    }
}
