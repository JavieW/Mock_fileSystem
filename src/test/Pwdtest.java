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
import commands.Pwd;
import commands.Cd;
import fileSystem.Directory;
import fileSystem.File;
import fileSystem.FileSystem;
import fileSystem.Getter;

public class Pwdtest {


  private FileSystem fs;
  private Pwd pwd;
  private Cd cd;

  @Before
  public void setUp() {
    pwd = new Pwd();
    fs = FileSystem.createSingleFS();
    
   }

   @Test
   public void testPwdWithNoPreviousCommands() throws MissingParameterException, InvalidFileNameException, NotFileException, NotExistException, NotDirectoryException, InvalidInputException {
     pwd.excute(fs, null, "> f");
     int i = fs.getRoot().FindChildIndex("f");
     File f = (File) fs.getRoot().getChild().get(i);
     assertEquals(f.getValue(), "/");
      
   }
}

