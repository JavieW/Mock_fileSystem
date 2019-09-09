package commands;

import java.util.ArrayList;
import Exceptions.*;
import fileSystem.*;
/**
 * a class of find command
 * 
 * @author Jingwei Wang
 *
 */
public class Find implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection)
      throws InvalidInputException, NotExistException, NotDirectoryException,
      InvalidFileNameException, NotFileException, MissingParameterException {

    String result = getResult(fs, parameter);
    if (redirection == null)
      System.out.println(result);
    else {
      Echo echo = new Echo();
      echo.excute(fs, '"' + result + '"', redirection);
    }

  }

  private String getResult(FileSystem fs, String parameter) throws
    InvalidInputException {
    // initiate result
    String result = "";

    // split the input in to three parts
    int indexOfType = parameter.indexOf("-type");
    int indexOfName = parameter.indexOf("-name");
    if (indexOfType == -1 || indexOfName == -1)
      throw new InvalidInputException("you should give a -type and " +
                                      "a -name as part of input");
    String allpath = parameter.substring(0, indexOfType);
    String type = parameter.substring(indexOfType, indexOfName);
    String name = parameter.substring(indexOfName);

    // get the paths, type and name from the three parts of input
    String[] paths = allpath.trim().split(" ");
    String[] tmpType = type.trim().split(" ");
    String[] tmpName = name.trim().split(" ");
    type = tmpType[tmpType.length - 1];
    name = tmpName[tmpName.length - 1];

    // concatenate results from each valid path
    for (String path : paths) {
      try {
        Directory targetDir = Getter.getTargetDirectory(fs, path);
        result = (result + "\n" + findInDir(targetDir, name, type)).trim();
      } catch (Exception e) {
        System.out.println(e);
      }
    }
    return result.trim();
  }

  private String findInDir(Directory targetDir, String name, String type) {

    String result = "";
    ArrayList<StemFile> children = targetDir.getChild();

    // go through each child of the directory
    for (StemFile child : children) {

      // if child is a directory have a recursive call
      if (child instanceof Directory) {
        if (type.equals("d") && child.getName().equals(name)) {
          // show the full path to distinguish the
          // quantified directories since they all have same name
          String relativePath = Getter.getPath(targetDir);
          if (relativePath.equals("/"))
            result = result + "\n" + relativePath + name;
          else
            result = result + "\n" + relativePath + "/" + name;
        }
        // here is the recursive call
        result = (result + "\n" + findInDir((Directory) child, name, type)).trim();

        // if child is a file then no recursive calls
      } else {
        if (type.equals("f") && child.getName().equals(name)) {
          // for the same reason to show full path of the files
          String relativePath = Getter.getPath(targetDir);
          if (relativePath.equals("/"))
            result = result + "\n" + relativePath + name;
          else
            result = result + "\n" + relativePath + "/" + name;
        }
      }

    }
    return result.trim();
  }

  @Override
  public String getDescription() {
    return "Find all files or directories based on type and name " +
            "in the given paths";
  }

}
