package com.grobster.security;

import java.io.*;

public interface ObjectSaver extends Serializable {
	public void save(Object o);
	public Object reconstruct(File file);
}