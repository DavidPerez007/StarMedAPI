package med.star.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorManager {

    @ExceptionHandler(EntityNotFoundException.class) //So that it detects 404, it will return this response instead of one another
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ValidationErrorData::new).toList(); //like form.errors in django
        return ResponseEntity.badRequest().body(errors); //We are showing the specific errors to the user
    }
    // This record is meant to be used as a DTO for not to show the user all stacktrace but only field and error.
    private record ValidationErrorData(String field, String error){
        public ValidationErrorData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }
}
