package fileSystem;

/**
 * A class that used to check the validation of inputs.
 * @author Jingwei Wang
 */
public class SytaxEvaluater {

  /**
   * Check whether the FileName is valid.
   * 
   * @param fileName, a string represent a file's name
   * @return true, if the file name is valid
   */
  public static boolean isValidFileName(String fileName) {
    return (isValidInput(fileName) && (fileName.indexOf(".") == -1));
  }

  /**
   * Check whether the inputs is valid.
   * 
   * @param input, as string input given to execute a command
   * @return true, if the input is valid
   */
  public static boolean isValidInput(String input) {
    boolean result = true;
    String invalidChar = "/ !@#$%^&*(){}~|<>?";
    for (char c : input.toCharArray()) {
      if (invalidChar.indexOf(c) != -1) {
        result = false;
        break;
      }
    }
    return result;
  }
  
  /**
   * Check whether the inputs is an integer.
   * 
   * @param integer, as string integer given to execute a command
   * @return true, if the input is an integer.
   */
  public static boolean isInteger(String integer) {
    boolean result = true;
    String integerNum = "0123456789";
    for (char c : integer.toCharArray()) {
      if (integerNum.indexOf(c) == -1) {
        result = false;
        break;
      }
    }
    return result;
  }
}
