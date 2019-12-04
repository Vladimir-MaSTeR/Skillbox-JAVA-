package main;

import model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import model.ToDo;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @RequestMapping(value = "/ToDo", method = RequestMethod.GET)
    public List<ToDo> list() {
        Iterable<ToDo> toDoIterable = toDoRepository.findAll()
        return Storage.getAllToDo();
    }

    @RequestMapping(value = "/ToDo", method = RequestMethod.POST)
    public int add(ToDo toDo) {
        ToDo newToDo = toDoRepository.save(toDo);
        return newToDo.getId();
    }

    @RequestMapping(value = "/ToDo/{id}", method = RequestMethod.GET)
    public ResponseEntity get (@PathVariable int id) {
        ToDo toDo = Storage.getToDo(id);
        if (toDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(toDo, HttpStatus.OK);
    }

    @RequestMapping(value = "/ToDo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        ToDo toDo = Storage.getToDo(id);
        if (toDo == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return new ResponseEntity(Storage.deleteToDo(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/ToDo/{id}", method = RequestMethod.PUT)
    public ResponseEntity put(@PathVariable int id) {
        ToDo toDo = Storage.getToDo(id);
        if (toDo == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return new ResponseEntity(Storage.putToDo(toDo), HttpStatus.OK);
    }

    @RequestMapping(value = "/ToDo/search/{name}", method = RequestMethod.GET)
    public ResponseEntity  search(@PathVariable String name) {
        List<ToDo> list = Storage.search(name);
        //if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
