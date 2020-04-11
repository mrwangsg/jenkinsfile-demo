pipeline {
    agent any
    stages {
        stage('env') {
            steps {
                sh 'source /etc/profile'
            }
        }
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
