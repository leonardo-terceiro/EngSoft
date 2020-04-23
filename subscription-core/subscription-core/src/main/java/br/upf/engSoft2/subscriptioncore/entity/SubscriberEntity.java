package br.upf.engSoft2.subscriptioncore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "subscriber")
public class SubscriberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "name must not be null")
	@NotEmpty(message = "name must not be empty")
	@Column(name = "name")
	private String name;

	@NotNull(message = "last name must not be null")
	@NotEmpty(message = "last name must not be empty")
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull(message = "cpf must not be null")
	@NotEmpty(message = "cpf must not be empty")
	@Size(max = 11, min = 11, message = "please inform a valid cpf")
	@Column(name = "cpf")
	private String cpf;
	
	@NotNull(message = "email must not be null")
	@NotEmpty(message = "email must not be empty")
	@Column(name = "email")
	private String email;
	
	@NotNull(message = "cellphone must not be null")
	@NotEmpty(message = "cellphone must not be empty")
	@Column(name = "cellphone")
	private String cellphone;
	
}
