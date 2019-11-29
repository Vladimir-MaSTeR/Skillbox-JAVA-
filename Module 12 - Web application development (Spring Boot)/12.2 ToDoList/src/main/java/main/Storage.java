package main;

import response.ToDo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {

    private static int currentId = 1;
    private static HashMap<Integer, ToDo> toDos = new HashMap<>();

    public static List<ToDo> getAllToDo() {
        ArrayList<ToDo> toDosList = new ArrayList<>();
        toDosList.addAll(toDos.values());
        return toDosList;
    }

    public static int addToDo(ToDo toDo) {
        int id = currentId++;
        toDo.setId(id);
        toDos.put(id, toDo);
        return id;
    }

    public static ToDo getToDo(int toDoId) {
        if (toDos.containsKey(toDoId)) {
            return toDos.get(toDoId);
        }
        return null;
    }

    public static void deleteToDo(int toDoId) {
        toDos.remove(toDoId);
    }

    public static void putToDo(ToDo toDo) {
        toDos.put(toDo.getId(), toDo);
    }

}
