pipeline {
    agent { docker 'maven:3.3.3' }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('package') {
            steps {
                sh 'java -version'
            }
        }
        stage('test') {
            steps {
                sh 'echo "change test"'
            }
        }
    }
}
