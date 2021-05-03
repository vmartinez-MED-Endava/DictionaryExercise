package com.endava.workshop.utils.exceptions;

import com.endava.workshop.utils.logger.MLogger;

public class HandleException extends Exception {
        private String code;

        public HandleException(String code, String message) {
            this.setCode(code);
            MLogger.error(code + message);
        }

        public HandleException(String code, String message, Throwable cause) {
            super(message, cause);
            this.setCode(code);
            MLogger.error(code + message);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
}
