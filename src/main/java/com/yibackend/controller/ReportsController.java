package com.yibackend.controller;


import com.yibackend.classes.entity.Reports;
import com.yibackend.classes.entity.Result;
import com.yibackend.service.ReportsServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportsServise reportsServise;

    @PostMapping("/submit")
    public Result submit(@RequestBody Reports report) {
        reportsServise.sumit(report);

        return Result.success();
    }
}
