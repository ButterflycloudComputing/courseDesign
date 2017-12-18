package cn.edu.tju.scs.dao.cache;

import redis.clients.jedis.JedisPool;

/**
 * Created by haoxiaotian on 2016/9/26 20:33.
 */
public class RedisUtil {
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        if(jedisPool == null){
            jedisPool = new JedisPool(host,port);
        }
        return jedisPool;
    }


}
