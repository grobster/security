package com.grobster.security;

import java.io.*;

public class PasswordSaver implements ObjectSaver {
	private static final long serialVersionUID = 229265892263786098L;
	
	public void save(Object o) {
		if (o instanceof Password) {
			try {
				FileOutputStream fos = new FileOutputStream("o.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(o);
				oos.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public Object reconstruct(File file) {
		Password password = null;
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				Object o = ois.readObject();
				
				password = (Password) o;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return password;
	}
}