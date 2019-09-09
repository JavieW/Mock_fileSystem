package commands;

import Exceptions.*;
import fileSystem.*;

/**
 * a class of ls command.
 * 
 * @author JingWei Wang, Yuming Liu, Yiqian Wu
 */
public class Ls implements Command {

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

	private String getResult(FileSystem fs, String parameter) {
		boolean R = false;
		String result = "";
		if (parameter != null && parameter.startsWith("-R")) {
			R = true;
			parameter = parameter.substring(2).trim();
		}

		if (parameter == null || parameter.equals(""))
			result = result + "\n" + "\n" + getContents(fs.getCur(), R);
		else {	
			String[] paths = parameter.split(" ");
			for (String path : paths) {
				try {
					StemFile st = Getter.getTargetStemFile(fs, path);
					result = result + "\n" + "\n" + getContents(st, R);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		return result.trim();
	}

	private String getContents(StemFile st, boolean R) {
		String result = st.getName();
		if (st instanceof Directory) {
			result = result + ":";
			for (StemFile child : ((Directory) st).getChild()) {
				result = result + "\n" + child.getName();

				if (R) {
					result = result + "\n" + getContents(child, R);
				}
			}
		}
		return result;
	}

	@Override
	public String getDescription() {
		return "Print the contents (file or directory) " + 
	"of the current directory or of a path.";
	}
}
