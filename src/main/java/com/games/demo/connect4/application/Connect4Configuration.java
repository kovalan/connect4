package com.games.demo.connect4.application;

import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Add dropwizard configurations here
 * @author kvenkate
 *
 */
public class Connect4Configuration extends Configuration {
	@NotEmpty
	private String requestTimeOut;

	@JsonProperty
	public String getRequestTimeOut() {
		return requestTimeOut;
	}
	
	@JsonProperty
	public void setRequestTimeOut(String requestTimeOut) {
		this.requestTimeOut = requestTimeOut;
	}
	
}
