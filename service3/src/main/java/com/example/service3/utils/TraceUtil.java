package com.example.service3.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class TraceUtil {

    private static final Logger logger = LoggerFactory.getLogger(TraceUtil.class);

    public static String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    public static void logWithTrace(String traceId, String s) {
        logger.info("[TraceId:{}] {}",traceId,s);
    }
}
