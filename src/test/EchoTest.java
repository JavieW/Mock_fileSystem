package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.InvalidFileNameException;
import Exceptions.InvalidInputException;
import Exceptions.MissingParameterException;
import Exceptions.NotDirectoryException;
import Exceptions.NotExistException;
import Exceptions.NotFileException;
import commands.Echo;
import fileSystem.*;

public class EchoTest {

	private FileSystem fs;
	private Echo echo;
	
	@Before
	public void setUp() {
		fs = FileSystem.createSingleFS();
		echo = new Echo();
	}
	
	@Test
	public void testExistedFile0() 
			throws InvalidInputException, NotExistException, 
			NotDirectoryException, InvalidFileNameException, 
			NotFileException, MissingParameterException {
		File a = new File("a");
		fs.getRoot().add(a);
		echo.excute(fs, '"'+"Hello"+'"', "> a");
		assertEquals("Hello", a.getValue());
	}
	
	@Test
	public void testExistedFile1() 
			throws InvalidInputException, NotExistException, 
			NotDirectoryException, InvalidFileNameException, 
			NotFileException, MissingParameterException {
		File a = new File("a");
		fs.getRoot().add(a);
		echo.excute(fs, '"'+"abc"+'"', ">> a");
		assertEquals("abc", a.getValue());
	}
	
	@Test
	public void testOverwrite0() 
			throws InvalidInputException, NotExistException, 
			NotDirectoryException, InvalidFileNameException, 
			NotFileException, MissingParameterException {
		File a = new File("a");
		fs.getRoot().add(a);
		a.setValue("123");
		echo.excute(fs, '"'+"World"+'"', "> a");
		assertEquals("World", a.getValue());
	}

	@Test
	public void testAppend1() 
			throws InvalidInputException, NotExistException, 
			NotDirectoryException, InvalidFileNameException, 
			NotFileException, MissingParameterException {
		File a = new File("a");
		fs.getRoot().add(a);
		a.setValue("Hello");
		echo.excute(fs, '"'+"World"+'"', ">> a");
		assertEquals("HelloWorld", a.getValue());
	}
	
	@Test
	public void testUnexistedFile0() 
			throws InvalidInputException, NotExistException, 
			NotDirectoryException, InvalidFileNameException, 
			NotFileException, MissingParameterException {
		echo.excute(fs, '"'+"Hello"+'"', "> b");
		File b = (File) fs.getRoot().getChild().get(0);
		assertEquals("Hello", b.getValue());
	}
	
	@Test
	public void testUnexistedFile1() 
			throws InvalidInputException, NotExistException, 
			NotDirectoryException, InvalidFileNameException, 
			NotFileException, MissingParameterException {
		echo.excute(fs, '"'+"Hello"+'"', ">> c");
		File c = (File) fs.getRoot().getChild().get(0);
		assertEquals("Hello", c.getValue());
	}
}
