# Makefile
MVN := $(shell [ -x ./mvnw ] && echo ./mvnw || echo mvn)

.PHONY: dev prod test run

dev:
	$(MVN) clean spring-boot:run -Dspring-boot.run.profiles=dev

prod:
	$(MVN) clean spring-boot:run -Dspring-boot.run.profiles=prod

test:
	$(MVN) clean spring-boot:run -Dspring-boot.run.profiles=test

run:
	$(MVN) clean package
	java -jar target/user-account-service.jar --spring.profiles.active=$(PROFILE)
