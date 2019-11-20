package core;

public class Car
{
    public String number;       // создание переменной
    public int height;          // создание переменной
    public double weight;       // создание переменной
    public boolean hasVehicle;  // создание переменной
    public boolean isSpecial;   // создание переменной

    public String toString()
    {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}