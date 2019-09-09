package commands;

import Exceptions.InvalidInputException;
import Exceptions.MissingParameterException;
import Exceptions.NotDirectoryException;
import Exceptions.NotExistException;
import fileSystem.*;

/**
 * a class of pushd command
 * 
 * @author Yiqian Wu, Jingwei Wang
 */
public class Pushd implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection) throws NotExistException,
      NotDirectoryException, InvalidInputException, MissingParameterException {
    // if there is no parameter then show the MissingParameterException
    if (parameter == null)
      throw new MissingParameterException("Missing parameter for pushd!");
    // if parameter is not empty, then add the current directory
    Directory cur = fs.getCur();
    fs.setCur(Getter.getTargetDirectory(fs, parameter));
    fs.getDirStack().push(cur);
  }

  @Override
  public String getDescription() {
    return "Saves the current working directory by pushing "
        + "onto directory stack and then changes the new current working " +
        "directory to DIR.";
  }
}
