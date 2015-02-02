package com.grobster.security;

import java.io.*;
import java.nio.file.*;
import java.nio.file.Files;
import java.util.*;

public class MyDirectory implements MyDirectoryInterface, Serializable {
	private static final long serialVersionUID = 1276733217262648335L;
	private Path currentDirectory;
	private ArrayList<Path> currentDirectoryFiles;
	private ArrayList<Path> encryptedDirectoryFiles;
	public final static String ENCRYPTED_FOLDER_NAME = "encrypted";
	public final static Path ENCRYPTED_DIRECTORY_PATH = Paths.get(ENCRYPTED_FOLDER_NAME);
	
	public MyDirectory() {}
	
	public MyDirectory(Path currentDirectory) {
		this.currentDirectory = currentDirectory;
	}
	
	public List<Path> getEncryptedFiles() {
		return encryptedDirectoryFiles;
	}
	
	/**
	 * This method gets all of the files in the Encrypted directory.
	 */
	public Path getEncryptedDirectory() {
		encryptedDirectoryFiles = new ArrayList<>();
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(ENCRYPTED_DIRECTORY_PATH)) {
			for (Path path: stream) {
                encryptedDirectoryFiles.add(path.getFileName());
				System.out.println(path.getFileName());
            }
        } catch (IOException ex) {
			ex.printStackTrace();
		}
		return ENCRYPTED_DIRECTORY_PATH;
	}
	
	/**
	 * This methods creates the createEncryptedDirectory.
	 */
	public static boolean createEncryptedDirectory() {
		try {
			if (!Files.exists(ENCRYPTED_DIRECTORY_PATH)) {
				Files.createDirectory(ENCRYPTED_DIRECTORY_PATH);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (Files.exists(ENCRYPTED_DIRECTORY_PATH)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method searches current directory and returns result.
	 * @param Path p
	 * @param String filter
	 */
	public boolean searchDirectory(Path p, String filter) {
		if (Files.isDirectory(p)) {
			currentDirectoryFiles = new ArrayList<>();
			
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(p)) {
				for (Path path: stream) {
					if (path.getFileName().toString().toLowerCase().contains(filter.toLowerCase())) {
						currentDirectoryFiles.add(path.getFileName());
						System.out.println(path.getFileName());					
					}
				}
				return true;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean filterDirectory(Path p) {
		return false;
	}
	
	/**
	 * Method deletes a file.
	 * @param Path p
	 */ 
	public boolean deleteFile(Path p) {
		try {
			return Files.deleteIfExists(p);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Method creats a file.
	 * @param Path p
	 */
	public boolean createFile(Path p) {
		try {
			Path file = Files.createFile(p);
			return Files.exists(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Method creates a directory.
	 * @param Path p
	 */
	public boolean createDirectory(Path p) {
		return false;
	}
	
	/**
	 * Method returns the files in the current directory.
	 */
	public List<Path> getFiles() {
		return currentDirectoryFiles;
	}
	
	public Path getCurrentDirectory() {
		currentDirectoryFiles = new ArrayList<>();
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirectory)) {
			for (Path path: stream) {
                currentDirectoryFiles.add(path.getFileName());
				System.out.println(path.getFileName());
            }
        } catch (IOException ex) {
			ex.printStackTrace();
		}
		return currentDirectory;
	}
	
	public void setCurrentDirectory(Path p) {
		currentDirectory = p;
	}
	
	/**
	 * Method creates an encrypted directory
	 * overloaded method
	 * @param Path p
	 * @return boolean true if encrypted directory created; false otherwise
	 */
	public static boolean createEncryptedDirectory(Path p) {
		try {
			Files.createDirectory(p);
			if(Files.exists(p)) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}