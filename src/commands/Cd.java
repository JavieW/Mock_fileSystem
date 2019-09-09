package commands;

import Exceptions.*;
import fileSystem.*;


/**
 * a class of cd command.
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public class Cd implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection) throws NotExistException,
      NotDirectoryException, InvalidInputException, MissingParameterException {
    if (parameter == null)
      throw new MissingParameterException("Missing parameter for cd!");
    String fileName = parameter.trim();
    fs.setCur(Getter.getTargetDirectory(fs, fileName));
  }

  @Override
  public String getDescription() {
    return "Change directory to DIR, which may be relative to"
        + "the current directory or may be a full path.";
  }
}
