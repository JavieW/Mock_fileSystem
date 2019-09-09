package fileSystem;

import java.util.ArrayList;

/**
 * This class of Directory.
 *
 * @author Jingwei Wang, Yuming Liu
 */
public class Directory extends StemFile {

  private ArrayList<StemFile> child = new ArrayList<StemFile>();

  /**
   * create a directory with the given name.
   * 
   * @param name, a string represent the name of the directory
   */
  public Directory(String name) {
    super(name);
  }

  /**
   * Adds a StemFile as its child
   * 
   * @param st, A StemFile to be added as a child of this Directory.
   */
  public void add(StemFile st) {
    this.child.add(st);
    st.setParent(this);
  }

  /**
   * get the children of the directory.
   * 
   * @return a list of all children of the dirctory.
   */
  public ArrayList<StemFile> getChild() {
    return this.child;
  }

  /**
   * Find the index of a child of current Directory with the given name.
   * 
   * @param name, A String represent the name of a child of the current
   *              Directory.
   * @return the index of the child with the given name.
   */
  public int FindChildIndex(String name) {
    int result = -1;
    for (int i = 0; i < this.child.size(); i++) {
      if (this.child.get(i).getName().equals(name))
        result = i;
    }
    return result;
  }

  /**
   * delete a child of that directory
   * 
   * @param st, a StemFile that need to be deleted
   */
  public void delete(StemFile st) {
    child.remove(st);
  }

  /**
   * Copy that directory
   */
  public Directory copy() {
    Directory myCopy = new Directory(this.getName());
    for (StemFile st : this.child) {
      myCopy.add(st.copy());
    }
    return myCopy;
  }
}
