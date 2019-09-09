package Exceptions;

/**
 * This class is called when the file already exist in the system
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public class FileExistException extends Exception {

  public FileExistException(String message) {
    super(message);
  }
}
