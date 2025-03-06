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
        // stage('Deploy and Service to Kubernetes') {
        //     steps {
        //         script {
        //                 // Kubernetes Deployment and Service 생성 및 적용 (1일차 교육때 사용한 deploy & service 생성 yaml 파일 등록하여 사용)
        //                 // sh "kubectl apply -f ./yaml/demo-app.yaml -n ${NAMESPACE}"
        //                 sh "kubectl apply -f ./yaml/app.yaml -n ${NAMESPACE}"
        //                 // sh "kubectl rollout restart deployment/<deployment-name>"
        //                 // image tag 변경 없이 적용하려면 pod 재시작
        //                 sh "kubectl rollout restart deployment/springboot-cha -n ${NAMESPACE}"
        //         }
        //     }
        // }
        // stage('Deployment Image to Update') {
        //     steps {
        //         script {
        //             // Kubenetes에서 특정 Deployment의 컨테이너 이미지를 업데이트 (아래 이름은 중복되지 않게 주의하여 지정, deployment, selector 이름으로)
        //             sh "kubectl set image deployment/springboot-cha springboot-cha=${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${IMAGE_TAG} --namespace=${NAMESPACE}"
        //         }
        //     }
        // }
    }
}
