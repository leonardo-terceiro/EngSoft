package upf.engsoft.notificationmail.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import upf.engsoft.notificationmail.dto.ResponseDTO;
import upf.engsoft.notificationmail.exception.NoDataFoundException;

@RestControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ResponseDTO> noDataFoundExceptionHandle(NoDataFoundException nde){
		ResponseDTO response = new ResponseDTO();
		response.setCode("200");
		response.setStatus("OK");
		response.setMessage(nde.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
