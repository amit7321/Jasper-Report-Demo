package com.example.Jasper.Report.Demo.repository;

import net.sf.jasperreports.engine.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class ReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JasperPrint exportPdf(int id) throws SQLException, FileNotFoundException, JRException {

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        File file = ResourceUtils.getFile("classpath:report/Todo.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);

        return JasperFillManager.fillReport(jasperReport, parameter, connection);

    }
}
