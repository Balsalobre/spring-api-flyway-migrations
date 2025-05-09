# Makefile
MVN := $(shell [ -x ./mvnw ] && echo ./mvnw || echo mvn)
DC := docker compose

.PHONY: dev prod test run

dev:
	$(MVN) clean spring-boot:run -Dspring-boot.run.profiles=dev

prod:
	$(MVN) clean spring-boot:run -Dspring-boot.run.profiles=prod

test:
	$(MVN) clean test -Dspring-boot.run.profiles=test

run:
	$(MVN) clean package
	java -jar target/user-account-service.jar --spring.profiles.active=$(PROFILE)

debug:
	@echo "🔧 Starting application in debug mode - port 5005"
	$(MVN) clean spring-boot:run \
		-Dspring-boot.run.profiles=dev \
		-Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"

db-up:
	$(DC) up -d oracle-db

db-down:
	$(DC) down oracle-db

db-full-reset:
	@echo "Stopping and removing oracle-db container"
	$(DC) stop oracle-db
	$(DC) rm -f oracle-db
	@echo "🔄 Removing all volumes"
	sudo rm -rf ./volumes/database
	@echo "Starting oracle-db container"
	mkdir -p ./volumes/database
	@echo "⚙️ Setting permissions for volumes"
	sudo chown -R $$(id -u):$$(id -g) ./volumes/database
	sudo chmod -R 777 ./volumes/database
	@echo "🚀 Starting oracle-db container"
	$(DC) up -d oracle-db
