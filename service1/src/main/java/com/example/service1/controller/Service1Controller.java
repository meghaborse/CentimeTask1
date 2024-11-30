package com.example.service1.controller;

import com.example.service1.service.Service1Service;
import com.example.service1.utils.TraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/service1")
public class Service1Controller {
    private final Service1Service service;
    private static final Logger logger = LoggerFactory.getLogger(TraceUtil.class);


    public Service1Controller(Service1Service service) {
        this.service = service;
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        String traceId = TraceUtil.generateTraceId();
        TraceUtil.loggWithTrace(traceId, "In GET /status call");
        return ResponseEntity.ok("UP");
    }

    @PostMapping("/process")
    public ResponseEntity<Object> process(@RequestBody Map<String, String> payload) {
        String traceId = TraceUtil.generateTraceId();
        TraceUtil.loggWithTrace(traceId, "In POST /process call");
        Map<String, Object> response = new HashMap<>();
        try {
            String contactedString = service.orchestrate(payload);
            response.put("concatString", contactedString);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while processing status: {}", e.getMessage());
            response.put("errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
