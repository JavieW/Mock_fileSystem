package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Exceptions.*;
import commands.*;
import fileSystem.*;

public class MkdirTest {

  FileSystem fs;
  Mkdir mkdir;

  @Before
  public void setup() {
	  fs = FileSystem.createSingleFS();
	  mkdir = new Mkdir();
  }
  @Test
  public void testMkdirByName0() throws MissingParameterException{
     mkdir.excute(fs, "1", null);
     assertTrue(fs.getCur().FindChildIndex("1") != -1);
  }
  
  @Test
  public void testMkdirByInvalidName0() throws MissingParameterException{
	     mkdir.excute(fs, "#$^$^", null);
	     assertTrue(fs.getCur().FindChildIndex("#$^$^") == -1);
  }
 
  @Test
  public void testMkdirByPath0() throws MissingParameterException{
     mkdir.excute(fs, "1/2", null);
     Directory parent = (Directory) fs.getCur().getChild().get(0);
     assertTrue(parent.FindChildIndex("2") != -1);
  } 
  
  @Test
  public void testMkdirByPath1() throws MissingParameterException{
     mkdir.excute(fs, "./1/3", null);
     Directory parent = (Directory) fs.getCur().getChild().get(0);
     assertTrue(parent.FindChildIndex("3") != -1);
  }
  
  @Test
  public void testMkdirByPath2() throws MissingParameterException{
	 mkdir.excute(fs, "/4", null);
	 assertTrue(fs.getCur().FindChildIndex("4") != -1);
  }
  
  @Test
  public void testMultipleDir0() throws MissingParameterException{
	 mkdir.excute(fs, "/5 6 ./7", null);
	 assertTrue(fs.getCur().FindChildIndex("5") != -1);
	 assertTrue(fs.getCur().FindChildIndex("6") != -1);
	 assertTrue(fs.getCur().FindChildIndex("7") != -1);
  }
  
  @Test
  public void testMultipleDir1() throws MissingParameterException{
	 mkdir.excute(fs, "/8 #$% ./10", null);
	 assertTrue(fs.getCur().FindChildIndex("#$%") == -1);
	 assertTrue(fs.getCur().FindChildIndex("10") != -1);
	 assertTrue(fs.getCur().FindChildIndex("8") != -1);
  }
  
  
}
