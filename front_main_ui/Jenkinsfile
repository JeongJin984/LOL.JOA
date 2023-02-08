node {
    stage('Clone repository') {
        checkout scm
    }
    stage('Build image') {
        app = docker.build("loljoa/front-main-ui:0.0.1-SNAPSHOT")

    }
    stage('Push image') {
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }
}