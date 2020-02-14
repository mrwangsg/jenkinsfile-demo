pipeline {
    agent { docker 'maven:3.3.3' }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
            }
        }
        stage('package') {
            steps {
                sh 'pwd'
                sh 'ls'
                sh 'mvn clean package -Dmaven.test.skip=true'
            }
        }
        stage('test') {
            steps {
                sh 'echo "change test"'
            }
        }
    }
}
