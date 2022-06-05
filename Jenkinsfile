pipeline {
    agent any

    tools {
        maven "MAVEN"
    }

    environment {
        SUITE = "src/test/resources/${params.Suite}.xml"
    }

    stages {
        stage('Build') {
            steps {
                git branch: 'master', url: 'https://github.com/vitaliiyz/Automation_Project_Vitaly_Zaitsev.git'
                bat 'mvn clean'
            }
        }
        stage('Test run') {
            steps {
                echo "--------------------------------------Started ${env.SUITE}--------------------------------------"
                bat 'mvn clean test -Dsuite=${env.SUITE}'
            }
        }
        stage('Reports') {
            steps {
                script {
                    allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}