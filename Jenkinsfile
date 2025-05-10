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

        stage('Generate Allure Report') {
            steps {
                script {
                    // Генерация отчета Allure
                    bat 'mvn allure:report'
                }
            }
        }

        stage('Serve Allure Report') {
            steps {
                script {
                    // Запуск сервера Allure для отображения отчета
                    // Используйте '&' для запуска в фоновом режиме, если это необходимо
                    bat 'allure serve target/allure-results'
                }
            }
        }
    }

    post {
        always {
            // Сохранение результатов Allure для последующего анализа в Jenkins
            allure([
                results: [[path: 'target/allure-results']]
            ])
        }
    }
}

