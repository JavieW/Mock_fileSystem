package commands;

import java.util.ArrayList;
import Exceptions.InvalidFileNameException;
import Exceptions.InvalidInputException;
import Exceptions.MissingParameterException;
import Exceptions.NotDirectoryException;
import Exceptions.NotExistException;
import Exceptions.NotFileException;
import fileSystem.*;
/**
 * a class of tree command
 * 
 * @author Jingwei Wang
 *
 */
public class Tree implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection)
      throws InvalidInputException, NotExistException, NotDirectoryException,
      InvalidFileNameException, NotFileException, MissingParameterException {
    // parse the tree from the root
    Directory root = fs.getRoot();
    String result = "/" + getResult(root, "\n\t");
    if (redirection == null)
      System.out.println(result);
    else {
      Echo echo = new Echo();
      echo.excute(fs, '"' + result + '"', redirection);
    }
  }

  private String getResult(Directory root, String indentation) {
    String result = "";
    ArrayList<StemFile> children = root.getChild();
    // for each child print its name in each line with same indentation
    for (StemFile child : children) {
      result = result + indentation + child.getName();
      // if the child is a directory, recursion!
      if (child instanceof Directory) {
        // when we get deeper, add more tab in front.
        result = result + getResult((Directory) child, indentation + "\t");
      }
    }
    return result;
  }

  @Override
  public String getDescription() {
    return "Display the entire filesystem as a tree, " +
            "from the root directory";
  }
}
