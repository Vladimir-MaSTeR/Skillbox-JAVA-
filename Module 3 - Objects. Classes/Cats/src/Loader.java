
public class Loader
{

    public void newCat () {
        Cat cat = new Cat(2000.2);
    }

    public static void main(String[] args)
    {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();

        while (cat5.isAlive) {
            cat5.meow();
        }


        System.out.println("Статус пятой кошки: " + cat5.getStatus());

        Cat cat = new Cat();
        cat.drink(1.1);
        cat.feed(2.2);
        cat.meow();
        cat.feed(9.0);

        
        System.out.println(cat.getStatus());
        System.out.println("Масса съеденной еды: " + cat.weightEatenFood());
        System.out.println("Количество котов: " + Cat.count);
    }
}