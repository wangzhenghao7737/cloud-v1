# 优化
## 单点登录
### 当前问题
目前登录代码中，虽然重复登录的新token会覆盖旧token，但旧token
仍然有效，因为缓存中的键是token解析出的subject，对于同一用户的
新旧token都可以解析出user id。
携带旧token访问/logout接口，会从缓存中删除新token
### 优化方式
生成jwt时同时生成jti，即每个jwt的唯一id（可以用uuid）。
登录代码中向redis存储的是自定义的LoginUserDetails类，可以给该
类增添一个string字段用于存储jti
## jwt
### 当前问题
1. identity service用到的 dto,utils,redisClient,objectMapper
,constants没有提取到公共模块。
2. 有关security的配置文件使用nacos统一管理，如密钥，token过期时间，常量等
3. security相关的redisTemplate，objectMapper与其他服务模块存在名
称重复或功能重复。
### 优化方式
1. 将security有关的代码写在一个包里
2. 抽取为自定义starter
### 限制
redisTemplate,objectMapper都是较重的对象，尽量不重复定义