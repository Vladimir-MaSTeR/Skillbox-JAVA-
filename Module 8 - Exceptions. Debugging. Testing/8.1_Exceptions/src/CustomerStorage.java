import exceptions.*;

import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    private final String REGEX_EMAIL = "^.+@.+$";
    private final String REGEX_PHONE_NUMBER = "(\\+*)\\d{7,11}";

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        if(components.length != 4){
            throw new NodLengthException("Wrong format. Correct format: \n" + "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        if (!components[2].matches(REGEX_EMAIL)){
            throw new NodValidEmailException("Wrong format EMAIL. Correct format: \n" + "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        if (!components[3].matches(REGEX_PHONE_NUMBER)) {
             throw new NodValidPhoneNumberException("Wrong format PHONE NUMBER. Correct format: \n" + "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }

        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        if (storage.size() == 0) throw new NodSizeHashMapStorageException("Wrong. List is empty");
        else storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        String [] removeName = name.split("\\s+");
        if (storage.size() == 0) throw new NodSizeHashMapStorageException("Wrong. List is empty");
        if (removeName.length != 2) throw new NodLengthException("Wrong format NAME. Correct format: Василий Петров");
        if (!storage.containsKey(name)) throw new NodContainsKeyHashMapException("Wrong. There is no such name");
        storage.remove(name);

    }

    public int getCount()
    {
        return storage.size();
    }
}