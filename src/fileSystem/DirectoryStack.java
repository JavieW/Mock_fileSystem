package fileSystem;

import java.util.ArrayList;
import Exceptions.EmptyDirectoryStackException;

/**
 * A class of directory stack.
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public class DirectoryStack {

  private ArrayList<Directory> dirStack;

  /**
   * construct the directory stack.
   */
  public DirectoryStack() {
    dirStack = new ArrayList<Directory>();
  }

  /**
   * add a dirctory on the top of the stack
   */
  public void push(Directory dir) {
    dirStack.add(dir);
  }

  /**
   * removes and returns the directory on the top of Stack
   * 
   * @return a Directory
   */
  public Directory pop() throws EmptyDirectoryStackException {
    if (dirStack.size() == 0)
      throw new EmptyDirectoryStackException("The directory stack is empty!");
    else
      return dirStack.remove(0);
  }

  public boolean isEmpty() {
    return dirStack.size() == 0;
  }
}
