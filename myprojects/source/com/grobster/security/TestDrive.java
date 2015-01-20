package com.grobster.security;

import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class TestDrive {
	public static void main(String[] args) {
		String password = "123456";
		
		try {
			File file = new File("v.ser");
			if (file.exists()) {
				String otherPath = JOptionPane.showInputDialog(null, "Enter Password");
				
				if (otherPath.equals(password)) {
					System.out.println("restored");
					FileInputStream fileStream = new FileInputStream(file);
					ObjectInputStream os = new ObjectInputStream(fileStream);
					Object one = os.readObject();
					FileViewer view = (FileViewer) one;
					view.createView();
					os.close();
				}
			} else {
				Path p = Paths.get("C:\\myprojects\\classes");
				MyDirectoryInterface myDirectory = new MyDirectory(p);
				FileViewer viewer = new FileViewer(myDirectory);
				viewer.createView();
				System.out.println("not restored. a new instance");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}