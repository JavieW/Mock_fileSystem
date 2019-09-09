package fileSystem;

import java.util.ArrayList;

/**
 * A class of StemFile which is defined as the unit of fileSystem
 * 
 * @author Jingwei Wang, Yuming Liu
 */
abstract public class StemFile {

  private String name;
  private Directory parent;

  /**
   * create a StemFile with the given name.
   * 
   * @param name, a string represent a name of the StemFile.
   */
  public StemFile(String name) {
    this.name = name;
  }

  /**
   * get the name of the StemFile
   * 
   * @return the name of the StemFile
   */
  public String getName() {
    return name;
  }

  /**
   * set the name of the StemFile as the given newName
   * 
   * @param newName, a string represent the new name of the StemFile
   */
  public void setName(String newName) {
    name = newName;
  }

  /**
   * get the reference of the StemFile's parent directory.
   * 
   * @return a directory that contains that StemFile.
   */
  public Directory getParent() {
    return parent;
  }

  /**
   * set the given directory to be the parent of the StemFile
   * 
   * @param parent, a directory where the StemFile inhabits in.
   */
  public void setParent(Directory parent) {
    this.parent = parent;
  }

  /**
   * detach that stem file from its parent.
   */
  public void detach() {
    Directory myParent = this.getParent();
    myParent.delete(this);
    this.parent = null;
  }

  /**
   * copy the stem file.
   * 
   * @return a StemFile that copied from that stem file.
   */
  public abstract StemFile copy();
}
