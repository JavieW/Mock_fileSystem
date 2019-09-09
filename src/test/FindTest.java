package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Exceptions.InvalidFileNameException;
import Exceptions.InvalidInputException;
import Exceptions.MissingParameterException;
import Exceptions.NotDirectoryException;
import Exceptions.NotExistException;
import Exceptions.NotFileException;
import commands.Find;
import fileSystem.*;

public class FindTest {

  private FileSystem fs;
  private Find find;

  @Before
  public void setuo() {
    fs = FileSystem.createSingleFS();
    find = new Find();
    File a1 = new File("1");
    Directory a2 = new Directory("2");
    Directory b1 = new Directory("1");
    Directory b2 = new Directory("2");
    File c1 = new File("1");
    Directory dc1 = new Directory("1");

    b1.add(c1);
    b2.add(dc1);
    a2.add(b1);
    a2.add(b2);

    fs.getRoot().add(a1);
    fs.getRoot().add(a2);
  }

  @Test
  public void testTypef() 
      throws InvalidInputException, NotExistException, 
      NotDirectoryException, InvalidFileNameException, 
      NotFileException, MissingParameterException {
    find.excute(fs, "/ -type f -name 1", "> output1");
    int i = fs.getRoot().FindChildIndex("output1");
    File output1 = (File) fs.getRoot().getChild().get(i);
    String actural = output1.getValue();
    assertEquals("/1\n/2/1/1", actural);
  }

  @Test
  public void testTyped() 
      throws InvalidInputException, NotExistException, 
      NotDirectoryException, InvalidFileNameException, 
      NotFileException, MissingParameterException {
    find.excute(fs, "/ -type d -name 1", "> output2");
    int i = fs.getRoot().FindChildIndex("output2");
    File output2 = (File) fs.getRoot().getChild().get(i);
    String actural = output2.getValue();
    assertEquals("/2/1\n/2/2/1", actural);
  }
}
