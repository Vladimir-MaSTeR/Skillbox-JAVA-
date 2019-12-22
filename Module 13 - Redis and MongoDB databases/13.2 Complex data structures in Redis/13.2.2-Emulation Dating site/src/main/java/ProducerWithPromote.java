import redis.clients.jedis.Jedis;

import static utils.Constants.*;
import static utils.Utils.waitSomeTime;

public class ProducerWithPromote {

    public static void main(String[] args) {
        int count = 0;
        try (Jedis client = new Jedis(HOST, PORT)) {
            while (true) {
                client.rpoplpush(KEY, KEY);
                waitSomeTime(1000);
                count++;
                if (count == 5) {
                    long randomPosition = (long) (0 + 20 * Math.random());
                    String user = client.lindex(KEY, randomPosition);
                    client.lrem(KEY,0, user);
                    client.lpush(KEY, user);
                    count = 0;
                }
            }
        }
    }

}
