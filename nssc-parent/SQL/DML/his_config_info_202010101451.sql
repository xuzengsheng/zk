INSERT INTO nacos_config.his_config_info (id,data_id,group_id,app_name,content,md5,gmt_create,gmt_modified,src_user,src_ip,op_type,tenant_id) VALUES 
(0,'nacos-consumer-dev.yml','DEFAULT_GROUP','','config:
    info: 本配置来自配置中心--dev','dcf3c8f5e9fc0586ef2df164c0bd8f99','2020-09-14 15:50:30.0','2020-09-14 15:50:30.0',NULL,'10.74.130.56','I','')
,(0,'nacos-consumer-test.yml','DEFAULT_GROUP','','config:
    info: 本配置来自配置中心--test','2661476d150f54c250ffc32f16adc03b','2020-09-14 15:50:30.0','2020-09-14 15:50:30.0',NULL,'10.74.130.56','I','')
,(1,'nacos-consumer-dev.yml','DEFAULT_GROUP','','config:
    info: 本配置来自配置中心1--dev','e1f256a7860b23da4b49137486608e4b','2020-09-14 15:55:14.0','2020-09-14 15:55:15.0',NULL,'10.74.130.56','U','')
,(0,'nccs-gateway','GATEWAY_GROUP','','server:
  port: 3000
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nacos-customer
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nacos-customer
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/consumer/**   #匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        - id: nacos-config
          uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
          predicates:
            - Path=/client/**
          filters:
            - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
            - name: RequestRateLimiter  # 局部限流过滤器
              args:
                key-resolver: ''#{@iPKeyResolver}''
                redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
                redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
            - name: RequestSize   #设置文件上传大小
              args:
                maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','06daaeff595b7504b582041f44baf11d','2020-10-10 10:10:18.0','2020-10-10 10:10:19.0',NULL,'0:0:0:0:0:0:0:1','I','')
,(3,'nccs-gateway','GATEWAY_GROUP','','server:
  port: 3000
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nacos-customer
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nacos-customer
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/consumer/**   #匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        - id: nacos-config
          uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
          predicates:
            - Path=/client/**
          filters:
            - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
            - name: RequestRateLimiter  # 局部限流过滤器
              args:
                key-resolver: ''#{@iPKeyResolver}''
                redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
                redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
            - name: RequestSize   #设置文件上传大小
              args:
                maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','06daaeff595b7504b582041f44baf11d','2020-10-10 10:27:22.0','2020-10-10 10:27:23.0',NULL,'0:0:0:0:0:0:0:1','U','')
,(0,'nccs-gateway_dev','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nacos-customer
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nacos-customer
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/consumer/**   #匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        - id: nacos-config
          uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
          predicates:
            - Path=/client/**
          filters:
            - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
            - name: RequestRateLimiter  # 局部限流过滤器
              args:
                key-resolver: ''#{@iPKeyResolver}''
                redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
                redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
            - name: RequestSize   #设置文件上传大小
              args:
                maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','c698058e684bf5fefd815022d930d3d0','2020-10-10 10:31:03.0','2020-10-10 10:31:03.0',NULL,'0:0:0:0:0:0:0:1','I','')
,(5,'nccs-gateway_dev','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nacos-customer
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nacos-customer
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/consumer/**   #匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        - id: nacos-config
          uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
          predicates:
            - Path=/client/**
          filters:
            - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
            - name: RequestRateLimiter  # 局部限流过滤器
              args:
                key-resolver: ''#{@iPKeyResolver}''
                redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
                redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
            - name: RequestSize   #设置文件上传大小
              args:
                maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','c698058e684bf5fefd815022d930d3d0','2020-10-10 10:31:19.0','2020-10-10 10:31:20.0',NULL,'0:0:0:0:0:0:0:1','D','')
,(0,'nccs-gateway-dev.yml','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nacos-customer
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nacos-customer
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/consumer/**   #匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        - id: nacos-config
          uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
          predicates:
            - Path=/client/**
          filters:
            - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
            - name: RequestRateLimiter  # 局部限流过滤器
              args:
                key-resolver: ''#{@iPKeyResolver}''
                redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
                redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
            - name: RequestSize   #设置文件上传大小
              args:
                maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','c698058e684bf5fefd815022d930d3d0','2020-10-10 10:32:12.0','2020-10-10 10:32:13.0',NULL,'0:0:0:0:0:0:0:1','I','')
,(0,'nccs-user-dev','DEFAULT_GROUP','','server:
  port: 9001

spring:
  #数据库
  datasource:
    url: jdbc:mysql://localhost:3306/nccs_user?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver','3c91833c316b4e11e5271055fee7808b','2020-10-10 11:49:18.0','2020-10-10 11:49:19.0',NULL,'0:0:0:0:0:0:0:1','I','')
,(7,'nccs-user-dev','DEFAULT_GROUP','','server:
  port: 9001

spring:
  #数据库
  datasource:
    url: jdbc:mysql://localhost:3306/nccs_user?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver','3c91833c316b4e11e5271055fee7808b','2020-10-10 11:54:57.0','2020-10-10 11:54:58.0',NULL,'0:0:0:0:0:0:0:1','D','')
;
INSERT INTO nacos_config.his_config_info (id,data_id,group_id,app_name,content,md5,gmt_create,gmt_modified,src_user,src_ip,op_type,tenant_id) VALUES 
(0,'nccs-user-dev.yml','DEFAULT_GROUP','','server:
  port: 9001

spring:
  #数据库
  datasource:
    url: jdbc:mysql://localhost:3306/nccs_user?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver','3c91833c316b4e11e5271055fee7808b','2020-10-10 11:55:25.0','2020-10-10 11:55:26.0',NULL,'0:0:0:0:0:0:0:1','I','')
,(1,'nacos-consumer-dev.yml','DEFAULT_GROUP','','config:
    info: 本配置来自配置中心4--dev','7b35ec65cd4163bb8aadf1ab53fbb86f','2020-10-10 13:49:38.0','2020-10-10 13:49:39.0',NULL,'0:0:0:0:0:0:0:1','D','')
,(2,'nacos-consumer-test.yml','DEFAULT_GROUP','','config:
    info: 本配置来自配置中心--test','2661476d150f54c250ffc32f16adc03b','2020-10-10 13:49:38.0','2020-10-10 13:49:39.0',NULL,'0:0:0:0:0:0:0:1','D','')
,(3,'nccs-gateway','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nacos-customer
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nacos-customer
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/consumer/**   #匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        - id: nacos-config
          uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
          predicates:
            - Path=/client/**
          filters:
            - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
            - name: RequestRateLimiter  # 局部限流过滤器
              args:
                key-resolver: ''#{@iPKeyResolver}''
                redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
                redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
            - name: RequestSize   #设置文件上传大小
              args:
                maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','c698058e684bf5fefd815022d930d3d0','2020-10-10 13:49:38.0','2020-10-10 13:49:39.0',NULL,'0:0:0:0:0:0:0:1','D','')
,(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nacos-customer
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nacos-customer
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/consumer/**   #匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        - id: nacos-config
          uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
          predicates:
            - Path=/client/**
          filters:
            - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
            - name: RequestRateLimiter  # 局部限流过滤器
              args:
                key-resolver: ''#{@iPKeyResolver}''
                redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
                redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
            - name: RequestSize   #设置文件上传大小
              args:
                maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','c698058e684bf5fefd815022d930d3d0','2020-10-10 13:51:52.0','2020-10-10 13:51:53.0',NULL,'0:0:0:0:0:0:0:1','U','')
,(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nccs-user
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nccs-user
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/user/**   #匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        - id: nacos-config
          uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
          predicates:
            - Path=/client/**
          filters:
            - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
            - name: RequestRateLimiter  # 局部限流过滤器
              args:
                key-resolver: ''#{@iPKeyResolver}''
                redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
                redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
            - name: RequestSize   #设置文件上传大小
              args:
                maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','8ee6d4d2fe84048dc8db65a0c0b6ec88','2020-10-10 14:01:23.0','2020-10-10 14:01:23.0',NULL,'0:0:0:0:0:0:0:1','U','')
,(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nccs-user
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nccs-user
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/a/**   #匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        - id: nacos-config
          uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
          predicates:
            - Path=/client/**
          filters:
            - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
            - name: RequestRateLimiter  # 局部限流过滤器
              args:
                key-resolver: ''#{@iPKeyResolver}''
                redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
                redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
            - name: RequestSize   #设置文件上传大小
              args:
                maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','176186f94beb36671776045cdef0bd7a','2020-10-10 14:01:46.0','2020-10-10 14:01:46.0',NULL,'0:0:0:0:0:0:0:1','U','')
,(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nccs-user
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nccs-user
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/a/**   #匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        # - id: nacos-config
        #   uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
        #   predicates:
        #     - Path=/client/**
        #   filters:
        #     - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
        #     - name: RequestRateLimiter  # 局部限流过滤器
        #       args:
        #         key-resolver: ''#{@iPKeyResolver}''
        #         redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
        #         redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
        #     - name: RequestSize   #设置文件上传大小
        #       args:
        #         maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','48f61f1dfda01b4fc933768eab976d97','2020-10-10 14:09:25.0','2020-10-10 14:09:25.0',NULL,'0:0:0:0:0:0:0:1','U','')
,(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nccs-user
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nccs-user
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/a/**   
            # 匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        # - id: nacos-config
        #   uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
        #   predicates:
        #     - Path=/client/**
        #   filters:
        #     - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
        #     - name: RequestRateLimiter  # 局部限流过滤器
        #       args:
        #         key-resolver: ''#{@iPKeyResolver}''
        #         redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
        #         redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
        #     - name: RequestSize   #设置文件上传大小
        #       args:
        #         maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','dfaa7ef12ada1673530841a29cdf71bd','2020-10-10 14:18:29.0','2020-10-10 14:18:29.0',NULL,'0:0:0:0:0:0:0:1','U','')
,(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nccs-user
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nccs-user
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/a/**
            # 匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        # - id: nacos-config
        #   uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
        #   predicates:
        #     - Path=/client/**
        #   filters:
        #     - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
        #     - name: RequestRateLimiter  # 局部限流过滤器
        #       args:
        #         key-resolver: ''#{@iPKeyResolver}''
        #         redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
        #         redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
        #     - name: RequestSize   #设置文件上传大小
        #       args:
        #         maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','9e0c8da02a4f2f59c1b66791968f270e','2020-10-10 14:22:06.0','2020-10-10 14:22:06.0',NULL,'0:0:0:0:0:0:0:1','U','')
;
INSERT INTO nacos_config.his_config_info (id,data_id,group_id,app_name,content,md5,gmt_create,gmt_modified,src_user,src_ip,op_type,tenant_id) VALUES 
(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: NCCS-USER
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://NCCS-USER
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/a/**
            # 匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        # - id: nacos-config
        #   uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
        #   predicates:
        #     - Path=/client/**
        #   filters:
        #     - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
        #     - name: RequestRateLimiter  # 局部限流过滤器
        #       args:
        #         key-resolver: ''#{@iPKeyResolver}''
        #         redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
        #         redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
        #     - name: RequestSize   #设置文件上传大小
        #       args:
        #         maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','42be5f187e2fb77103cdb3b2b6133b0e','2020-10-10 14:23:16.0','2020-10-10 14:23:16.0',NULL,'0:0:0:0:0:0:0:1','U','')
,(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        - id: nccs-user
          # 目标服务地址（uri：地址，请求转发后的地址）
          uri: lb://nccs-user
          # 路由条件（predicates：断言，匹配 HTTP 请求内容）
          predicates:
            - Path=/a/**
            # 匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        # - id: nacos-config
        #   uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
        #   predicates:
        #     - Path=/client/**
        #   filters:
        #     - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
        #     - name: RequestRateLimiter  # 局部限流过滤器
        #       args:
        #         key-resolver: ''#{@iPKeyResolver}''
        #         redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
        #         redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
        #     - name: RequestSize   #设置文件上传大小
        #       args:
        #         maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','9e0c8da02a4f2f59c1b66791968f270e','2020-10-10 14:28:38.0','2020-10-10 14:28:38.0',NULL,'0:0:0:0:0:0:0:1','U','')
,(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','
# management:
#   endpoints:
#     web:
#       exposure:
#         include: ''*''
# server:
#   port: 8081
#   servlet:
#     context-path: /
# spring:
#   application:
#     name: gateway
#   cloud:
#     gateway:
#       routes:
#         - id: tick-route
#           filters:
#             - StripPrefix=1
#           predicates:
#             - name: Path
#               args[pattern]: /tick/**
#           uri: lb://demo-tick1
#         - id: tick-route
#           filters:
#             - StripPrefix=1
#           predicates:
#             - name: Path
#               args[pattern]: /dick/**
#           uri: lb://demo-dick
#     nacos:
#       discovery:
#         server-addr: localhost:8848
#       password: nacos
#       username: nacos

server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:
        - id: nccs-user
          filters:
            - StripPrefix=1
          predicates:
            - name: Path
              args[pattern]: /a/**
          uri: lb://nccs-user

        # uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称
        # 路由标识（id：标识，具有唯一性）
        # - id: nccs-user
        #   # 目标服务地址（uri：地址，请求转发后的地址）
        #   uri: lb://nccs-user
        #   # 路由条件（predicates：断言，匹配 HTTP 请求内容）
        #   predicates:
        #     - Path=/a/**
            # 匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        # - id: nacos-config
        #   uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
        #   predicates:
        #     - Path=/client/**
        #   filters:
        #     - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
        #     - name: RequestRateLimiter  # 局部限流过滤器
        #       args:
        #         key-resolver: ''#{@iPKeyResolver}''
        #         redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
        #         redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
        #     - name: RequestSize   #设置文件上传大小
        #       args:
        #         maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','3b20e38eb89f951041ae631b94e498d9','2020-10-10 14:31:17.0','2020-10-10 14:31:18.0',NULL,'0:0:0:0:0:0:0:1','U','')
,(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','
# management:
#   endpoints:
#     web:
#       exposure:
#         include: ''*''
# server:
#   port: 8081
#   servlet:
#     context-path: /
# spring:
#   application:
#     name: gateway
#   cloud:
#     gateway:
#       routes:
#         - id: tick-route
#           filters:
#             - StripPrefix=1
#           predicates:
#             - name: Path
#               args[pattern]: /tick/**
#           uri: lb://demo-tick1
#         - id: tick-route
#           filters:
#             - StripPrefix=1
#           predicates:
#             - name: Path
#               args[pattern]: /dick/**
#           uri: lb://demo-dick
#     nacos:
#       discovery:
#         server-addr: localhost:8848
#       password: nacos
#       username: nacos

server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:
        - id: nccs-user
          filters:
            - StripPrefix=1
          predicates:
            - name: Path
              args[pattern]: /a/**     #断言 与该格式匹配的路由会进行转发
          uri: lb://nccs-user          #uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称

        
        # 路由标识（id：标识，具有唯一性）
        # - id: nccs-user
        #   # 目标服务地址（uri：地址，请求转发后的地址）
        #   uri: lb://nccs-user
        #   # 路由条件（predicates：断言，匹配 HTTP 请求内容）
        #   predicates:
        #     - Path=/a/**
            # 匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        # - id: nacos-config
        #   uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
        #   predicates:
        #     - Path=/client/**
        #   filters:
        #     - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
        #     - name: RequestRateLimiter  # 局部限流过滤器
        #       args:
        #         key-resolver: ''#{@iPKeyResolver}''
        #         redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
        #         redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
        #     - name: RequestSize   #设置文件上传大小
        #       args:
        #         maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','b39fa26d93f53e3ccf21c645b277ac99','2020-10-10 14:31:27.0','2020-10-10 14:31:28.0',NULL,'0:0:0:0:0:0:0:1','U','')
,(6,'nccs-gateway-dev.yml','GATEWAY_GROUP','','server:
  port: 7001
spring:

  cloud:
    gateway:
      discovery:
        locator:
          # 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
          enabled: true
      # 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
      routes:
        - id: nccs-user
          filters:
            - StripPrefix=1
          predicates:
            - name: Path
              args[pattern]: /a/**     #断言 与该格式匹配的路由会进行转发
          uri: lb://nccs-user          #uri以lb://开头（lb代表从注册中心获取服务），后面接的就是需要转发到的服务名称

        
        # 路由标识（id：标识，具有唯一性）
        # - id: nccs-user
        #   # 目标服务地址（uri：地址，请求转发后的地址）
        #   uri: lb://nccs-user
        #   # 路由条件（predicates：断言，匹配 HTTP 请求内容）
        #   predicates:
        #     - Path=/a/**
            # 匹配 如 localhost:3001/consumer/test的请求地址
            # - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]  匹配 2019年1月1日0点0分0秒之后的所有请求， -Before 为之前
            # - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai] 时间范围之内
            # - Header=X-Request-Id, \\d+  请求头匹配
            # - Host=**.baidu.com  host域名匹配,  www.baidu.com,md.baidu.com
            # - Method=GET  请求方式匹配
            # - Query=smile  请求参数匹配  ,eg:localhost:8080?smile=x&id=2
            # - RemoteAddr=192.168.1.1/24 ,ip地址匹配
            # 各种 Predicates 同时存在于同一个路由时，请求必须同时满足所有的条件才被这个路由匹配

        # - id: nacos-config
        #   uri: lb://nacos-config #lb:使用loadBalanceClient实现负载均衡，后面users是微服务的名称[应用于集群环境]
        #   predicates:
        #     - Path=/client/**
        #   filters:
        #     - SetResponseHeader=gatewayHeader, GateWay   #转发添加设置响应头
        #     - name: RequestRateLimiter  # 局部限流过滤器
        #       args:
        #         key-resolver: ''#{@iPKeyResolver}''
        #         redis-rate-limiter.replenishRate: 1   # 每秒钟只允许1个请求
        #         redis-rate-limiter.burstCapacity: 10   # 允许并发有3个请求[宽限的个数]
        #     - name: RequestSize   #设置文件上传大小
        #       args:
        #         maxSize: 5000000



  redis:
    database: 0        # 设置存储分片 db0
    host: localhost
    port: 6379
    password:

','ac1dd425a586e5af30f5d010ed1af7ed','2020-10-10 14:31:47.0','2020-10-10 14:31:48.0',NULL,'0:0:0:0:0:0:0:1','U','')
;