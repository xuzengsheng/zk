INSERT INTO nacos_config.config_info (data_id,group_id,content,md5,gmt_create,gmt_modified,src_user,src_ip,app_name,tenant_id,c_desc,c_use,effect,`type`,c_schema) VALUES 
('nccs-gateway-dev.yml','GATEWAY_GROUP','server:
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
              args[pattern]: /b/**     #断言 与该格式匹配的路由会进行转发
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

','abe2c726bd92782a9fcf45cce3b11b17','2020-10-10 10:32:13.0','2020-10-10 14:31:48.0',NULL,'0:0:0:0:0:0:0:1','','','网关配置','null','null','yaml','null')
,('nccs-user-dev.yml','DEFAULT_GROUP','server:
  port: 9001

spring:
  #数据库
  datasource:
    url: jdbc:mysql://localhost:3306/nccs_user?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver','3c91833c316b4e11e5271055fee7808b','2020-10-10 11:55:26.0','2020-10-10 11:55:26.0',NULL,'0:0:0:0:0:0:0:1','','','用户模块配置',NULL,NULL,'yaml',NULL)
;