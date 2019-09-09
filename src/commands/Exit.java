package commands;

import fileSystem.FileSystem;

/**
 * a class of exit command
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public class Exit implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection) {
    fs.Exit();
  }

  @Override
  public String getDescription() {
    return "Quit the program";
  }
}
