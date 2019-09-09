package commands;

import java.util.ArrayList;

import Exceptions.InvalidFileNameException;
import Exceptions.InvalidInputException;
import Exceptions.MissingParameterException;
import Exceptions.NotDirectoryException;
import Exceptions.NotExistException;
import Exceptions.NotFileException;
import fileSystem.*;

/**
 * a class of history command
 * 
 * @author Jingwei Wang, Yuming Liu
 */
public class History implements Command {

	@Override
	public void excute(FileSystem fs, String parameter, String redirection) 
			throws InvalidInputException, NotExistException,
			NotDirectoryException, InvalidFileNameException, 
			NotFileException, MissingParameterException {
		String result = getResult(fs, parameter);
		if (redirection == null)
			System.out.println(result);
		else {
			Echo echo = new Echo();
			echo.excute(fs, '"' + result + '"', redirection);
		}
	}
	
	private String getResult(FileSystem fs, String parameter) 
			throws InvalidInputException {
		// get the history and initiate result
		ArrayList<String> history = fs.getHistory();
		String result = "";

		// no parameter given means display all history
		if (parameter == null) {
			for (int i = 0; i < history.size(); i++)
				result = result + "\n" + (i + 1) + ". " + history.get(i);
			// print the number of histories desired by the user
		} else {
			String inputNum = parameter.trim();
			if (SytaxEvaluater.isInteger(inputNum)) {
				int size = history.size();
				int num = Integer.parseInt(inputNum);
				for (int i = 0; i < Math.min(num, size); i++)
					result = result + "\n" + (i + 1) + ". " + history.get(i);
			} else
				throw new InvalidInputException("'" + inputNum 
						+ "' is not a integer!");
		}
		return result;
	}

	@Override
	public String getDescription() {
		return "Print out recent commands based on "
				+ "the input number.";
	}
}
