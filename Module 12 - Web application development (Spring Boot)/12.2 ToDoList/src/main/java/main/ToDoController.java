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

    @RequestMapping(value = "/ToDo/{name}", method = RequestMethod.GET)
    public ResponseEntity  search(@PathVariable String name) {
        List<ToDo> list = Storage.search(name);
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
