package com.loljoa.DBInit.web.api;

import com.loljoa.DBInit.web.service.InitializeDB;
import com.loljoa.DBInit.web.service.InitializeDBFromS3;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/init/db")
@RequiredArgsConstructor
public class DBInitializeApi {
    private final InitializeDBFromS3 initializeDBFromS3;
    private final InitializeDB initializeDB;

    @GetMapping("/")
    public String hello() {
        return "Hello World!!!";
    }

    @PutMapping("/lck")
    public String initializeSchedule() throws IOException {
        initializeDBFromS3.initLCKSchedule();
        return "SUCCESS!";
    }

    @PutMapping("/data")
    public String initializeData() {
        initializeDB.initData();
        return "SUCCESS!";
    }

    @PutMapping("/test/data")
    public String initializeTestData() {
        initializeDB.InitTestData();
        return "SUCCESS!";
    }
}

