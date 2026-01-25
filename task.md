# 优化
## 单点登录
### 目前问题
目前登录代码中，虽然重复登录的新token会覆盖旧token，但旧token
仍然有效，因为缓存中的键是token解析出的subject，对于同一用户的
新旧token都可以解析出user id。
携带旧token访问/logout接口，会从缓存中删除新token
### 优化方式
生成jwt时同时生成jti，即每个jwt的唯一id（可以用uuid）。
登录代码中向redis存储的是自定义的LoginUserDetails类，可以给该类增添一个string字段用于存储jti