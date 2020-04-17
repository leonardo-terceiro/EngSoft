package br.upf.engSoft2.subscriptioncore.dto;

import lombok.Data;

@Data
public class ErrorMessageDTO {

	private String message;
	private String code;
	private String reason;
	
}
