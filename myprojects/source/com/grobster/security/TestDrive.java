package com.grobster.security;

import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class TestDrive {
	public static void main(String[] args) {
		//String password = "123456";
		Password password = null;
		ObjectSaver objectSaver = new PasswordSaver();
		
		Path encrypted_path = Paths.get("encrypted");
		
		if (!Files.exists(encrypted_path)) {  //check to see if encrypted directory exists
			try {
				String newPassword = JOptionPane.showInputDialog(null, "Enter New Password"); //prompt user to enter password
				if (!newPassword.equals("") && newPassword.length() > 0) { //ensure password was entered
					String newPasswordConfirmed = JOptionPane.showInputDialog(null, "Confirm New Password"); // prompt for password again
					if (newPasswordConfirmed.equals(newPassword)) { //ensure passwords match
						password = new Password(); //new password object
						password.setPassword(newPasswordConfirmed); //set password to confirmed user entered password
						objectSaver.save(password);
						//duplication
						try {
							String enteredPassword = JOptionPane.showInputDialog(null, "Enter Password");
								
							if (enteredPassword.equals(password.getPassword())) {
								Path p = Paths.get("C:\\myprojects\\classes");
								MyDirectoryInterface myDirectory = new MyDirectory(p);
								FileViewer viewer = new FileViewer(myDirectory);
								viewer.createView();
								System.out.println("not restored. a new instance");
							}

						} catch (Exception ex) {
							ex.printStackTrace();
						}						
						//dupication
					}
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

		} else if (Files.exists(encrypted_path)) {
			try {
				String enteredPassword = JOptionPane.showInputDialog(null, "Enter Password");
				Password restoredPassword = (Password) objectSaver.reconstruct(new File("o.ser"));
				
				if (enteredPassword.equals(restoredPassword.getPassword())) {
					Path p2 = Paths.get("C:\\myprojects\\classes");
					MyDirectoryInterface myDirectory = new MyDirectory(p2);
					FileViewer viewer = new FileViewer(myDirectory);
					viewer.createView();
					System.out.println("not restored. a new instance");
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}		
		}
		

	}
}