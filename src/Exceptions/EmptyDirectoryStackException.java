package Exceptions;

/**
 * This class of exception is initiated when there is no more Directories in the stack
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public class EmptyDirectoryStackException extends Exception {

  public EmptyDirectoryStackException(String message) {
    super(message);
  }
}
