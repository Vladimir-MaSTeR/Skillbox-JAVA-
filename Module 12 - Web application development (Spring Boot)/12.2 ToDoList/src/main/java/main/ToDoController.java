package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.ToDo;

import java.util.List;

@RestController
public class ToDoController {

    @RequestMapping(value = "/ToDo", method = RequestMethod.GET)
    public List<ToDo> list() {
       return Storage.getAllToDo();
    }

    @RequestMapping(value = "/ToDo", method = RequestMethod.POST)
    public int add(ToDo toDo) {
       return Storage.addToDo(toDo);
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
    public void delete(@PathVariable int id) {
        Storage.deleteToDo(id);
    }

    @RequestMapping(value = "/ToDo", method = RequestMethod.PUT)
    public void put(ToDo toDo) {
        Storage.putToDo(toDo);
    }
}
