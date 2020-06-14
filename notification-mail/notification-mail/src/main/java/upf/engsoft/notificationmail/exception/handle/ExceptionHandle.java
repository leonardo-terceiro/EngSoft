package upf.engsoft.notificationmail.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import upf.engsoft.notificationmail.dto.ResponseDTO;
import upf.engsoft.notificationmail.exception.NoDataFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ResponseDTO> noDataFoundExceptionHandle(NoDataFoundException nde){
		ResponseDTO response = new ResponseDTO();
		response.setCode("200");
		response.setStatus("OK");
		response.setMessage(nde.getMessage());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDTO> defaultExceptionHandle(Exception e){
		log.error("defaultExceptionHandle() - ERROR - unexpected error happend: ", e);
		ResponseDTO response = new ResponseDTO();
		response.setCode("500");
		response.setStatus("UNEXPECTED_ERROR");
		response.setMessage("Erro inexperado ocorreu, por favor tente novamente mais tarde ou entre em contato com o nosso suporte [163510@upf.br]");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
}
