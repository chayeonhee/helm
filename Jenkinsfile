pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/chayeonhee/helm.git'
        HELM_CHART_REPO = 'https://github.com/chayeonhee/helmchart.git'
        HELM_CHART_DIR = './hellochart'  // git 내의 Helm 차트 디렉토리
        HELM_REPO_NAME = 'team6-cha-repo'
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

        stage('Checkout Helm Chart') {
            steps {
                // Helm chart 리포지토리에서 Helm 차트를 클론
                git url: HELM_CHART_REPO, branch: 'main'
            }
        }
         

         stage('Package Helm Chart') {
            steps {
                script {
                    // Helm 차트 패키징
                    sh "helm package ${HELM_CHART_DIR} --destination ./helm-packages"
                }
            }
        }
        
         stage('Commit and Push Packaged Chart to Git') {
            steps {
                script {
                    // Git 저장소에 Helm 패키지 파일을 커밋하고 푸시
                    sh 'git config --global user.email "yeonhee1021@gmail.com"'
                    sh 'git config --global user.name "chayeonhee"'
                    sh """
                        # Git 상태 확인
                        git status
                        
                        # Helm 패키지 파일을 Git에 추가
                        git add helm-packages/*.tgz
                        
                        # 커밋 메시지 작성
                        git commit -m "package"
                        
                        # 변경 사항을 원격 저장소에 푸시
                        git push origin main
                    """
                }
            }
        }
         stage('Deploy to Kubernetes (Helm)') {
            // when {
            //     // src 디렉토리가 변경된 경우에만 실행
            //     changeset "**/src/**"
            // }
            steps {
                script {
                    // Helm 차트를 사용하여 Kubernetes에 배포
                    // sh "helm upgrade --install hello-world ./helm-packages/springboot-hello-world-0.1.0.tgz --set image.repository=${DOCKER_REGISTRY}/${DOCKER_IMAGE} --set image.tag=${IMAGE_TAG} --values https://raw.githubusercontent.com/chayeonhee/helm/main/charts/values.yaml"
                    sh "helm upgrade --install hello-world ./helm-packages/springboot-hello-world-0.1.0.tgz --set image.repository=${DOCKER_REGISTRY}/${DOCKER_IMAGE} --set image.tag=${IMAGE_TAG} "
                }
            }
        }
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
