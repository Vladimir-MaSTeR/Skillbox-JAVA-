package utils;

public class TestData {

    public static void insertTestData(){
        MongoDB.addShop("ДОБАВИТЬ_МАГАЗИН Spar");
        MongoDB.addShop("ДОБАВИТЬ_МАГАЗИН Девяточка");
        MongoDB.addShop("ДОБАВИТЬ_МАГАЗИН Пятерочка");

        MongoDB.addProducts("ДОБАВИТЬ_ТОВАР Вафли 100");
        MongoDB.addProducts("ДОБАВИТЬ_ТОВАР Хлеб 50");
        MongoDB.addProducts("ДОБАВИТЬ_ТОВАР Печенье 80");

        MongoDB.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Вафли Девяточка");
        MongoDB.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Хлеб Девяточка");
        MongoDB.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Печенье Девяточка");

        MongoDB.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Вафли Пятерочка");
        MongoDB.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Хлеб Пятерочка");
        MongoDB.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Печенье Пятерочка");

        MongoDB.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Вафли Spar");
        MongoDB.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Хлеб Spar");
        MongoDB.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Печенье Spar");
    }

}
