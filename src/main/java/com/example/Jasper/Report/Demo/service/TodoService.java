package com.example.Jasper.Report.Demo.service;


import com.example.Jasper.Report.Demo.entity.Todo;
import com.example.Jasper.Report.Demo.repository.ReportRepository;
import com.example.Jasper.Report.Demo.repository.TodoRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ReportRepository reportRepository;

    public void createTodos(Todo todo) {
        todoRepository.save(todo);
    }

    public List<Todo> list() {
        return todoRepository.findAll();
    }

    public void exportPdfReport(int id, OutputStream outputStream) throws JRException, IOException, SQLException {
        JasperPrint jasperPrint = reportRepository.exportPdf(id);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }
}
