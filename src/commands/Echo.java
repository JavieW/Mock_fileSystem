package commands;

import Exceptions.*;
import fileSystem.*;

/**
 * a class of echo command.
 * 
 * @author Jingwei Wang, Yuming Liu
 */

public class Echo implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection)
      throws InvalidInputException, NotExistException, NotDirectoryException,
      InvalidFileNameException, NotFileException, MissingParameterException {

    // check the validation of the parameter
    if (parameter == null)
      throw new MissingParameterException("Missing parameter for echo!");
    if (!(parameter.startsWith("" + '"') && parameter.endsWith("" + '"')))
      throw new InvalidInputException("You must surround your string by" +
                                      " double quotes!");

    // if out file is not given
    if (redirection == null) {
      System.out.println(parameter.substring(1, parameter.length() - 1));
    } else {
      // get the reference of the out file
      String outFileName = redirection.substring(2).trim();
      File file = this.getReferenceOfFile(fs, outFileName);

      // append or overwrite the file with the input parameter
      if (redirection.startsWith(">>"))
        file.addValue(parameter.substring(1, parameter.length() - 1));
      else if (redirection.startsWith(">"))
        file.setValue(parameter.substring(1, parameter.length() - 1));
      else
        throw new InvalidInputException(
            "You only have a '>' or a '>>'" + "as a symbol in your input!");
    }
  }

  /*
   * This is echo's unique path handling mechanism,
   * differ from others Since it may create a file!
   */
  private File getReferenceOfFile(FileSystem fs, String fileName) throws
      NotExistException, NotDirectoryException, InvalidInputException,
      InvalidFileNameException, NotFileException {

    File targetFile = null;
    Directory targetDir;
    String targetFileName;

    // get the target file name (and target directory)
    String[] path = fileName.split("/");
    if (path.length == 1) {
      targetDir = fs.getCur();
      targetFileName = fileName;
    } else {
      targetFileName = path[path.length - 1];
      String targetPath = fileName.substring(0,
                          fileName.length() - targetFileName.length());
      // in case the path has the form "/a"
      if (targetPath.length() > 1)
        targetPath = targetPath.substring(0, targetPath.length() - 1);
      targetDir = Getter.getTargetDirectory(fs, targetPath);
    }

    // create or get a file based on the valid file name,
    // throw a error if the target is a directory.
    if (!SytaxEvaluater.isValidFileName(targetFileName))
      throw new InvalidFileNameException(
          "Your input file name '" + targetFileName + "' is invalid!");
    int i = targetDir.FindChildIndex(targetFileName);
    if (i == -1) {
      targetFile = new File(targetFileName);
      targetDir.add(targetFile);
    } else {
      if (targetDir.getChild().get(i) instanceof File)
        targetFile = (File) targetDir.getChild().get(i);
      else
        throw new NotFileException("'" + targetFileName + "' is not a file!");
    }
    return targetFile;
  }

  @Override
  public String getDescription() {
    return "Print STRING on the shell or overwrite a file by it or" +
            "append it to a file.";
  }
}
