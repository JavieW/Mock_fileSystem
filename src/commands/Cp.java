package commands;

import Exceptions.InvalidInputException;
import Exceptions.MissingParameterException;
import Exceptions.NotDirectoryException;
import Exceptions.NotExistException;
import fileSystem.*;

/**
 * a class of cp command
 * 
 * @author Jingwei Wang
 *
 */
public class Cp implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection)
      throws MissingParameterException, NotExistException, 
      NotDirectoryException, InvalidInputException {

    // check the validation for input parameter
    if (parameter == null)
      throw new MissingParameterException("The parameter " + 
                                          "for cp is missing");
    int i = parameter.indexOf(" ");
    if (i != -1) {
      // get two paths
      String oldPath = parameter.substring(0, i).trim();
      String newPath = parameter.substring(i).trim();

      // get two stem files
      StemFile oldSt = Getter.getTargetStemFile(fs, oldPath);
      Directory newDir = Getter.getTargetDirectory(fs, newPath);

      // copy the oldSt file then put in to newSt if it's a directory
      StemFile copy = oldSt.copy();
      int j = newDir.FindChildIndex(copy.getName());
      if (j == -1)
        newDir.add(copy);
      else
        newDir.getChild().set(j, copy);
    } else
      throw new MissingParameterException("The new path is missing!");
  }

  @Override
  public String getDescription() {
    return "Copy item in OLDPATH. If NEWPATH is a directory, " 
              + "put the copy into the directory.";
  }
}
