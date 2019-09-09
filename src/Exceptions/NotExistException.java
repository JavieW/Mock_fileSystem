package Exceptions;

/**
 * This class of exception is called when the target file does not exist
 * 
 * @author Jingwei Wang, Yiqian Wu
 */
public class NotExistException extends Exception {

  public NotExistException(String message) {
    super(message);
  }
}
