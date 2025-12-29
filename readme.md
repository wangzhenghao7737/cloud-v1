# cloud-demo-v1
## 服务模块介绍
### 1 service 聚合模块
#### user-service
#### product-service
### 2 gateway 网关服务
### 3 common 聚合模块
#### 1 auth 
自定义starter，用于service服务的权限认证
#### 2 model 
公共类，dto，基础常量，基础枚举，自定义业务异常，统一返回类
## 技术栈
### spring boot
### spring cloud alibaba
### spring cloud gateway
### spring cloud openfeign
### nacos
### sentinel
### seata
### spring security
### redis
### mysql

### 通用技术
#### mapstruct依赖
api+注解处理器
## 经验
### 空值判断
对象判空 → Objects.nonNull()
集合判空 → CollectionUtils.isEmpty()
字符串判空 → StringUtils.isNotBlank() 或 s != null && !s.isBlank()
基本类型 → 无需判 null，直接比值
