package commands;

import Exceptions.EmptyDirectoryStackException;
import fileSystem.*;

/**
 * a class of popd command
 * 
 * @author Yiqian Wu, Jingwei Wang
 */
public class Popd implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection) throws
    EmptyDirectoryStackException {
    // push the directory in to the dirStack then pop the last one
    DirectoryStack dirStack = fs.getDirStack();
    fs.setCur(dirStack.pop());
  }

  @Override
  public String getDescription() {
    return "Remove the top entry from the directory stack, " +
                       "and cd into it.";
  }
}
