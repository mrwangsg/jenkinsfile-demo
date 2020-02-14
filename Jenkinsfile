pipeline {
    agent { docker 'maven:3.3.3' }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'pwd'
            }
        }
        stage('package') {
            steps {
                sh 'mvn package -Dmaven.test.skip=true'
            }
        }
        stage('test') {
            steps {
                sh 'echo "change test"'
            }
        }
    }
}
