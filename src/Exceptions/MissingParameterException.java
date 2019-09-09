package Exceptions;

/**
 * This class of exception is called when parameter is missing for some class
 * that needs one
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public class MissingParameterException extends Exception {

  public MissingParameterException(String message) {
    super(message);
  }
}
