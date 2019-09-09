package test;

import static org.junit.Assert.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.*;
import Exceptions.*;
import commands.*;
import fileSystem.*;
import driver.CommandExecuter;

public class HistoryTest {
  Command command;
  FileSystem fs;
  Interceptor interceptor;
  PrintStream origOut;

  private static class Interceptor extends PrintStream {
    String printedResult = "";
    public Interceptor(OutputStream out) {
      super(out, true);
    }

    @Override
    public void print(String s) {// do what ever you like
      printedResult = s;
    }
    public String getOutput() {
      return printedResult;
    }
  }
  

  public static void main(String[] args) {
    PrintStream origOut = System.out;
    PrintStream interceptor = new Interceptor(origOut);
    System.setOut(interceptor);// just add the interceptor
  }

  @Before
  public void setUp() {
    fs = FileSystem.createSingleFS();
    origOut = System.out;
    interceptor = new Interceptor(origOut);
    System.setOut(interceptor);// just add the interceptor

  }

  @Test
  public void testGetSpecificNNumberOfHistories() {

    // test case incomplete
    CommandExecuter.excute(fs, "Mkdir dir");
    CommandExecuter.excute(fs, "ls");
    //assertEquals(interceptor.getOutput(), "/:\ndir");
    CommandExecuter.excute(fs, "history");
    String trimmed = interceptor.getOutput().trim();
    String[] result = trimmed.split("\n");
    //origOut.print(result[0]);
    assertEquals(result[0], "1. history");
    assertEquals(result[1], "2. ls");
    assertEquals(result[2], "3. Mkdir dir");
    
  }

}
