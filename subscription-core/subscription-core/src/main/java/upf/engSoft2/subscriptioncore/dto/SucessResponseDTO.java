package upf.engSoft2.subscriptioncore.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SucessResponseDTO {

	private String code;
	private String status;
	private String message;
	private LocalDateTime date;

	
}
