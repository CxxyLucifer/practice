# shop

框架使用 spring-boot + spring-data-jpa 构建，数据库使用的mysql8.0

1、使用 Hibernate Validator 对请求实体进行验证，结合web前端项目处理验证信息

2、集成druid数据源，配置master、salve实现读写分离 数据库监控，访问地址：http://127.0.0.1:8080/druid/index.html

3、集成swagger2构建RESTful API ,访问地址：http://127.0.0.1:8080/swagger-ui.html

4、集成Redis缓存
