package by.sysoev.tourApp.GlobalHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> catchRuntimeException(RuntimeException exception){
//        log.error("something was wrong with message {}}", exception.getMessage());
//        return ResponseEntity.status(500).body(String.format("something was wrong with message %s}",exception));
//    }
}
