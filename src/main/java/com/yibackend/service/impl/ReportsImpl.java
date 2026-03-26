package com.yibackend.service.impl;

import com.yibackend.classes.entity.Reports;
import com.yibackend.context.CurrentIdHolder;
import com.yibackend.mapper.ReportsMapper;
import com.yibackend.service.ReportsServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportsImpl implements ReportsServise {

    @Autowired
    private ReportsMapper reportsMapper;

    @Override
    public void sumit(Reports report) {
        //TODO把id改成动态的
        report.setReporterId(CurrentIdHolder.getCurrentId());
        reportsMapper.insert(report);
    }
}
