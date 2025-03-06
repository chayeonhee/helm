pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/chayeonhee/helm.git'
        HELM_CHART_DIR = './charts/hello-chart'  // git 내의 Helm 차트 디렉토리
        DOCKER_REGISTRY = "k8s-vga-worker1:5000"
        DOCKER_IMAGE = "group1-team6-cha-hello"
        IMAGE_TAG = "latest"
        NAMESPACE = "group1-team6"
        JAVA_HOME = "/tmp/jdk-21.0.5"
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                // Git 저장소에서 소스 코드 및 Helm 차트를 클론
                git branch: 'main', url: GIT_REPO
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Docker 이미지 빌드 및 푸시
                    sh "docker build -t ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${IMAGE_TAG} ."
                    sh "docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${IMAGE_TAG}"
                }
            }
        }

       
    }
}
