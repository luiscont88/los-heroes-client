FROM gradle:7.6.0-jdk8

RUN gradle --version && java -version

WORKDIR /app

# Only copy dependency-related files
COPY build.gradle settings.gradle /app/

# Only download dependencies
# Eat the expected build failure since no source code has been copied yet
RUN gradle clean build --no-daemon > /dev/null 2>&1 || true

# Copy all files
COPY ./ /app/

# Do the actual build
RUN gradle clean build --no-daemon

CMD java -jar build/libs/*.jar