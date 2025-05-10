pipeline {
    agent { label 'windows-agent' }

    options {
        timeout(time: 30, unit: 'MINUTES')
    }

    stages {
        stage('Download Project') {
            steps {
                echo "Downloading project"

                // Checkout the code from Git
                checkout scm: [
                    $class: 'GitSCM',
                    branches: [[name: 'refs/heads/master']], // Указание ветки master
                    userRemoteConfigs: [[
                        credentialsId: 'a9f805d2-c15f-4156-9f9a-b452f6dfad29',
                        url: 'https://github.com/EvgenyGrand/ApiMockTest1.git'
                    ]]
                ]
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Выполнение команды Maven для запуска тестов
                    bat 'mvn clean test'
                }
            }
        }
    }
}