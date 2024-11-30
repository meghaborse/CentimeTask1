package com.example.service3.controller;

import com.example.service3.exception.InvalidInputeException;
import com.example.service3.utils.TraceUtil;
import org.slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/service3")
public class Service3Controller {
    private static final Logger logger = LoggerFactory.getLogger(Service3Controller.class);

    @PostMapping("/concat")
    public ResponseEntity<String> concat(@RequestBody Map<String, String> payload) throws InvalidInputeException {
        String traceId = TraceUtil.generateTraceId();
        TraceUtil.logWithTrace(traceId, "");
        if (payload.get("Name") == null || payload.get("surname") == null) {
            logger.error("Invalid input please check payload");
            throw new InvalidInputeException("Invalid input please check payload");
        }
        String name = payload.get("Name");
        String surname = payload.get("surname");
        return ResponseEntity.ok(name + " " + surname);
    }
}
