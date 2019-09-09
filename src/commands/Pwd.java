package commands;

import Exceptions.InvalidFileNameException;
import Exceptions.InvalidInputException;
import Exceptions.MissingParameterException;
import Exceptions.NotDirectoryException;
import Exceptions.NotExistException;
import Exceptions.NotFileException;
import fileSystem.*;

/**
 * a class of pwd command
 * 
 * @author Yiqian Wu, Jingwei Wang
 */
public class Pwd implements Command {
  @Override
  public void excute(FileSystem fs, String parameter, String redirection)
      throws MissingParameterException, InvalidFileNameException,
      NotFileException,
      NotExistException, NotDirectoryException, InvalidInputException {
    Directory cur = fs.getCur();
    String result = Getter.getPath(cur);
    if (redirection == null)
      System.out.println(result);
    else {
      Echo echo = new Echo();
      echo.excute(fs, '"' + result + '"', redirection);
    }
  }

  @Override
  public String getDescription() {
    return "Print the current working directory (including the" +
            " whole path).";
  }
}
