package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.ToDo;

import java.util.List;

@RestController
public class ToDoController {

    @GetMapping("/books")
    public List<ToDo> list() {
       return Storage.getAllToDo();
    }

    @PostMapping("/books")
    public int add(ToDo toDo) {
       return Storage.addToDo(toDo);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity get (@PathVariable int id) {
        ToDo toDo = Storage.getToDo(id);
        if (toDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(toDo, HttpStatus.OK);
    }
}
