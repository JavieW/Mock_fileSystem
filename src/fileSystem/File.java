package fileSystem;

/**
 * A class of file.
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public class File extends StemFile {

  private String value;

  /**
   * create a file with the given name.
   * @param name, a string represent the name of the file.
   */
  public File(String name) {
    super(name);
  }

  /**
   * set the given string value to the file.
   * @param s, a string value that will be set to the file
   */
  public void setValue(String s) {
    this.value = s;
  }

  /**
   * get the string value of the file.
   * @return a string value of the file.
   */
  public String getValue() {
    return this.value;
  }

  /**
   * append the given string to the end the file's string value.
   * @param s, a string that will be added to the file's string value.
   */
  public void addValue(String s) {
    if (this.value == null)
      this.value = s;
    else
      this.value = this.value + s;
  }
  
  /**
   * copy that file.
   */
  @Override
  public File copy() {
	  File myCopy = new File(this.getName());
	  myCopy.setValue(this.value);
	  return myCopy;
  }
}
