all:	build mysql

build:
	docker build -t mysqldatabase:latest -f Dockerfile .
mysql:
	docker run --rm -d --name mysql-container -p 3310:3306 -e MYSQL_ROOT_PASSWORD=123456 --network backend-network mysqldatabase:latest

.PHONY: build mysql