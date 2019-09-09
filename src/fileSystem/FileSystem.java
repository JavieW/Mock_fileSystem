package fileSystem;

import java.util.ArrayList;

/**
 * A class of fileSystem.
 *
 * @author Jingwei Wang, Yiqian Wu, Yunxuan Zhang
 */
public class FileSystem {

  private Directory root;
  private Directory cur;
  private boolean exit;
  private DirectoryStack dirStack;
  private ArrayList<String> history;
  private static FileSystem singleReference;

  private FileSystem() {
    this.root = new Directory("/");
    this.cur = root;
    this.exit = false;
    this.dirStack = new DirectoryStack();
    this.history = new ArrayList<String>();
  }

  /**
   * A factory method that used to create a singleton file system.
   *
   * @return the single reference of the FileSystem.
   */
  public static FileSystem createSingleFS() {
    if (singleReference == null)
      singleReference = new FileSystem();
    return singleReference;
  }

  /**
   * get the command history of the fs.
   * 
   * @return a list of command histories.
   */
  public ArrayList<String> getHistory() {
    return this.history;
  }

  /**
   * set the input history to be the history of the fs
   * 
   * @param history, a ArrayList<String> that will be set as history
   */
  public void setHistory(ArrayList<String> history) {
    //this.history = history;
  }

  /**
   * get the directory stack of the fs.
   * 
   * @return a dirStack that inhabit in the fs.
   */
  public DirectoryStack getDirStack() {
    return dirStack;
  }

  /**
   * set the dirStack to be the directory stack of the fs
   * 
   * @param dirStack, a DirectoryStack that will be set as dirStack
   */
  public void setDirStack(DirectoryStack dirStack) {
    this.dirStack = dirStack;
  }

  /**
   * get the current directory.
   * 
   * @return the current directory.
   */
  public Directory getCur() {
    return cur;
  }

  /**
   * set the given directory to the current directory
   * 
   * @param dir, the directory that will be set to current dirctory.
   */
  public void setCur(Directory dir) {
    this.cur = dir;
  }

  /**
   * get the root directory of the fs.
   * 
   * @return the root directory of the fs.
   */
  public Directory getRoot() {
    return this.root;
  }

  /**
   * set the input root to the fs
   * 
   * @param root, a directory that will be set as a root of this fs
   */
  public void setRoot(Directory root) {
    this.root = root;
  }

  /**
   * exit the fs.
   */
  public void Exit() {
    exit = true;
  }

  /**
   * check whether the fs is exited.
   * 
   * @return true, if the fs is exited and false otherwise.
   */
  public boolean isExit() {
    return this.exit;
  }
}
