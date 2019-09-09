// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: wangj589
// UT Student #: 1003777754
// Author: Jingwei Wang
//
// Student2:
// UTORID user_name: liuyumi1
// UT Student #: 1004178695
// Author: Yuming Liu
//
// Student3:
// UTORID user_name: wuyiqia2
// UT Student #: 1003784826
// Author: Yiqian Wu
//
// Student4:
// UTORID user_name:
// UT Student #:
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package driver;

import java.util.Scanner;
import fileSystem.*;

/**
 * This class was used to run all the cases get the input from users scan them,
 * execute the commands and record the commends
 * 
 * @author Yiqian Wu, Jingwei Wang
 *
 */
public class JShell {

  private static FileSystem fs;

  /**
   * construct the JShell
   */
  public JShell() {
    fs = FileSystem.createSingleFS();
  }

  /**
   * get the reference of the FS
   * @return the single reference of the FileSyetem
   */
  public FileSystem getFs() {
    return fs;
  }

  /**
   * Driver for the program.
   * @param arg
   */
  public static void main(String arg[]) {
    // use the past two function to define JShell and FileSystem
    JShell js = new JShell();
    FileSystem fs = js.getFs();
    // keep ask users for inputs until exit this file system
    while (!fs.isExit()) {
      // get a input from users
      Scanner in = new Scanner(System.in);
      System.out.print("/#: ");
      String commandLine = in.nextLine();
      if (!commandLine.equals("")) {
	      // record that command to history
	      CommandExecuter.excute(fs, commandLine);
      }
    }
  }
}
