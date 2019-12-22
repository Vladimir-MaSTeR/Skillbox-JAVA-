import redis.clients.jedis.Jedis;

public class Main {

    public static void main(String[] args) {
        Jedis jedisClient = new Jedis("localhost", 6379);
        Cities cities = new Cities(jedisClient);

        cities.start();
    }
}
