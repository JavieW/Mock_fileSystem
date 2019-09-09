package Exceptions;

/**
 * This class of exception is called when some file name is not a valid name
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public class InvalidFileNameException extends Exception {

  public InvalidFileNameException(String Message) {
    super(Message);
  }
}
