package Exceptions;

/**
 * 
 * This is an exception that will be thrown when your input command is not exist.
 * 
 * @author Jingwei Wang, Yuming Liu
 *
 */
public class CommandNotExitException extends Exception {

  public CommandNotExitException(String Message) {
    super(Message);
  }
}
