node {
    // 환경 설정 단계
    stage('Checkout SCM') {
        // 작업 디렉토리 청소
        deleteDir()
        
        // 소스 코드 체크아웃
        checkout scm
    }

    // Docker 빌드 단계
    stage('Docker Build') {
        // Docker 이미지 빌드
        sh 'docker build -t 110.15.58.113:8081/repository/bootgenie:latest .'
    }

    // Docker 푸시 단계
    stage('Docker Push') {
        withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
            sh 'docker login -u $NEXUS_USERNAME -p $NEXUS_PASSWORD 110.15.58.113:8081'
            sh 'docker push 110.15.58.113:8081/repository/bootgenie:latest'
        }
    }

    // 버전 태그 업 단계
    stage('Tag Version Up') {
        script {
            def version = sh(script: "git describe --tags --abbrev=0", returnStdout: true).trim()
            def (major, minor, patch) = version.tokenize('.').collect { it.toInteger() }
            patch += 1
            def newVersion = "${major}.${minor}.${patch}"
            sh "git tag -a ${newVersion} -m 'Version ${newVersion}'"
            withCredentials([usernamePassword(credentialsId: 'github-credentials', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
                sh """
                    git config user.name '${GIT_USERNAME}'
                    git config user.email 'donghyun4591@gmail.com'
                    git push origin ${newVersion}
                """
            }
        }
    }
}
