import redis.clients.jedis.Jedis;

import java.util.Set;

public class Cities {

    private Jedis jedisClient;

    public Cities(Jedis jedisClient) {
        this.jedisClient = jedisClient;
    }

    public void start() {
        init();
        smallPrice();
        bigPrise();
        removeKey();
    }

    private void init() {
        jedisClient.zadd("Cities",43000, "Оукленд");
        jedisClient.zadd("Cities",37000, "Мельбурн");
        jedisClient.zadd("Cities",25000, "Токио");
        jedisClient.zadd("Cities",10000, "Санкт-Петербург");
        jedisClient.zadd("Cities",40000, "Ванкувер");
        jedisClient.zadd("Cities",34000, "Чикаго");
        jedisClient.zadd("Cities",8000, "Осло");
        jedisClient.zadd("Cities",50000, "Лос Анджелес");
        jedisClient.zadd("Cities",15000, "Ливерпуль");
    }

    private void  smallPrice() {
        Set<String> cities = jedisClient.zrange("Cities", 0, 2);
        System.out.println("Самые дешёвые билеты: " + cities);
    }

    private void bigPrise() {
        Set<String> cities = jedisClient.zrevrange("Cities", 0, 2);
        System.out.println("Самые дорогие билеты: " + cities);
    }

    private void removeKey() {
        jedisClient.del("Cities");
    }
}
