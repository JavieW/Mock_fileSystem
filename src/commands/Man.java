package commands;

import Exceptions.CommandNotExitException;
import Exceptions.InvalidFileNameException;
import Exceptions.InvalidInputException;
import Exceptions.MissingParameterException;
import Exceptions.NotDirectoryException;
import Exceptions.NotExistException;
import Exceptions.NotFileException;
import fileSystem.FileSystem;

/**
 * a class of man command
 * 
 * @author Yiqian Wu, Jingwei Wang
 */
public class Man implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection) {
    try {
      String result = getResult(fs, parameter);
      if (redirection == null)
        System.out.println(result);
      else {
        Echo echo = new Echo();
        echo.excute(fs, '"' + result + '"', redirection);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private String getResult(FileSystem fs, String parameter)
      throws CommandNotExitException, MissingParameterException,
      ClassNotFoundException,
      InstantiationException, IllegalAccessException {
    // man prints the documentation of a given command, so it needs a input
    if (parameter == null)
      throw new MissingParameterException("Missing parameter for man!");
    String command = parameter.trim();

    // breaks down the input parameter and recognize which command it is
    command = command.toLowerCase();
    command = command.substring(0, 1).toUpperCase() + command.substring(1);
    Class temp = Class.forName("commands." + command);

    // get the documentation of the input command
    Command instanceOfClss = (Command) temp.newInstance();
    return instanceOfClss.getDescription();
  }

  @Override
  public String getDescription() {
    return "Print documentation for a command.";
  }
}
