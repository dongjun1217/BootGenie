# 공식 Python 이미지를 베이스 이미지로 사용
FROM python:3.9-slim

# 컨테이너의 작업 디렉토리를 /app으로 설정
WORKDIR /app

# 현재 디렉토리의 내용을 컨테이너의 /app에 복사
COPY . /app

# requirements.txt에 명시된 필요한 패키지를 설치
RUN pip install --no-cache-dir -r requirements.txt

# 이 컨테이너 외부에서 접근할 수 있도록 포트 80을 개방
EXPOSE 80

# 환경 변수를 설정
ENV NAME World

# 컨테이너가 실행될 때 app.py를 실행
CMD ["python", "app.py"]
