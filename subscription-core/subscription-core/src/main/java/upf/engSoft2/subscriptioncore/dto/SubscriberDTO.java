package upf.engSoft2.subscriptioncore.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SubscriberDTO {

	@NotNull(message = "name must not be null")
	@NotEmpty(message = "name must not be empty")
	private String name;

	@NotNull(message = "last name must not be null")
	@NotEmpty(message = "last name must not be empty")
	private String lastName;
	
	@NotNull(message = "cpf must not be null")
	@NotEmpty(message = "cpf must not be empty")
	@Size(max = 11, min = 11, message = "please inform a valid cpf")
	private String cpf;
	
	@NotNull(message = "email must not be null")
	@NotEmpty(message = "email must not be empty")
	private String email;
	
	@NotNull(message = "cellphone must not be null")
	@NotEmpty(message = "cellphone must not be empty")
	private String cellphone;
	
}
