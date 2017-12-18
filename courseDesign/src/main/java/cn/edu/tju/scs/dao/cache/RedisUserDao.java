package cn.edu.tju.scs.dao.cache;

import cn.edu.tju.scs.entity.auth.User;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by haoxiaotian on 2016/9/26 21:18.
 */
public class RedisUserDao {

    private RedisUtil redisUtil;

    Logger logger = LoggerFactory.getLogger(RedisUserDao.class);

    private RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);

    public Long putUser(String token,User user){
        try{
            Jedis jedis = redisUtil.getJedisPool().getResource();
            try{
                byte [] bytes = ProtostuffIOUtil.toByteArray(user, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                // 超时缓存
                int timeout = 60 * 60; // 1 小时
                Long result = jedis.hset("users".getBytes(),token.getBytes(),bytes);
                return result;
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    public User getUser(String token){
        // redis 操作逻辑
        try {
            Jedis jedis =  redisUtil.getJedisPool().getResource();
            try{
                // 并没有实现内部序列化操作
                // get——>byte[] ->反序列化
                // protostuff:pojo
                byte[] bytes = jedis.hget("users".getBytes(), token.getBytes());
                // 缓存重获取到
                if(bytes != null){
                    // 空对象
                    User user = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,user,schema);
                    // path 被反序列化
                    return user;
                }
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }


    public Long delUser(String token){
        // redis 操作逻辑
        try {
            Jedis jedis =  redisUtil.getJedisPool().getResource();
            try{
                Long result = jedis.hdel("users",token);
                return result;
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }



    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
