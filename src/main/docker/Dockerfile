FROM java:8
VOLUME /tmp
ADD home-0.0.1-SNAPSHOT.jar home.jar
RUN bash -c 'touch /home.jar'
EXPOSE 8085
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/home.jar" ]