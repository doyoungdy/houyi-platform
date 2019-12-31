## nacos startup
docker run --name saas-nacos -d -e PREFER_HOST_MODE=hostname -e MODE=standalone -p 8848:8848 nacos/nacos-server:1.0.0-RC3

## redis startup
docker run --name saas-redis -d -p 6379:6379 redis

## mysql startup
docker run --name saas-mysql -d -v E:/containers/database/conf:/etc/mysql/mysql.conf.d -v E:/containers/database/data:/var/lib/mysql -p 3306:3306  -e MYSQL_ROOT_PASSWORD=password -e lower_case_table_name=1 mysql:latest

## sentinel-dashboard
docker run --name saas-sentinel-dashboard-141 -d --restart=always --link saas-nacos-1:nacos-server -p 8080:8080 10.10.108.114:5000/microservices-platform/sentinel-dashboard:1.4.1

## ############################## ##

## uaa
docker run --name saas-uaa -p 8000:8000 --link saas-nacos-1:saas-nacos --link saas-mysql:saas-mysql --link saas-redis:saas-redis 10.10.108.114:5000/microservices-platform/uaa:1.4.0

## gateway
docker run --name saas-gateway -p 9900:9900 --link saas-sentinel-dashboard-141:saas-sentinel-dashboard --link saas-nacos-1:saas-nacos --link saas-mysql:saas-mysql --link saas-redis:saas-redis 10.10.108.114:5000/microservices-platform/zuul-gateway:1.4.1

## back-web 
docker run --name saas-back-web -p 8066:8066 --link saas-nacos-1:saas-nacos --link saas-mysql:saas-mysql --link saas-redis:saas-redis 10.10.108.114:5000/microservices-platform/back-web:1.4.0

## user-center
docker run --name saas-user-center -p 7000:7000 --link saas-nacos-1:saas-nacos --link saas-mysql:saas-mysql --link saas-redis:saas-redis 10.10.108.114:5000/microservices-platform/user-center:1.4.0

docker start saas-nacos saas-redis saas-mysql saas-uaa  saas-user-center saas-back-web saas-gateway 