package part02;

/**
 * This class represents ...a console-based menu
 * @author Paul Sage, Andrew Ellis
 * @version V1.2
 */
public class Menu {
	private String options[];	// array of strings representing user options
	private String title;		// menu title
	private String extraDetails; //used to store extra details to display in the menu
	/**
	 * Constructor for class
	 * @param title - the menu title
	 * @param options - the options for user selection
	 */
	public Menu(String title, String options[]) {
		this.title = title;
		copyOptions(options);
	}
	
	/**
	 * Requests and read a user choice
	 * @return - the user choice
	 */
	public int getChoice() {
		display();
		System.out.print("> Enter choice: ");
		int choice = GetInput.getIntInput();
		return choice;
	}
	
	/**
	 * displays the menu title followed by the user
	 * option for selection
	 */
	private void display() {
		if (title != null && options !=null) {
			// title and options are valid
			// display title and underline with '+'
			System.out.println("");
			System.out.println(title);
			for(int i=0;i<title.length();i++) {
				System.out.print("+");
			}
			System.out.println("\n");
			if(extraDetails != null) {
				System.out.print(extraDetails);
			}

			// display the menu options
			// prefix each with an int starting at 1
			int count = 1;
			for(String str : options) {
				System.out.println(count+". "+str);
				count++;
			}
			System.out.println();
		}
		else {
			// title and/or options are not valid
			System.out.println("Menu not defined.");
		}
	}
	
	/**
	 * will initialise the options array by copying
	 * contents of data
	 * @param data - array of Strings - options for user to select from
	 */
	private void copyOptions(String data[]) {
		if ( data != null) {
			options = new String[data.length];
			for(int index=0;index<data.length;index++) {
				options[index] = data[index];
			}
		}
		else {
			options = null;
		}
	}

	public void setExtraDetails(String extraDetails) {
		this.extraDetails = extraDetails;
	}
	
}
