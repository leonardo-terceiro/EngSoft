package upf.engsoft.notificationmail.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseDTO {

	private String status;
	private String code;
	private String message;
	private LocalDateTime timestamp = LocalDateTime.now();
	
}
