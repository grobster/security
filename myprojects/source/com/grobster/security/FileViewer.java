package com.grobster.security;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;


public class FileViewer implements Serializable {
	private static final long serialVersionUID = 6126972222622776138L;
	private JFrame frame;
	private JPanel shortcutPanel;
	private JTextField pathField;
	private JPanel pathPanel;
	private JTextField searchField;
	private JPanel fileInfoPanel;
	private final static String SEARCH = "Search ";
	private JButton naviateForwardButton;
	private JButton navigateBackwardButton;
	private JTextArea area;
	private JScrollPane scroller;
	private JTextArea faveArea;
	private JScrollPane faveScroller;
	private transient MyDirectoryInterface myDirectory;
	
	public FileViewer(MyDirectoryInterface myDirectory) {
		this.myDirectory = myDirectory;
		this.myDirectory.getCurrentDirectory();
		Path p = Paths.get("encrypted");
		MyDirectory.createEncryptedDirectory();
		
	}
	
	public void createView() {
		frame = new JFrame("File Viewer");
		frame.setSize(800, 600);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		shortcutPanel = new JPanel();
		
		area = new JTextArea(10, 5);
		scroller = new JScrollPane(area);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(BorderLayout.CENTER, scroller);
		
		faveArea = new JTextArea(10, 30);
		faveScroller = new JScrollPane(faveArea);
		faveScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		faveScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		shortcutPanel.add(faveScroller);
		frame.getContentPane().add(BorderLayout.WEST, faveScroller);
		
		pathPanel = new JPanel();
		

		navigateBackwardButton = new JButton("<<");
		pathPanel.add(navigateBackwardButton);
		//navigateBackwardButton.addActionListener(e -> save());
		
		naviateForwardButton = new JButton(">>");
		pathPanel.add(naviateForwardButton);
		

		
		pathField = new JTextField(40);
		pathField.addKeyListener(new PathFieldListener());
		pathPanel.add(pathField);
		
		searchField = new JTextField(15);
		searchField.addKeyListener(new ClearSearchField());
		searchField.addKeyListener(new SearchFieldListener());
		pathPanel.add(searchField);
		
		frame.getContentPane().add(BorderLayout.NORTH, pathPanel);
		
		fileInfoPanel = new JPanel();
		frame.getContentPane().add(BorderLayout.SOUTH, fileInfoPanel);
		
		pathField.setText(myDirectory.getCurrentDirectory().toString());
		addFileNamesToArea();
		resetSearchField();
		frame.setVisible(true);
	}
	
	public void search() {
		Path p = Paths.get(pathField.getText());
		myDirectory.searchDirectory(p, searchField.getText());
	}
	
	public boolean clearSearchField() {
		if (searchField.getText().equals("Search ")) {
			searchField.setText("");
			return true;
		}
		return false;
	}
	
	public void addFileNamesToArea() {
		for (Path p: myDirectory.getFiles()) {
			faveArea.append(p.toString() + "\n");
		}
	}
	
	public void resetSearchField() {
		searchField.setText(FileViewer.SEARCH);
	}
	
	public void save() {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("v.ser"));
			os.writeObject(this);
			os.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	class ClearSearchField implements KeyListener {
		public void keyPressed(KeyEvent e) {
			clearSearchField();
		}
		
		public void keyTyped(KeyEvent e) {
			clearSearchField();
		}
		
		public void keyReleased(KeyEvent e) {
			clearSearchField();
		}
	}
	
	class PathFieldListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			//area.setText("");
			//pathField.setText(myDirectory.getCurrentDirectory().toString());
			//addFileNamesToArea();
		}
		
		public void keyTyped(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				Path p = Paths.get(pathField.getText());
				myDirectory.setCurrentDirectory(p);
				pathField.setText(myDirectory.getCurrentDirectory().toString());
				faveArea.setText("");
				addFileNamesToArea();			
			}

		}
		
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				Path p = Paths.get(pathField.getText());
				myDirectory.setCurrentDirectory(p);
				pathField.setText(myDirectory.getCurrentDirectory().toString());
				faveArea.setText("");
				addFileNamesToArea();			
			}
		}
	}
	
	class SearchFieldListener implements KeyListener {
		public void keyPressed(KeyEvent e) {

		}
		
		public void keyTyped(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				search();
				faveArea.setText("");
				addFileNamesToArea();			
			}

		}
		
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				search();
				faveArea.setText("");
				addFileNamesToArea();			
			}
		}	
	}
}