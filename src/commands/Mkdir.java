package commands;

import Exceptions.*;
import fileSystem.*;

/**
 * a class of mkdir command
 * 
 * @author Yiqian Wu, Jingwei Wang
 */
public class Mkdir implements Command {

	@Override
	public void excute(FileSystem fs, String parameter, String redirection) 
			throws MissingParameterException {
		// judge whether the parameter is null, if it is then show "Missing
		// parameter for mkdir!"
		if (parameter == null)
			throw new MissingParameterException("Missing parameter "
					+ "for mkdir!");
		// split the parameter into several paths
		String[] paths = parameter.trim().split(" ");
		// for loop all the paths, if the path is not empty then create the
		// directory
		for (String path : paths) {
			if (!path.equals("")) {
				try {
					mkOneDir(fs, path);
					// the path is empty then print out
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}

	private void mkOneDir(FileSystem fs, String path) 
			throws FileExistException, InvalidFileNameException,
			NotExistException, NotDirectoryException, InvalidInputException {
		// initiate targetDir and newFileName
		String newFileName;
		Directory targetDir;

		// find the newFileName and targetDir
		String[] fileNames = path.split("/");
		// if the input path is just a file name
		if (fileNames.length == 1) {
			newFileName = fileNames[0];
			targetDir = fs.getCur();
		} else {
			newFileName = fileNames[fileNames.length - 1];
			// get the path of the directory that will contain the new file
			path = path.substring(0, path.length() - newFileName.length());
			if (path.length() > 1)
				path = path.substring(0, path.length() - 1);
			targetDir = Getter.getTargetDirectory(fs, path);
		}

		// check the validation of the new file's name
		if (!SytaxEvaluater.isValidFileName(newFileName))
			throw new InvalidFileNameException("Your input file name '" 
		+ newFileName + "'  is invalid!");
		// create the file with that name if the name is not duplicated
		if (targetDir.FindChildIndex(newFileName) == -1) {
			Directory newDir = new Directory(newFileName);
			targetDir.add(newDir);
		} else
			throw new FileExistException(
					"The file '" + newFileName + "' already " 
			+ "exists, please give another name!");
	}

	@Override
	public String getDescription() {
		return "Create directories, each of which may be relative "
				+ "to the current directory or may be a full path.";
	}
}
