package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import fileSystem.Directory;
import fileSystem.File;

public class DirectoryTest {

  private Directory d;
  private Directory b;

  @Before
  public void setup() {
    d = new Directory("1");
    d.add(new File("a"));
    b = new Directory("b");
    d.add(b);
    b.add(new File("c"));
  }

  @Test
  public void testCopy0() {
    Directory cp = d.copy();
    assertEquals(d.getName(), cp.getName());
    assertEquals(d.getChild().get(0).getName(),
                  cp.getChild().get(0).getName());
  }

  @Test
  public void testDetach0() {
    b.detach();
    assertTrue(b.getParent() == null);
  }
}
