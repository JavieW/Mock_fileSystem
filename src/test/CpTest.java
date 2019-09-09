package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Exceptions.InvalidInputException;
import Exceptions.MissingParameterException;
import Exceptions.NotDirectoryException;
import Exceptions.NotExistException;
import commands.Cp;
import fileSystem.Directory;
import fileSystem.File;
import fileSystem.FileSystem;

public class CpTest {

	private FileSystem fs;
	private Cp cp;
	
	@Before
	public void setUp() {
		cp = new Cp();
		fs = FileSystem.createSingleFS();
		Directory root = new Directory("/");
		Directory a = new Directory("a");
		Directory e = new Directory("e");
		Directory b = new Directory("b");
		File c = new File("c");
		File d = new File("d");
		root.add(a);
		root.add(e);
		a.add(b);
		a.add(c);
		b.add(d);
		c.setValue("Hellow");
		d.setValue("World");
		fs.setRoot(root);
		fs.setCur(root);
	}
	
	@Test
	public void testCpFile0() 
			throws MissingParameterException, NotExistException, 
			NotDirectoryException, InvalidInputException {
		cp.excute(fs, "a/c /", null);
		int i = fs.getRoot().FindChildIndex("c");
		File cCopy = (File) fs.getRoot().getChild().get(i);
		assertEquals("Hellow", cCopy.getValue());
	}
	
	@Test
	public void testCpFile1() 
			throws MissingParameterException, NotExistException, 
			NotDirectoryException, InvalidInputException {
		cp.excute(fs, "/a/c /e", null);
		int i = fs.getRoot().FindChildIndex("e");
		Directory e = (Directory) fs.getRoot().getChild().get(i);
		int j = e.FindChildIndex("c");
		assertEquals("Hellow", ((File) e.getChild().get(j)).getValue());
	}
	
	@Test
	public void testCpDir0() 
			throws MissingParameterException, NotExistException, 
			NotDirectoryException, InvalidInputException {
		cp.excute(fs, "/a/b /", null);
		int i = fs.getRoot().FindChildIndex("b");
		Directory b = (Directory) fs.getRoot().getChild().get(i);
		File d = (File) b.getChild().get(0);
		assertEquals("World", d.getValue());
	}
	
	@Test
	public void testCpDir1() 
			throws MissingParameterException, NotExistException, 
			NotDirectoryException, InvalidInputException {
		cp.excute(fs, "/a /", null);
		int i = fs.getRoot().FindChildIndex("a");
		Directory a = (Directory) fs.getRoot().getChild().get(i);
		File c = (File) a.getChild().get(1);
		assertEquals("Hellow", c.getValue());
	}
	
	@Test
	public void testCpDir2() 
			throws MissingParameterException, NotExistException, 
			NotDirectoryException, InvalidInputException {
		cp.excute(fs, "/a /", null);
		int i = fs.getRoot().FindChildIndex("a");
		Directory a = (Directory) fs.getRoot().getChild().get(i);
		Directory b = (Directory) a.getChild().get(0);
		File d = (File) b.getChild().get(0);
		assertEquals("World", d.getValue());
	}
}
