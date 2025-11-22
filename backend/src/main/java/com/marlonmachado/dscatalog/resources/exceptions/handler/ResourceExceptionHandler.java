package com.marlonmachado.dscatalog.resources.exceptions.handler;

import com.marlonmachado.dscatalog.resources.exceptions.StandardError;
import com.marlonmachado.dscatalog.services.exceptions.DatabaseException;
import com.marlonmachado.dscatalog.services.exceptions.ResourcesNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourcesNotFoundException e, HttpServletRequest request){
            HttpStatus status = HttpStatus.NOT_FOUND;
            StandardError err = new StandardError();
            err.setTimestamp(Instant.now());
            err.setStatus(status.value());
            err.setError("Resource not Found");
            err.setMessage(e.getMessage());
            err.setPath(request.getRequestURI());
            return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Database exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
