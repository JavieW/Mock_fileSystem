package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Exceptions.MissingParameterException;
import commands.*;
import fileSystem.FileSystem;

public class ExitTest {
  FileSystem fs;
  private static Exit exit = new Exit();

  @Before
  public void setUp() {
    fs = FileSystem.createSingleFS();
  }

  @Test
  public void testExcute() throws MissingParameterException {
    exit.excute(fs, null, null);
    assertTrue(fs.isExit());
  }

}
