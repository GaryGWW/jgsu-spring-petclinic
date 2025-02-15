pipeline {
    agent any

    stages {
        stage('Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/GaryGWW/jgsu-spring-petclinic.git'    
            }
        }
        
        stage('Build') {
            steps {
                powershell './mvnw clean package'
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                always {
                    junit '*target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}