package fileSystem;

import Exceptions.*;

/**
 * this is a helper class used to handle path.
 * 
 * @author Jingwei Wang
 *
 */
public class Getter {

  /**
   * Get the directory of the input file system base on the input file name
   * or a path, but not check into it.
   * 
   * @param fs, a FileSystem where the cd command is called
   * @param fileName, a String of a file's name or the whole path
   * @throws InvalidInputException, if the input path is invalid
   * @throws NotDirectoryException, if the target StemFile is not a directory
   * @throws NotExistException, if the target StemFile is not exist
   */
  public static Directory getTargetDirectory(FileSystem fs, String stringPath)
      throws NotExistException, NotDirectoryException, InvalidInputException {
    // initialize the target directory
    Directory target = fs.getCur();
    // if the input fileName is a full path then it begins with "/", so
    // change the target directory to root and slice the first "/".
    if (stringPath.startsWith("/")) {
      target = fs.getRoot();
      stringPath = stringPath.substring(1);
    }
    // if currently file name is not an empty string we get the target
    // directory by path
    if (!stringPath.equals("")) {
      String[] path = stringPath.split("/");
      for (String nextFileName : path) {
        // check the validation of the input parameter
        if (!SytaxEvaluater.isValidInput(nextFileName))
          throw new InvalidInputException("The input path '" + nextFileName 
              + "' is invalid!");

        if (nextFileName.equals("..")) {
          // if the current Directory is not root
          if (target.getParent() != null)
            target = target.getParent();
        } else if (nextFileName.equals(".")) {
          // do nothing, this is here just so this case is covered
        } else {
          int childIndex = target.FindChildIndex(nextFileName);
          // if there is a child of the current directory
          // named fileName
          if (childIndex != -1) {
            // if the child is a Directory instead of file
            StemFile child = target.getChild().get(childIndex);
            if (child instanceof Directory)
              target = (Directory) child;
            else
              throw new NotDirectoryException(
                  "Path can not" + "contains a file '" + nextFileName + "' !");
          } else
            throw new NotExistException(
                "No such file or " + "directory as '" + nextFileName + "' !");
        }
      }
    }
    return target;
  }

  /**
   * get the target StemFile by a file name or a path
   * 
   * @param fs, the FileSystem
   * @param path, a string of file name or path
   * @return a the reference of the StemFile of the input path
   * @throws NotExistException, if the target StemFile is not exist
   * @throws NotDirectoryException, if the target StemFile is not a directory
   * @throws InvalidInputException, if the input path is invalid
   */
  public static StemFile getTargetStemFile(FileSystem fs, String path)
      throws NotExistException, NotDirectoryException, InvalidInputException {
    // get the target name from the path
    String[] fileNames = path.split("/");
    String targetName = fileNames[fileNames.length - 1];

    // get the parent directory of the target StemFile
    path = path.substring(0, path.length() - targetName.length());
    if (path.length() > 1)
      path = path.substring(0, path.length() - 1);
    Directory parentDirectory = getTargetDirectory(fs, path);

    if (targetName.equals(""))
      return parentDirectory;

    // get the target StemFile from the parent directory
    int i = parentDirectory.FindChildIndex(targetName);
    if (i == -1)
      throw new NotExistException("No such file or " + "directory as '" 
    + targetName + "' !");
    else
      return parentDirectory.getChild().get(i);
  }

  /**
   * get the full path of the input directory.
   * 
   * @param dir, a Directory that we get the path for.
   * @return a String representation of the path.
   */
  public static String getPath(Directory dir) {
    // initialize a string result
    String result = "";
    // if currently in root
    if (dir.getParent() == null) {
      result = "/";
    } else {
      // while cur is not a root, add the current directory name in front
      while (dir.getParent() != null) {
        result = "/" + dir.getName() + result;
        dir = dir.getParent();
      }
    }
    return result;
  }
}
