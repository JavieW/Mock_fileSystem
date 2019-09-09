package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import Exceptions.*;
import fileSystem.*;
/**
 * a class of get command
 * 
 * @author Jingwei Wang
 *
 */
public class Get implements Command {

  @Override
  public void excute(FileSystem fs, String parameter, String redirection)
      throws InvalidFileNameException, IOException {

    // get the file name from the input url
    String[] parts = parameter.split("/");
    String fileName = parts[parts.length - 1];
    if (!SytaxEvaluater.isValidFileName(fileName))
      throw new InvalidFileNameException("'" + fileName +
                                          "' is a invalid file name");

    // connect the url and reader the file in that url
    URL url = new URL(parameter);
    URLConnection urlc = url.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader
                                            (urlc.getInputStream()));

    // get the data from the url
    String aline;
    String data = "";
    while ((aline = in.readLine()) != null)
      data = data + "\n" + aline;

    // create a file with fileName and data in current directory
    File f = new File(fileName);
    f.setValue(data.trim());
    fs.getCur().add(f);
  }

  @Override
  public String getDescription() {
    return " Retrieve the file at that URL and add it to the " +
           "current working directory.";
  }

}
