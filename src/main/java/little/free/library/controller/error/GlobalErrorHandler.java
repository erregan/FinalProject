package little.free.library.controller.error;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

//This Global Handling Error catches and manages any unhandled errors 
//that happen in the application as a whole

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
		@ExceptionHandler(NoSuchElementException.class)
		@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> handleNoSuchElementException (
		NoSuchElementException ex) {
			log.error("Exception: {}", ex.toString());
			return Map.of("message", ex.toString());
		}
	}
