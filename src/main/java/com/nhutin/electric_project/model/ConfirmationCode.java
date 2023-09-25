package com.nhutin.electric_project.model;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "ConfirmationCode")
@NamedQuery(name = "ConfirmationCode.findAll", query = "SELECT c FROM ConfirmationCode c")
public class ConfirmationCode implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "user_id")
	private int userID;

	@Column(name = "IsConfirmed")
	private boolean isConfirmed;

	private String OTPCode;

	private Timestamp OTPCreationDate;

	private Timestamp OTPExpirationDate;

	// bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public ConfirmationCode(int userID, boolean isConfirmed, String oTPCode, Timestamp oTPCreationDate,
			Timestamp oTPExpirationDate, User user) {
		this.userID = userID;
		this.isConfirmed = isConfirmed;
		OTPCode = oTPCode;
		OTPCreationDate = oTPCreationDate;
		OTPExpirationDate = oTPExpirationDate;
		this.user = user;
	}

	public ConfirmationCode(int userID, boolean isConfirmed, String OTPCode, Timestamp OTPCreationDate,
			Timestamp OTPExpirationDate) {
		this.userID = userID;
		this.isConfirmed = isConfirmed;
		this.OTPCode = OTPCode;
		this.OTPCreationDate = OTPCreationDate;
		this.OTPExpirationDate = OTPExpirationDate;
	}



	public ConfirmationCode() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public boolean getIsConfirmed() {
		return this.isConfirmed;
	}

	public void setIsConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public String getOTPCode() {
		return this.OTPCode;
	}

	public void setOTPCode(String OTPCode) {
		this.OTPCode = OTPCode;
	}

	public Timestamp getOTPCreationDate() {
		return this.OTPCreationDate;
	}

	public void setOTPCreationDate(Timestamp OTPCreationDate) {
		this.OTPCreationDate = OTPCreationDate;
	}

	public Timestamp getOTPExpirationDate() {
		return this.OTPExpirationDate;
	}

	public void setOTPExpirationDate(Timestamp OTPExpirationDate) {
		this.OTPExpirationDate = OTPExpirationDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}