缓存工具说明
======

简介
---

本缓存工具主要是为了简化一些常见缓存操作，合理规范地使用Redis。

### 缓存Key

以英文冒号分割多个单词（至少要有一个冒号）, 禁用大写字母。



接口
---

### CacheService


### CacheObjectService



使用
---

### 先配置Maven

```java
    <dependencies>
        <groupId>com.pzj.framework</groupId>
        <artifactId>pzj-cache</artifactId>
        <version>1.1.0-SNAPSHOT</version>
    </dependencies>
```

### 在Spring中配置redis连接

```java
    <!-- 连接池配置 -->
    <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
        <!-- 连接池中最大连接数。高版本：maxTotal，低版本：maxActive -->
        <property name="maxTotal" value="200" />
        <!-- 连接池中最大空闲的连接数 -->
        <property name="maxIdle" value="$0" />
        <!-- 连接池中最少空闲的连接数 -->
        <property name="minIdle" value="10"/>
        <!-- 当连接池资源耗尽时，调用者最大阻塞的时间，超时将跑出异常。单位，毫秒数;默认为-1.表示永不超时。高版本：maxWaitMillis，低版本：maxWait -->
        <property name="maxWaitMillis" value="20000" />
        <!-- 连接空闲的最小时间，达到此值后空闲连接将可能会被移除。负值(-1)表示不移除. -->
        <property name="minEvictableIdleTimeMillis" value="300000" />
        <!-- 对于“空闲链接”检测线程而言，每次检测的链接资源的个数。默认为3 -->
        <property name="numTestsPerEvictionRun" value="3" />
        <!-- “空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1. -->
        <property name="timeBetweenEvictionRunsMillis" value="60000" />

        <!-- 向调用者输出“链接”资源时，是否检测是有有效，如果无效则从连接池中移除，并尝试获取继续获取。默认为false。建议保持默认值 -->
        <property name="testOnBorrow" value="false"/>
        <!-- 向连接池“归还”链接时，是否检测“链接”对象的有效性。默认为false。建议保持默认值 -->
        <property name="testOnReturn" value="false"/>
        <!-- 向调用者输出“链接”对象时，是否检测它的空闲超时；默认为false。如果“链接”空闲超时，将会被移除。建议保持默认值. -->
        <property name="testWhileIdle" value="false"/>
        <!-- whenExhaustedAction:当“连接池”中active数量达到阀值时，即“链接”资源耗尽时，连接池需要采取的手段, 默认为1(0:抛出异常。1:阻塞，直到有可用链接资源。2:强制创建新的链接资源) -->
    </bean>

    <bean id="jedisPool" class="redis.clients.jedis.JedisPool" scope="singleton">
        <constructor-arg index="0" ref="jedisPoolConfig"/>
        <constructor-arg index="1" value="${redis.uri}" />
    </bean>
```


### 配置缓存操作对象

    <bean id="redisCacheContext" class="com.pzj.framework.cache.redis.RedisCacheContext">
        <property name="connection">
            <bean class="com.pzj.framework.cache.redis.RedisPoolConnection">
                <property name="jedisPool" ref="jedisPool" />
            </bean>
        </property>
    </bean>
    
    <bean id="cacheService" factory-bean="redisCacheContext" factory-method="getCacheService" />
    
    <bean id="cacheObjectService" factory-bean="redisCacheContext" factory-method="getCacheObjectService" />

### 基本类型操作

```javascript
    @Resource
    private CacheService cacheService
    
    @Test
    public void test(){
        String key = "user:123456789";  // key 至少要有一个冒号
        String value = "{'id' : '123456789', 'name' : 'smith'}";
        cacheService.strSet(key, value);
    }
```

### 自定义类型操作

    @Resource
    private CacheObjectService cacheObjectService
    
    @Test
    public void test(){
        SysUser user = new SysUser();
        user.setId(123456789L);
        user.setName("smith")
    
        String key = "user:123456789";  // key 至少要有一个冒号
        
        cacheObjectService.set(key, user);  // 默认序列化成JSON保存在String类型缓存中
        
        cacheObjectService.hset(key, user); // 默认序列化成JSON保存在Map类型缓存中
    }

