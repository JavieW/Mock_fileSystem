package commands;

import Exceptions.*;
import fileSystem.*;


/**
 * a class of cat command.
 * 
 * @author Jingwei Wang
 * @author Yuming Liu
 * @author Yunxuan Zhang
 */
public class Cat implements Command {

	@Override
	public void excute(FileSystem fs, String parameter, String redirection)
			throws MissingParameterException, InvalidFileNameException,
			NotFileException, NotExistException,
			NotDirectoryException, InvalidInputException {
		String result = getResult(fs, parameter);
		if (redirection == null)
			System.out.println(result);
		else {
			Echo echo = new Echo();
			echo.excute(fs, '"' + result + '"', redirection);
		}
	}

	private String getResult(FileSystem fs, String parameter) 
			throws MissingParameterException{

		// cat command needs a parameter to function,
		// give message if its missing
		if (parameter == null)
			throw new MissingParameterException("Missing parameter for cat!");

		String result = "";
		String[] fileNames = parameter.trim().split(" ");

		// show each file's content in fileNames
		for (String fileName : fileNames) {
			try {
				StemFile sf = Getter.getTargetStemFile(fs, fileName);
				if (sf instanceof File)
					result = result + (((File) sf).getValue());
				else
					throw new NotFileException("'" + fileName + 
							"' is not a file!");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return result.trim();
	}

	@Override
	public String getDescription() {
		return "Display the contents of FILE1 and other files" + 
		"(i.e. File2 ....)" + "concatenated in the shell.";
	}
}
