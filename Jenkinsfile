node {
    stage('Preparation') {
        git 'https://github.com/9lcTPe6/Auto_Sber.git'
        git branch: '${BRANCH}', url: 'https://github.com/9lcTPe6/Auto_Sber.git'
    }
    stage('Build') {
        withMaven {
            bat """mvn clean test -Dcucumber.filter.tags=\"${TAGS}\""""
        }
    }
    stage('Results') {
        allure includeProperties: false, jdk: '', results: [[path: 'target/reports/allure-results']]
    }
}

