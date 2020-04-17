package br.upf.engSoft2.subscriptioncore.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.upf.engSoft2.subscriptioncore.constants.ErrorCode;
import br.upf.engSoft2.subscriptioncore.dto.ErrorMessageDTO;
import br.upf.engSoft2.subscriptioncore.exception.SubscriberNotFoundException;

@RestControllerAdvice
public class SubscriberExceptionHandle {

	@ExceptionHandler(SubscriberNotFoundException.class)
	public ResponseEntity<ErrorMessageDTO> subscriberNotFoundExceptionHandle(SubscriberNotFoundException e) {
		ErrorMessageDTO errorMessage = new ErrorMessageDTO();
		errorMessage.setCode(ErrorCode.NOT_FOUND.getCode());
		errorMessage.setMessage(ErrorCode.NOT_FOUND.getMessage());
		errorMessage.setReason(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
	
}
