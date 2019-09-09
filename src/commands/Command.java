package commands;

import java.io.IOException;
import java.net.MalformedURLException;
import Exceptions.CommandNotExitException;
import Exceptions.EmptyDirectoryStackException;
import Exceptions.FileExistException;
import Exceptions.InvalidFileNameException;
import Exceptions.InvalidInputException;
import Exceptions.MissingParameterException;
import Exceptions.NotDirectoryException;
import Exceptions.NotExistException;
import Exceptions.NotFileException;
import fileSystem.FileSystem;

/**
 * a interface of commands.
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public interface Command {

  /**
   * execute the command.
   * 
   * @param fs, the single reference of the FileSystem
   * @param parameter, a string of paths or fileNames or the combination of
   *        both paths and fileNames.
   * @throws MissingParameterException, if the required parameter is missing
   * @throws InvalidFileNameException, if the target fileName is invalid
   * @throws NotFileException, if the target StemFile is not a file
   * @throws NotExistException, if the target StemFile is not exist
   * @throws NotDirectoryException, if the target StemFile is not a directory
   * @throws InvalidInputException, if the input path is invalid
   * @throws FileExistException, if the file with input fileName already exist
   * @throws CommandNotExitException, if the input command is not exist
   * @throw EmptyDirectoryStackException, if the directory stack is empty
   * @throws MalformedURLException , if some thing wrong with url
   * @throws IOException, if some thing wrong with the txt file in the url
   */
  public void excute(FileSystem fs, String parameter, String redirection)
    throws MissingParameterException, InvalidFileNameException,
    NotFileException, NotExistException, NotDirectoryException,
    InvalidInputException, FileExistException, CommandNotExitException,
    EmptyDirectoryStackException, MalformedURLException, IOException;

  /**
   * print the command description.
   */
  public String getDescription();
}
