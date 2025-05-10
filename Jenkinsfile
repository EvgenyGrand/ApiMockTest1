pipeline {
    agent { label 'windows-agent' }

    options {
        timeout(time: 1, unit: 'MINUTES')
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

        stage('Generate Allure Report') {
            steps {
                script {
                    // Генерация отчета Allure
                    bat 'mvn allure:report'
                }
            }
        }

        stage('Publish Allure Report') {
            steps {
                // Публикация отчета Allure
                allure([
                    includeProperties: true,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']] // Убедитесь, что путь указан правильно
                ])
            }
        }
    }

    post {
        always {
            // Здесь можно добавить дополнительные действия, например, очистку или уведомления
            echo "Pipeline завершен"
        }
    }
}


