package com.example.Jasper.Report.Demo.controller;


import com.example.Jasper.Report.Demo.entity.Todo;
import com.example.Jasper.Report.Demo.service.TodoService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    public String createTodo(@RequestBody Todo todo) {
        todoService.createTodos(todo);
        return "Saved Succesfully";
    }

    @GetMapping("/list")
    public List<Todo> listOfTodo() {
        return todoService.list();
    }

    @GetMapping("/report/{id}")
    public void generateReport(HttpServletResponse response, @PathVariable int id) throws IOException, JRException, SQLException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"todo.pdf\"");
        OutputStream outputStream = response.getOutputStream();
        todoService.exportPdfReport(id, outputStream);
    }

}
