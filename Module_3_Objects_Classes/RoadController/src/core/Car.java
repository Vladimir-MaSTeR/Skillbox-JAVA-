package core;

public class Car
{
    private String number;       // создание переменной
    private int height;          // создание переменной
    private double weight;       // создание переменной
    private boolean hasVehicle;  // создание переменной
    private boolean isSpecial;   // создание переменной

    public String toString()
    {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isHasVehicle() {
        return hasVehicle;
    }
    public void setHasVehicle(boolean hasVehicle) {
        this.hasVehicle = hasVehicle;
    }

    public boolean isSpecial() {
        return isSpecial;
    }
    public void setSpecial(boolean special) {
        isSpecial = special;
    }
}