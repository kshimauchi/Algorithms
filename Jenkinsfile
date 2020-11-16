pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                /* `make check` returns non-zero on test failures,
                * using `true` to allow the Pipeline to continue nonetheless
                will develope this later
                */
                echo 'building the application...'
                sh 'make check || true'

                junit '**/target/*.xml'
            }
        }
        stage('test'){
            steps{
                echo 'testing the application...'
            }
        }
        stage('deploy'){
            steps{
                echo 'building the application...'
            }
        }kol
    }
}
