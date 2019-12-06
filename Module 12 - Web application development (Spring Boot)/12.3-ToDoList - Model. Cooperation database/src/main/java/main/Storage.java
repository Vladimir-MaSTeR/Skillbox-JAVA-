package main;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Integer deleteToDo(int toDoId) {
        if (toDos.containsKey(toDoId)) {
            toDos.remove(toDoId);
            return toDoId;
        }
        return null;
    }

    public static ToDo putToDo(ToDo toDo) {
        if (toDos.containsKey(toDo.getId())) {
            toDos.remove(toDo.getId());
            toDos.put(toDo.getId(), toDo);
            return toDo;
        }
        return null;
    }

    public static List<ToDo> search(String name) {
        return toDos.values()
                .stream()
                .filter(toDo -> toDo.getName().contains(name))
                .collect(Collectors.toList());
    }

}
