package sequia_integra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;
import sequia_integra.entity.rainfall.domain.ErrorMessageDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MonthYearNotFoundException.class)
    public ResponseEntity<ErrorMessageDto>> handleMonthYearNotFoundException(MonthYearNotFoundException ex)
    {
        log.error("Month and year not found exception", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDto(ex.getMessage()));
    }

}
