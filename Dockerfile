FROM openjdk:17-alpine

# Install TIMEZONE and clean up
RUN apk --no-cache add tzdata && \
    cp /usr/share/zoneinfo/Asia/Seoul /etc/localtime && \
    echo "Asia/Seoul" > /etc/timezone && \
    apk del tzdata

# Install Korean Fonts and clean up
RUN apk --update add fontconfig && \
    mkdir -p /usr/share/fonts/nanumfont && \
    wget http://cdn.naver.com/naver/NanumFont/fontfiles/NanumFont_TTF_ALL.zip -O /tmp/NanumFont_TTF_ALL.zip && \
    unzip /tmp/NanumFont_TTF_ALL.zip -d /usr/share/fonts/nanumfont && \
    fc-cache -f && \
    rm -rf /var/cache/* /tmp/NanumFont_TTF_ALL.zip

# Set environment variables
ENV LANG=ko_KR.UTF-8 \
    LANGUAGE=ko_KR.UTF-8

# Create and set working directory
RUN mkdir -p /app/boot-genie
WORKDIR /app/boot-genie

# Copy JAR file
ARG JAR_FILE=build/libs/*.jar
COPY $JAR_FILE boot-genie.jar

# Run the application
ENTRYPOINT ["java", "-DprojectName=boot-genie", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "boot-genie.jar"]

# Optionally, run as non-root user for security
# RUN addgroup -S appgroup && adduser -S appuser -G appgroup
# USER appuser

