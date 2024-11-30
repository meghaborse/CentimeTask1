package com.example.service1.utils;

import org.slf4j.*;

import java.util.UUID;

public class TraceUtil {
    private static final Logger logger = LoggerFactory.getLogger(TraceUtil.class);

    public static void loggWithTrace(String traceId, String message) {
        logger.info("[TraceId: {}] {}", traceId, message);
    }

    public static String generateTraceId() {
        return UUID.randomUUID().toString();
    }
}
