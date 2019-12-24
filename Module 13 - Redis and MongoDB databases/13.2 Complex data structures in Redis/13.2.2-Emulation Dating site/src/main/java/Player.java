import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import static utils.Constants.*;
import static utils.Utils.waitSomeTime;

public class Player {

    private static Jedis client;

    private static void firstStart() {
        for (int i = 1; i <= USERS_COUNT; i++)
            client.rpush(KEY, Integer.toString(i));
    }

    public static void main(String[] args) {
        client = new Jedis(HOST, PORT);

        firstStart();

        Long userListLength = client.llen(KEY);

        for (;;) {
            for (long i = 0; i < userListLength; i++)
            {
                String currentUser = client.lindex(KEY,i);

                System.out.printf("Пользователь на экране <%s>%n",currentUser);

                if (Math.round(Math.random() * 10) >= 9)
                {
                    long random = randomUserPopup(userListLength, currentUser);
                    if (random < i) i--;
                }
                waitSomeTime(SLEEP);
            }
        }
    }

    private static long randomUserPopup(Long length,String currentUser) {
        boolean flag = true;
        String luckyUser = "";
        long random = 0;

        while (flag) {
            random = Math.round(Math.random() * (length - 1));
            luckyUser = client.lindex(KEY, random);
            flag = luckyUser.equals(currentUser);
        }

        client.lrem(KEY,0,luckyUser);
        client.linsert(KEY, ListPosition.AFTER,currentUser, luckyUser);

        System.out.printf("!!! Пользователь <%s> поставлен в начало очереди  !!!%n", luckyUser);
        return random;
    }

}
