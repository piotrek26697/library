FROM airhacks/glassfish
COPY ./target/library.war ${DEPLOYMENT_DIR}
