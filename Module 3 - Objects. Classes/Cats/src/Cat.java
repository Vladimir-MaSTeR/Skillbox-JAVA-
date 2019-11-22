
public class Cat implements Cloneable
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    public static int count;

    public static final int EYES_COUNT = 2;
    public static final double MIN_WEIGHT = 1000;
    public static final double MAX_WEIGHT = 5000;

    public static boolean isAlive;
    public double ieat;

    private ColorCat color;

//------------
    public Cat()
    {
        isAlive = true;
        count++;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;

    }
    public Cat(double weight) {
        this();
        this.weight = weight;
    }
//--------------
    @Override
    protected Cat clone() throws CloneNotSupportedException {
        return (Cat) super.clone();
    }

    public static Cat copy (Cat original) {
        Cat cat = new Cat();

        cat.setWeight(original.getWeight());
        cat.setOriginWeight(original.getOriginWeight());
        cat.setMinWeight(original.getMinWeight());
        cat.setMaxWeight(original.getMaxWeight());
        cat.setColor(original.getColor());

        return cat;
    }

    public double weightEatenFood() {
        return ieat;
    }

    public void useBathroom() {
        weight = weight - 1;
        System.out.println("Кошка сходила в туалет");
    }

    public void meow() {
        if (isAlive) {
            weight = weight - 1;
            System.out.println("Meow");

            if (!isWeightNormal()){
                isAlive = false;
                count--;
            }
        } else {
            System.out.println("Кот не может пить");
        }
    }

    public void feed(Double amount) {
        if (isAlive){
            ieat = ieat + amount;
            weight = weight + amount;

            if (!isWeightNormal()) {
                isAlive = false;
                count--;
            }
        } else {
            System.out.println("Котик не может кушать");
        }
    }

    public void drink(Double amount)
    {
        weight = weight + amount;
    }

    public boolean isWeightNormal() {
        return (getWeight()> MIN_WEIGHT && getWeight() <MAX_WEIGHT);
    }

//----------
    public double getOriginWeight() {
    return originWeight;
}
    public void setOriginWeight(double originWeight) {
        this.originWeight = originWeight;
    }

    public Double getWeight()
    {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMinWeight() {
        return minWeight;
    }
    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public ColorCat getColor() {
        return color;
    }
    public void setColor(ColorCat color) {
        this.color = color;
    }

    public String getStatus() {
        if(weight < minWeight) {
            //   isAlive = false;
            return "Dead";
        }
        else if(weight > maxWeight) {
            //  isAlive = false;
            return "Exploded";
        }
        else if(weight > originWeight) {
            //   isAlive = true;
            return "Sleeping";
        }
        else {
            //   isAlive = true;
            return "Playing";
        }
    }
}