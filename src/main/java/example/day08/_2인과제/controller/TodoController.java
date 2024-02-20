package example.day08._2인과제.controller;


import example.day08._2인과제.model.dao.TodoDAO;
import example.day08._2인과제.model.dto.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class TodoController {
    @Autowired
    TodoDAO todoDAO;

    @GetMapping("/todo")
    public String view(){

        return "/todo2";
    }
    @PostMapping("/todo")
    @ResponseBody
    public boolean write(TodoDTO todoDTO){
        System.out.println(todoDTO.getContent());
        boolean result = todoDAO.write(todoDTO);

        return result;
    }

    @GetMapping("/allview")
    @ResponseBody
    public ArrayList<TodoDTO> allview(){
        ArrayList<TodoDTO> result = todoDAO.allview();
        return result;
    }

    @PostMapping("/rework")
    @ResponseBody
    public boolean rework(int no){
        boolean result = todoDAO.rework(no);
        return result;
    }

    @PostMapping("/del")
    @ResponseBody
    public boolean del(int no){
        boolean result = todoDAO.del(no);
        return result;
    }

    @PostMapping("/update")
    @ResponseBody
    public boolean update(TodoDTO todoDTO){
        boolean result = todoDAO.update(todoDTO);
        return result;
    }
}
