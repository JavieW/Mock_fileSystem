package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import fileSystem.File;

public class FileTest {

  private File f;

  @Before
  public void setup() {
    f = new File("1");
    f.setValue("2");
  }

  @Test
  public void testCopy0() {
    File cp = f.copy();
    assertEquals(f.getName(), cp.getName());
    assertEquals(f.getValue(), cp.getValue());
  }

}
