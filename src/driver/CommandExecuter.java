package driver;

import commands.Command;
import fileSystem.FileSystem;

/**
 * judge the command, and then execute the command
 * 
 * @author Yiqian Wu, Jingwei Wang
 *
 */
public class CommandExecuter {

  /**
   * execute the command
   * 
   * @param fs, a FileSystem where the commands be called
   * @param commandLine, a string of commands
   */

  public static void excute(FileSystem fs, String commandLine) {

    fs.getHistory().add(0, commandLine);
    String command;
    String parameter = null;
    String redirection = null;

    // get the addition, if there has one.
    commandLine = commandLine.trim();
    int j = commandLine.indexOf(">");
    if (j != -1) {
      redirection = commandLine.substring(j);
      commandLine = commandLine.substring(0, j).trim();
    }

    // get the command name and parameter if there has one.
    int i = commandLine.indexOf(" ");
    if (i == -1) {
      command = commandLine;
    } else {
      command = commandLine.substring(0, i);
      parameter = commandLine.substring(i + 1).trim();
    }

    // // debug printting
    // System.out.println(command);
    // System.out.println(parameter);
    // System.out.println(redirection);
    try {
      // capitalize the first letter in command
      command = command.toLowerCase();
      command = command.substring(0, 1).toUpperCase() + command.substring(1);
      Class temp = Class.forName("commands." + command);
      try {
        // define instanceofClss and then execute it
        Command instanceOfClss = (Command) temp.newInstance();
        instanceOfClss.excute(fs, parameter, redirection);
      } catch (Exception e) {
        System.out.println(e);
      }

    } catch (ClassNotFoundException e) {
      System.out.println(e);
    }
  }
}
