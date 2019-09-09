package Exceptions;

/**
 * 
 * This exception is called when the target stemFile is not a directory
 * 
 * @author Jingwei Wang, Yiqian Wu
 *
 */
public class NotDirectoryException extends Exception {

  public NotDirectoryException(String message) {
    super(message);
  }
}
