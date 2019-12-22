import redis.clients.jedis.Jedis;

import static utils.Constants.*;
import static utils.Utils.waitSomeTime;

public class Player {

    public static void main(String[] args) {
        try (Jedis client = new Jedis(HOST, PORT)) {
            for (long i = 0; i < 20; i++) {
                client.lpush(KEY, "User  " + i);
            }
            while (true) {
                waitSomeTime(1000);
                String currentActiveUser = client.lindex(KEY, 0);
                System.out.println(currentActiveUser);
            }
        }
    }

}
