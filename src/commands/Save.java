package commands;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import Exceptions.*;
import fileSystem.*;
/**
 * a class of save command
 * 
 * @author Jingwei Wang
 *
 */
public class Save implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection)
      throws FileNotFoundException, UnsupportedEncodingException,
      EmptyDirectoryStackException,
      MissingParameterException {
    // check the existence of file name
    if (parameter == null)
      throw new MissingParameterException("Missing file name for save");

    Directory root = fs.getRoot();
    Directory cur = fs.getCur();
    DirectoryStack dirStack = fs.getDirStack();
    ArrayList<String> histories = fs.getHistory();

    //create a file in our computer to store these data
    PrintWriter writer = new PrintWriter(parameter);
    writer.println(recordContents(root));
    writer.println(recordCur(cur));
    writer.println(recordDirStack(dirStack));
    writer.println(recordHistory(histories));
    writer.close();
  }

  private String recordContents(StemFile sf) {
    String result = "";
    String type;
    if (sf instanceof File) {
      type = "f";
      result = ((File) sf).getValue();
    } else {
      type = "d";
      ArrayList<StemFile> children = ((Directory) sf).getChild();
      for (StemFile child : children)
        result = result + "," + recordContents(child);

      if (result.length() != 0)
        result = result.substring(1);

    }
    result = sf.getName() + "[" + result + "]" + type;
    return result;
  }

  private String recordCur(Directory cur) {
    return Getter.getPath(cur);
  }

  private String recordDirStack(DirectoryStack dirStack) throws
  EmptyDirectoryStackException {
    String result = "";
    while (!dirStack.isEmpty()) {
      Directory nextDir = dirStack.pop();
      result = Getter.getPath(nextDir) + "," + result;
    }
    return result;
  }

  private String recordHistory(ArrayList<String> histories) {
    String result = "";
    for (String history : histories)
      result = result + "," + history;
    if (!result.equals(""))
      result.substring(1);
    return result;
  }

  @Override
  public String getDescription() {
    return "Write a file to recored the data of the Jshell "
        + "so that next time you load the file you can " +
        "continous on where you left out";
  }

}
