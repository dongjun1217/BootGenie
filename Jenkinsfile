node {
    // Checkout SCM 단계: 소스 코드를 SCM에서 체크아웃합니다.
    stage('Checkout SCM') {
        deleteDir() // 작업 디렉토리를 삭제하여 깨끗한 상태로 시작합니다.
        checkout scm // 소스 코드를 체크아웃합니다.
    }

    // Docker Build 단계: Docker 이미지를 빌드합니다.
    stage('Docker Build') {
        sh 'docker build -t 110.15.58.113:8081/repository/bootgenie:latest .' // Docker 이미지를 Nexus 레지스트리에 태그를 붙여 빌드합니다.
    }

    // Docker Push 단계: 빌드된 Docker 이미지를 Nexus 레지스트리에 푸시합니다.
    stage('Docker Push') {
        withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
            sh 'docker login -u $NEXUS_USERNAME -p $NEXUS_PASSWORD http://110.15.58.113:8081/#browse/browse:bootgenie-docker' // Nexus 레지스트리에 로그인합니다.
            sh 'docker push 110.15.58.113:8081/repository/bootgenie:latest' // 빌드된 Docker 이미지를 Nexus 레지스트리에 푸시합니다.
        }
    }

    // Tag Version Up 단계: Git 태그를 증가시키고 푸시합니다.
    stage('Tag Version Up') {
        script {
            // 최신 태그를 가져와 버전을 증가시킵니다.
            def version = sh(script: "git describe --tags --abbrev=0", returnStdout: true).trim()
            def (major, minor, patch) = version.tokenize('.').collect { it.toInteger() }
            patch += 1
            def newVersion = "${major}.${minor}.${patch}"
            
            // 새로운 태그를 생성하고 푸시합니다.
            sh "git tag -a ${newVersion} -m 'Version ${newVersion}'"
            withCredentials([usernamePassword(credentialsId: 'github-credentials', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
                sh """
                    git config user.name '${GIT_USERNAME}' // Git 사용자 이름 설정
                    git config user.email 'your-github-email@example.com' // Git 사용자 이메일 설정
                    git push origin ${newVersion} // 새로운 태그를 원격 저장소에 푸시
                """
            }
        }
    }
}
