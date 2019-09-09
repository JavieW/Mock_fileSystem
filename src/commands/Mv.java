package commands;

import Exceptions.*;
import fileSystem.*;
/**
 * a class of mv command
 * 
 * @author Jingwei Wang
 *
 */
public class Mv implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection)
      throws MissingParameterException, NotExistException,
      NotDirectoryException,
      InvalidInputException {
    // check the validation for input parameter
    if (parameter == null)
      throw new MissingParameterException("Missing parameter for mv!");
    int indexOfSpace = parameter.indexOf(" ");
    if (indexOfSpace != -1) {
      // get two paths
      String oldPath = parameter.substring(0, indexOfSpace).trim();
      String newPath = parameter.substring(indexOfSpace).trim();

      // get two stem files
      StemFile oldSt = Getter.getTargetStemFile(fs, oldPath);
      Directory newDir = Getter.getTargetDirectory(fs, newPath);

      // copy the oldSt file then put in to newSt if it's a directory
      oldSt.detach();
      ((Directory) newDir).add(oldSt);
    } else
      throw new MissingParameterException("The new path is missing!");
  }

  @Override
  public String getDescription() {
    return "Move item in OLDPATH to NEWPATH. If NEWPATH is a directory, "
        + "move the item into the directory.";
  }
}
