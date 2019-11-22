import java.util.Scanner;                               // импорт пакета со санером для считывания пользовательского ввода

public class Main
{                                                       // начало тела метода МАйн
    // объявление и инициализация переменных
    private static int minIncome =  minInvestmentsAmount / (1 - mainTaxPercent) + calculateFixedCharges() / (1 - managerPercent);  //  нижняя границы дохода
    private static int maxIncome = 900000;             //  верхняя границы дохода

    private static int officeRentCharge = 140000;      // Стоимость аренды офиса
    private static int telephonyCharge = 12000;        // Стоимость телефонной связи
    private static int internetAccessCharge = 7200;    // Стоимость интернета

    private static int assistantSalary = 45000;        // зарплата ассистента
    private static int financeManagerSalary = 90000;   // зарплата финансового менеджера

    private static double mainTaxPercent = 0.24;       // налоговая ставка
    private static double managerPercent = 0.15;       // процент надбавки к зарплате менеджера

    private static double minInvestmentsAmount = 100000;   // минимальная сумма для инвестиций

    public static void main(String[] args)           // главный метод
    {                                                // начало тела метода МЕЙН
        while(true)                                  // начало цикла с условием
        {                                            // начало тела цикла
            System.out.println("Введите сумму доходов компании за месяц " + // вывод в консоль строки
                "(от 200 до 900 тысяч рублей): ");
            int income = (new Scanner(System.in)).nextInt();             // объявление переменной и инициализация этой переменной пользовательским вводом с клавиатуры

            if(!checkIncomeRange(income)) {                             // уловие проверки пользовательского ввода на соответствие границам
                continue;                                               // если условие выше НЕ true то запустить итерацию цикла с начала
            }                                                           // скобка закрывающая тело if,а

            double managerSalary = income * managerPercent;             // подсчет зарплаты менеджера - путём умножния на процент надбавки
            double pureIncome = income - managerSalary -                // подсчет чистого дохода компании от - пользовательский ввод - зарплата менеджера
                calculateFixedCharges();                                // и - общие расходы компании.
            double taxAmount = mainTaxPercent * pureIncome;             // подсчет суммы налога - налоговая ставка УМНОЖАЕТСЯ на чистый доход
            double pureIncomeAfterTax = pureIncome - taxAmount;         // подсчет чистого дохода после уплаты налогов

            boolean canMakeInvestments = pureIncomeAfterTax >=          // возможность инвестировать да \ нет ...чистый доход равен или больше мин. сумме для инвестиции
                minInvestmentsAmount;

            System.out.println("Зарплата менеджера: " + managerSalary);              // вывод в консоль зарплаты менеджера
            System.out.println("Общая сумма налогов: " +                             // вывод в консоль инфо. о налогах
                (taxAmount > 0 ? taxAmount : 0));                                    // тернарный оператор...Если сумма налогов больше 0 то выводить сумму налогов иначе выводить 0
            System.out.println("Компания может инвестировать: " +                    // вывод в констоль
                (canMakeInvestments ? "да" : "нет"));                                // тернарный оператор...Если переменна canMakeInvestments true то выводить ДА иначе выводить НЕТ
            if(pureIncome < 0) {                                                     // ветвление...Если Чистый доход компании МЕНЬШЕ 0
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");   //вывод в консоль текста
            }                                                                        // скобка закрывающая тело последнего ветвления
        }                                                                            // скобка закрывающая цикл While
    }                                                                                // скобка закрывающая метод  main

    private static boolean checkIncomeRange(int income)                              // метод типа БОЛЕН с 1 параметром типа ИНТ
    {
        if(income < minIncome)                                                       // ветвление если введенное значение меньше заданной мин. гранцы
        {                                                                            // начало тела ветвления
            System.out.println("Доход меньше нижней границы");                       // вывод текста в консоль
            return false;                                                            // принудительный выход из ветвления со значением ФОЛС
        }                                                                            // скобка закрывающая ветвление
        if(income > maxIncome)                                                       // ветвление если введённое значение больше заданной макс. границы
        {                                                                            // скобка открывающея тело ветвления
            System.out.println("Доход выше верхней границы");                        // вывод текста в консоль
            return false;                                                            // принудительный выход из ветвления со значением ФОЛС
        }                                                                            // скобка закрывающая ветвление
        return true;                                                                 // метод возвращает ТРУ если не одно из ветвлений в теле метода не выполненно
    }                                                                                // собка закрывающая тело метода

    private static int calculateFixedCharges()                                       // метод типа инт сумирует ежемесячные траты компании
    {                                                                                // начало тела метода
        return officeRentCharge +                                                    // Стоимость аренды офиса
                telephonyCharge +                                                    // Стоимость телефонной связи
                internetAccessCharge +                                               // Стоимость интернета
                assistantSalary +                                                    // зарплата ассистента
                financeManagerSalary;                                                // зарплата финансового менеджера
    }                                                                                // конец тела метода
}                                                                                    // конец тела метода МАЙН
