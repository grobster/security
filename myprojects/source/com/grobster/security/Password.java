package com.grobster.security;

import java.io.*;

public class Password implements Serializable {
	private String password;
	private static final long serialVersionUID = 629265792263786137L;
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
}