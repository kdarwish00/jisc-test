package net.openathens.journal.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global error handler that converts unexpected exceptions into a clear 500 response.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles any unhandled exception and returns a generic 500 error response.
     *
     * @param ex the underlying exception
     * @return 500 Internal Server Error with a simple error message payload
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemResponse> handleException(Exception ex) {
        logger.error("Unhandled exception while processing request", ex);
        ProblemResponse body = new ProblemResponse("Unexpected server error. Please try again later.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    /**
     * Minimal error response payload for clients.
     */
    public static final class ProblemResponse {
        private final String message;

        public ProblemResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}

