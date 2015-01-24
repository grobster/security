package com.grobster.security;

import java.io.*;

/**
 * This interface is used to save objects to a file.  Also,
 * it rebuilds the object after it has been saved.
 *
 * @author Jeffery Quarles
 * @since 1.0
 */
public interface ObjectSaver extends Serializable {
	public void save(Object o);
	public Object reconstruct(File file);
}