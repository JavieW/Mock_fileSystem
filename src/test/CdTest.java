package test;

import fileSystem.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Exceptions.*;
import commands.Cd;

public class CdTest {

  FileSystem fs;
  Cd cd;

  @Before
  public void setUp() {
    cd = new Cd();
    fs = FileSystem.createSingleFS();
    Directory root = fs.getRoot();
    root.add(new Directory("a"));
    Directory a = (Directory) root.getChild().get(0);
    a.add(new File("b"));
  }

  @Test
  public void testCdByFileName() throws NotExistException,
  NotDirectoryException,
      InvalidInputException, MissingParameterException {
    cd.excute(fs, "a", null);
    Directory cur = fs.getCur();
    assertEquals("a", cur.getName());
  }

  @Test
  public void testCdByPath1() throws NotExistException, NotDirectoryException,
      InvalidInputException, MissingParameterException {
    cd.excute(fs, "/a", null);
    Directory cur = fs.getCur();
    assertEquals("a", cur.getName());
  }

  @Test
  public void testCdByPath2() throws NotExistException, NotDirectoryException,
      InvalidInputException, MissingParameterException {
    cd.excute(fs, "./a", null);
    Directory cur = fs.getCur();
    assertEquals("a", cur.getName());
  }

  @Test
  public void testCdByPath3() throws NotExistException, NotDirectoryException,
      InvalidInputException, MissingParameterException {
    fs.setCur((Directory) fs.getCur().getChild().get(0));
    cd.excute(fs, "..", null);
    Directory cur = fs.getCur();
    assertEquals("/", cur.getName());
  }

  @Test
  public void testCdByPath4() throws NotExistException, NotDirectoryException,
      InvalidInputException, MissingParameterException {
    fs.setCur((Directory) fs.getCur().getChild().get(0));
    cd.excute(fs, "../a", null);
    Directory cur = fs.getCur();
    assertEquals("a", cur.getName());
  }

  @Test
  public void testNotDirectoryException()
      throws NotExistException, InvalidInputException,
      MissingParameterException {
    boolean thrown = false;
    fs.setCur((Directory) fs.getCur().getChild().get(0));
    try {
      cd.excute(fs, "b", null);
    } catch (NotDirectoryException e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  public void testNotExistException()
      throws NotDirectoryException, InvalidInputException,
      MissingParameterException {
    boolean thrown = false;
    try {
      cd.excute(fs, "c", null);
    } catch (NotExistException e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  public void testMissingParameterException()
      throws NotDirectoryException, InvalidInputException, NotExistException {
    boolean thrown = false;
    try {
      cd.excute(fs, null, null);
    } catch (MissingParameterException e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  public void testInvalidInputException()
      throws NotDirectoryException, NotExistException,
      MissingParameterException {
    boolean thrown = false;
    try {
      cd.excute(fs, "$#^%#^", null);
    } catch (InvalidInputException e) {
      thrown = true;
    }
    assertTrue(thrown);
  }
  
  @Test
  public void testRedirection0() throws NotExistException,
  NotDirectoryException,
      InvalidInputException, MissingParameterException {
    cd.excute(fs, "a", "> aas");
    Directory cur = fs.getCur();
    assertEquals("a", cur.getName());
  }
  
  @Test
  public void testRedirection1() throws NotExistException,
  NotDirectoryException,
      InvalidInputException, MissingParameterException {
    cd.excute(fs, "a", ">> aas");
    Directory cur = fs.getCur();
    assertEquals("a", cur.getName());
  }

  @After
  public void cleanUp() {
	// since fs used singleton design, we must refresh it manually.
    fs.setCur(fs.getRoot());
  }
}
