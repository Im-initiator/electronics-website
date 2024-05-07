all:	build backend

build:
	docker build -t springboot:latest -f Dockerfile .

backend:
	docker run --rm -it --name springboot-container -p 8080:8080 --network backend-network springboot:latest
.PHONY: build backend