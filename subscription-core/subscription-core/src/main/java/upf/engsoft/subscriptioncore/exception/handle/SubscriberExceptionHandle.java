package upf.engsoft.subscriptioncore.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import upf.engsoft.subscriptioncore.constants.ResponseCode;
import upf.engsoft.subscriptioncore.dto.ErrorMessageDTO;
import upf.engsoft.subscriptioncore.exception.SubscriberNotFoundException;
import upf.engsoft.subscriptioncore.exception.UnexpectedErrorException;

@RestControllerAdvice
public class SubscriberExceptionHandle {

	@ExceptionHandler(SubscriberNotFoundException.class)
	public ResponseEntity<ErrorMessageDTO> subscriberNotFoundExceptionHandle(SubscriberNotFoundException e) {
		ErrorMessageDTO errorMessage = new ErrorMessageDTO();
		errorMessage.setCode(ResponseCode.NOT_FOUND.getCode());
		errorMessage.setMessage(ResponseCode.NOT_FOUND.getMessage());
		errorMessage.setReason(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
	
	@ExceptionHandler(UnexpectedErrorException.class)
	public ResponseEntity<ErrorMessageDTO> UnexpectedErrorExceptionHandle(UnexpectedErrorException e) {
		ErrorMessageDTO errorMessage = new ErrorMessageDTO();
		errorMessage.setCode(ResponseCode.UNEXPECTED_ERROR.getCode());
		errorMessage.setMessage(ResponseCode.UNEXPECTED_ERROR.getMessage());
		errorMessage.setReason(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	}
	
}
