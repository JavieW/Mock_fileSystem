package Exceptions;

/**
 * This class of exception is called when some input is not a valid input
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public class InvalidInputException extends Exception {

  public InvalidInputException(String message) {
    super(message);
  }
}
