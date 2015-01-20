package com.grobster.security;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public interface MyDirectoryInterface extends Serializable {
	public boolean searchDirectory(Path p, String filter);
	public boolean filterDirectory(Path p);
	public boolean deleteFile(Path p);
	public boolean createFile(Path p);
	public boolean createDirectory(Path p);
	public List<Path> getFiles();
	public Path getCurrentDirectory();
	public void setCurrentDirectory(Path p);
	
}