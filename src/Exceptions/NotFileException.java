package Exceptions;

public class NotFileException extends Exception {
  /**
   * This exception is called when the target stemFile is not a file
   * 
   * @author Yuxuan Zhang, Yiqian Wu
   */
  public NotFileException(String message) {
    super(message);
  }

}
