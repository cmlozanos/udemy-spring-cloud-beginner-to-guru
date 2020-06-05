package cmls.spring.msscbeerservice.web.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandler{

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
		List<String> errors = e.getConstraintViolations().stream().map(constraintViolation -> 
			constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage()
		).collect(Collectors.toList());

		return ResponseEntity.badRequest().body(errors);
	}
}
