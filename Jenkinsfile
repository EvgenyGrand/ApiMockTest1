timeout(5) {
    node("windows-agent") {
        echo "Download project"

        checkout scm: [
            $class: 'GitSCM',
            branches: [[name: 'master']],
            userRemoteConfigs: [[
                credentialsId: 'a9f805d2-c15f-4156-9f9a-b452f6dfad29',
                url: 'git@github.com:EvgenyGrand/ApiMockTest1.git'
            ]]
        ]

        try {
            // Выполнение команды Maven для запуска тестов
            sh '''
                mvn clean test
            '''
        } finally {
            // Архивирование артефактов
            archiveArtifacts(artifacts: "target/surefire-reports/*.xml")

            // Генерация и отображение отчета Allure
            allure([
                includeProperties: true,
                jdk              : '',
                properties       : [],
                reportBuildPolicy: 'ALWAYS',
                results          : [[path: 'target/allure-results']]
            ])
        }
    }
}