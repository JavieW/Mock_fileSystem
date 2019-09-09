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
import commands.*;
import fileSystem.*;

public class CatTest {

  private FileSystem fs;
  private Cat cat;

  @Before
  public void setUp() {
    cat = new Cat();
    fs = FileSystem.createSingleFS();
    Directory root = new Directory("/");
    Directory a = new Directory("a");
    File b = new File("b");
    File c = new File("c");
    root.add(a);
    root.add(b);
    a.add(c);
    b.setValue("Hellow");
    c.setValue("World");
    fs.setRoot(root);
    fs.setCur(root);
  }

  @Test
  public void testCatOnFile() throws MissingParameterException,
      InvalidFileNameException, NotFileException, NotExistException,
      NotDirectoryException, InvalidInputException {
    boolean isThrow = false;
    try {
      cat.excute(fs, null, "> d");
    } catch (MissingParameterException e) {
      isThrow = true;
    }
    assertTrue(isThrow);
  }

  @Test
  public void testCatOneFile0() throws MissingParameterException,
      InvalidFileNameException,
      NotFileException,
      NotExistException, NotDirectoryException, InvalidInputException {
    cat.excute(fs, "b", "> d");
    int i = fs.getRoot().FindChildIndex("d");
    File d = (File) fs.getRoot().getChild().get(i);
    assertEquals("Hellow", d.getValue());
  }

  @Test
  public void testCatOneFile1() throws MissingParameterException,
      InvalidFileNameException,
      NotFileException, NotExistException, NotDirectoryException,
      InvalidInputException {
    cat.excute(fs, "b", ">> d");
    int i = fs.getRoot().FindChildIndex("d");
    File d = (File) fs.getRoot().getChild().get(i);
    assertEquals("Hellow", d.getValue());
  }

  @Test
  public void testCatOneFile2() throws MissingParameterException,
      InvalidFileNameException,
      NotFileException, NotExistException, NotDirectoryException,
      InvalidInputException {
    cat.excute(fs, "b", "> d");
    cat.excute(fs, "b", ">> d");
    int i = fs.getRoot().FindChildIndex("d");
    File d = (File) fs.getRoot().getChild().get(i);
    assertEquals("HellowHellow", d.getValue());
  }

  @Test
  public void testCatOneFile3() throws MissingParameterException,
      InvalidFileNameException,
      NotFileException, NotExistException, NotDirectoryException,
      InvalidInputException {
    cat.excute(fs, "b", ">> d");
    cat.excute(fs, "b", "> d");
    int i = fs.getRoot().FindChildIndex("d");
    File d = (File) fs.getRoot().getChild().get(i);
    assertEquals("Hellow", d.getValue());
  }

  @Test
  public void testCatOneFile4() throws MissingParameterException,
      InvalidFileNameException,
      NotFileException, NotExistException, NotDirectoryException,
      InvalidInputException {
    cat.excute(fs, "e", "> d");
  }

  @Test
  public void testCatOneFile5() throws MissingParameterException,
      InvalidFileNameException,
      NotFileException, NotExistException, NotDirectoryException,
      InvalidInputException {
    cat.excute(fs, "a", "> d");
  }

  @Test
  public void testCatMoreFile0() throws MissingParameterException,
      InvalidFileNameException,
      NotFileException, NotExistException, NotDirectoryException,
      InvalidInputException {
    cat.excute(fs, "b /a/c", "> d");
    int i = fs.getRoot().FindChildIndex("d");
    File d = (File) fs.getRoot().getChild().get(i);
    assertEquals("HellowWorld", d.getValue());
  }

  @Test
  public void testCatMoreFile1() throws MissingParameterException,
      InvalidFileNameException,
      NotFileException, NotExistException, NotDirectoryException,
      InvalidInputException {
    cat.excute(fs, "/b a/c", "> d");
    int i = fs.getRoot().FindChildIndex("d");
    File d = (File) fs.getRoot().getChild().get(i);
    assertEquals("HellowWorld", d.getValue());
  }

  @Test
  public void testCatMoreFile2() throws MissingParameterException,
      InvalidFileNameException,
      NotFileException, NotExistException, NotDirectoryException,
      InvalidInputException {
    cat.excute(fs, "/b e a/c", "> d");
    int i = fs.getRoot().FindChildIndex("d");
    File d = (File) fs.getRoot().getChild().get(i);
    assertEquals("HellowWorld", d.getValue());
  }

  @Test
  public void testCatMoreFile3() throws MissingParameterException,
      InvalidFileNameException,
      NotFileException, NotExistException, NotDirectoryException,
      InvalidInputException {
    cat.excute(fs, "/b a a/c", "> d");
    int i = fs.getRoot().FindChildIndex("d");
    File d = (File) fs.getRoot().getChild().get(i);
    assertEquals("HellowWorld", d.getValue());
  }
}
