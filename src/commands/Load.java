package commands;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import Exceptions.*;
import fileSystem.*;
/**
 * a class of load command
 * 
 * @author Jingwei Wang
 *
 */
public class Load implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection)
      throws FileNotFoundException, MissingParameterException {
    
    // parametre is required for this command
    if (parameter == null)
      throw new MissingParameterException("Missing file name for load");
    FileInputStream fStream = new FileInputStream(parameter);
    BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

    // 
    try {
      String content = br.readLine();
      String pathOfCur = br.readLine();
      String pathOfDirs = br.readLine();
      String histories = br.readLine();

      Directory root = (Directory) loadContents(content);
      fs.setRoot(root);

      Directory cur = (Directory) loadCur(fs, pathOfCur);
      fs.setCur(cur);

      DirectoryStack dirSt = loadDirStack(fs, pathOfDirs);
      fs.setDirStack(dirSt);

      ArrayList<String> history = loadHistory(fs, histories);
      fs.setHistory(history);

    } catch (Exception e) {
      e = new InvalidFileException("The manually modified " +
                                    "file is invalid!");
      System.out.println(e);
    }
  }

  private StemFile loadContents(String content) {
    StemFile result;
    
    // taking useful info from content
    int lengthOfContent = content.length();
    int indexOfLSB = content.indexOf("[");
    String fileName = content.substring(0, indexOfLSB);
    String type = content.substring(lengthOfContent - 1);

    // if the "type" of the content is f, then set the input contents in
    //content as the value of the new file
    if (type.equals("f")) {
      result = new File(fileName);
      String value = content.substring(indexOfLSB + 1, lengthOfContent - 2);
      ((File) result).setValue(value);
    
    // else it must be a directory the the children of the directory is in
    } else {
      result = new Directory(fileName);
      String children = content.substring(indexOfLSB + 1, lengthOfContent - 2);
      ArrayList<String> allChildren = getChildContent(children);
      for (String child : allChildren) {
        if (!child.equals(""))
          ((Directory) result).add(loadContents(child));
      }
    }
    return result;
  }
  
  private ArrayList<String> getChildContent(String children) {
    ArrayList<String> result = new ArrayList<String>();
    int braketCounter = 0;
    int left = 0;
    int right = 0;
    int i = 0;
    while (i<children.length()) {
      if (braketCounter == 0) {
        int j = children.indexOf("[", left);
        if (j == -1)
          break;
        i = j+1;
        braketCounter = 1;
      }
      if (("" + children.charAt(i)).equals("["))
        braketCounter++;
      if (("" + children.charAt(i)).equals("]"))
        braketCounter--;
      if (braketCounter == 0) {
        right = i+1;
        result.add(children.substring(left, right+1));
        left = right+2;
      }
      i++;
    }
    return result;
  }

  private Directory loadCur(FileSystem fs, String pathOfCur)
      throws NotExistException, NotDirectoryException, InvalidInputException {
    return Getter.getTargetDirectory(fs, pathOfCur);
  }

  private DirectoryStack loadDirStack(FileSystem fs, String pathOfDirs)
      throws NotExistException, NotDirectoryException, InvalidInputException {
    DirectoryStack result = new DirectoryStack();
    String[] paths = pathOfDirs.split(",");
    for (String path : paths) {
      Directory nextDir = Getter.getTargetDirectory(fs, path);
      result.push(nextDir);
    }
    return result;
  }

  private ArrayList<String> loadHistory(FileSystem fs, String histories) {
    ArrayList<String> result = new ArrayList<String>();
    String[] allHistories = histories.split(",");
    for (String history : allHistories)
      result.add(history);
    String curCommand = fs.getHistory().get(0);
    result.add(0, curCommand);
    return result;
  }

  @Override
  public String getDescription() {
    return "Load the contents of the FileNameand reinitialize everything"
        + " that was saved previously into the FileName. ";
  }

}
