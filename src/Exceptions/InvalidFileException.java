package Exceptions;

/**
 * This exception will be called if your read a invalid file
 * 
 * @author Jingwei Wang
 */
public class InvalidFileException extends Exception {

  public InvalidFileException(String message) {
    super(message);
  }
}
