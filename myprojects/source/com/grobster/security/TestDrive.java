package com.grobster.security;

import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class TestDrive {
	public static void main(String[] args) {
		String password = "123456";
		
		try {
			String otherPath = JOptionPane.showInputDialog(null, "Enter Password");
				
			if (otherPath.equals(password)) {
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