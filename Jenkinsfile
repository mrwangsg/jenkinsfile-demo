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
                sh 'mvn --settings /usr/local/apache-maven-3.6.3/conf/settings.xml install -Dmaven.test.skip=true'
            }
        }
        stage('test') {
            steps {
                sh 'echo "change test"'
            }
        }
    }
}
