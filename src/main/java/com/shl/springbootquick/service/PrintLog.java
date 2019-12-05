package com.shl.springbootquick.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintLog {
    // 日志级别：trace<debug<info<warn<error, 日志会打印当前级别及更高级别的日志
    // 默认日志级别： info
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        PrintLog printLog = new PrintLog();
        printLog.logger.trace("trace log");
        printLog.logger.debug("debug log");
        printLog.logger.info("info log");
        printLog.logger.warn("warn log");
        printLog.logger.error("error log");
    }
}
