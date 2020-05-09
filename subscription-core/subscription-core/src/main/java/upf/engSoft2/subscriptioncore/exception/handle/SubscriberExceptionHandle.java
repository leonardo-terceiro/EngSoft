package upf.engSoft2.subscriptioncore.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import upf.engSoft2.subscriptioncore.constants.ErrorCode;
import upf.engSoft2.subscriptioncore.dto.ErrorMessageDTO;
import upf.engSoft2.subscriptioncore.exception.SubscriberNotFoundException;
import upf.engSoft2.subscriptioncore.exception.UnexpectedErrorException;

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
	
	@ExceptionHandler(UnexpectedErrorException.class)
	public ResponseEntity<ErrorMessageDTO> UnexpectedErrorExceptionHandle(UnexpectedErrorException e) {
		ErrorMessageDTO errorMessage = new ErrorMessageDTO();
		errorMessage.setCode(ErrorCode.UNEXPECTED_ERROR.getCode());
		errorMessage.setMessage(ErrorCode.UNEXPECTED_ERROR.getMessage());
		errorMessage.setReason(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	}
	
}
