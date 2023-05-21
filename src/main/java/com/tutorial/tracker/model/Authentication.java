package com.tutorial.tracker.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;





import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tbl_token")
public class Authentication {
     @Id
     @SequenceGenerator(name="auth_sequence",sequenceName = "auth_sequence",allocationSize = 1,initialValue = 9000)
     @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "auth_sequence")
	private Long authId;
	
	private String token;
	
    private LocalDate tokenCreationDate;
	
	
	@OneToOne
	@JoinColumn(name = "fk_userId")
	private User user;


	public Authentication(User user) {
		super();
		this.token = UUID.randomUUID().toString();
		this.tokenCreationDate = LocalDate.now();
		this.user = user;
	}
	
	
}
