import redis.clients.jedis.Jedis;

import java.util.Map;

public class Main {

    final static String REDIS_HOST =  "localhost";
    final static int REDIS_PORT = 6379;

    final static String NAME_STUDENT = "Иванов И.И";

    final static String CURSES_WEB_DEVELOPER = "Web-Разработчик";
    final static String CURSES_DATA_SCIENCE = "Data Science";

    final static String NUMBER_HOMEWORK_WEB_DEVELOPER = "1";
    final static String NUMBER_HOMEWORK_DATA_SCIENCE = "4";

    public static void main(String[] args) {

        Jedis jedisClient = new Jedis(REDIS_HOST, REDIS_PORT);

        jedisClient.hset(NAME_STUDENT, CURSES_WEB_DEVELOPER, NUMBER_HOMEWORK_WEB_DEVELOPER);
        jedisClient.hset(NAME_STUDENT, CURSES_DATA_SCIENCE, NUMBER_HOMEWORK_DATA_SCIENCE);

        Map<String, String> studentivanov = jedisClient.hgetAll(NAME_STUDENT);

        studentivanov.keySet().forEach(s -> System.out.println("Курс " + s + " - кол-во выполненного ДЗ = " + studentivanov.get(s)));

        jedisClient.hincrBy(NAME_STUDENT, CURSES_DATA_SCIENCE, 1);

        System.out.println("\n" + "Кол-во ДЗ по " + CURSES_DATA_SCIENCE + " стало = " + jedisClient.hget(NAME_STUDENT, CURSES_DATA_SCIENCE));

    }
}
