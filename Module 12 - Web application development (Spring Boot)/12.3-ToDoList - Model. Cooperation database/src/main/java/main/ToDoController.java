package main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired(required=false)
    private ToDoRepository toDoRepository;

    @RequestMapping(value = "/ToDo", method = RequestMethod.GET)
    public List<ToDo> list() {
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        ArrayList<ToDo> toDoArrayList = new ArrayList<>();

        for (ToDo toDo : toDoIterable) {
            toDoArrayList.add(toDo);
        }

        return toDoArrayList;
    }

    @RequestMapping(value = "/ToDo", method = RequestMethod.POST)
    public int add(ToDo toDo) {
        ToDo newToDo = toDoRepository.save(toDo);
        return newToDo.getId();
    }

    @RequestMapping(value = "/ToDo/{id}", method = RequestMethod.GET)
    public ResponseEntity get (@PathVariable int id) {

        Optional<ToDo> optionalToDo = toDoRepository.findById(id);

        if (!optionalToDo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalToDo.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/ToDo/{id:\\d+}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        if (!toDoRepository.findById(id).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        toDoRepository.deleteById(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/ToDo/{id}", method = RequestMethod.PUT)
    public ResponseEntity put(@PathVariable int id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);

        if (!optionalToDo.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return new ResponseEntity(toDoRepository.save(optionalToDo.get()), HttpStatus.OK);
    }

    @RequestMapping(value = "/ToDo/search/{name}", method = RequestMethod.GET)
    public ResponseEntity  search(@PathVariable String name) {
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        ArrayList<ToDo> toDoArrayList = new ArrayList<>();

            for (ToDo toDo : toDoIterable) {
                if (toDo.getName().equals(name)) {
                    toDoArrayList.add(toDo);
                }
            }
        return new ResponseEntity(toDoArrayList, HttpStatus.OK);
    }
}
