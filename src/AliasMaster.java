import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;


public class AliasMaster {
private static HashMap<String, Object> aliases;
	
	
	public static void main(String[] args) {
		
		aliases = new HashMap<String, Object>();
		
		System.out.println("*** I Am Alias Master ***");
		
		Scanner scan = new Scanner(System.in);
   
		while (true) {
			System.out.print("Command: ");
			String s = scan.nextLine();
			if (!parseCommands(s))
				break;
		}
		
		scan.close();
	}
		public static boolean parseCommands(String s) {
			
			try {
				String[] cmd = s.split(" ", 3);
				
				// Set command sets alias and gives it a value. Allowed value datatypes are Int, Float, Date and String.
				if ( cmd[0].compareToIgnoreCase("set") == 0 ) {
					if (cmd.length == 3)
						saveAlias(cmd[1], cmd[2]);
					else 
						System.out.println("Invalid parameters!");
				}
				
				// Get command gets specific alias and its corresponding value if prompted 
				// otherwise gives all aliases
				else if ( cmd[0].compareToIgnoreCase("get") == 0 ) {
					if (cmd.length == 1)
						getAlias(null);
					else if (cmd.length == 2)
						getAlias(cmd[1]);
					else
						System.out.println("Invalid parameters!");
				}
				
				// Find command finds aliases from search values
				else if ( cmd[0].compareToIgnoreCase("find") == 0 ) {
					String[] searchValue = s.split(" ", 2);
					
					if (searchValue.length == 1)
						System.out.println("Find what?");
					else if (searchValue.length == 2)
						findAlias(searchValue[1]);				
				}
				
				// TODO
				// Update command updates alias value 
				else if ( cmd[0].compareToIgnoreCase("update") == 0 ) {
					System.out.println("update command");		
				}
				
				// TODO
				// Set command sets or queries locale
				else if ( cmd[0].compareToIgnoreCase("locale") == 0) {
						if (cmd.length == 1)
							System.out.println(Locale.getDefault());
						else if (cmd.length == 2) 
							setLocale(cmd[1]);					
						else
							System.out.println("Invalid parameters!");
				}
				
				// TODO
				// Save command saves aliases to file with specified name
				else if ( cmd[0].compareToIgnoreCase("save") == 0 ) {
					System.out.println("save command");	
				}
				
				// TODO
				// Load command loads aliases from specified file
				else if ( cmd[0].compareToIgnoreCase("load") == 0 ) {
					System.out.println("load command");
				}
				
				// Quit program
				else if ( cmd[0].compareToIgnoreCase("quit") == 0 ) {
					System.out.println("*** Goodbye! ***");	
					return false;
				}
				
				else {
					System.out.println("Unrecognized command");
				}
			}
			catch (Exception e){}
			return true;
		}
		
		private static void findAlias(Object alias) {
			
				Boolean result = false;
				for (HashMap.Entry<String, Object> entry : aliases.entrySet()) {
					
					if (Objects.equals(alias, entry.getValue())) {
						System.out.println(entry.getKey());
						result = true;
					}
				}
				if ( result == false)
					System.out.println("No such value!");
		}
		
		private static void getAlias(String alias) {
			
			//DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
			//NumberFormat formatter = NumberFormat.getInstance(Locale.getDefault());
			if (alias == null) {
				
				for (HashMap.Entry<String, Object> entry : aliases.entrySet()) {
					System.out.println(entry.getKey() + " " + entry.getValue());
				}
			}			
			else if (aliases.containsKey(alias))
				System.out.println(aliases.get(alias));
			else 
				System.out.println("Alias " + alias + " not found!");
		}	
		private static void saveAlias(String alias, String value) {
			
			boolean found = false;
			
			//Alias in use
			if (aliases.containsKey(alias))
				System.out.println("Alias already used!");
			
			//Alias not in use
			else{
				
				//Int check
				try{
					  int num = Integer.parseInt(value);
					  // is an integer!
					  System.out.println("Integer");
					  aliases.put(alias, num);
					  found = true;
					} catch (NumberFormatException e) {
					  System.out.println("Not Int");
						// not an integer!
					}
				
				//Double check
				if(!found){
					
					NumberFormat formatter = NumberFormat.getInstance(Locale.getDefault());
					
					Number number;
					try {
						number = formatter.parse(value);
						if(number.toString().equals(value)){
							System.out.println("Double");
							aliases.put(alias, number.doubleValue());
							found = true;
						}
					} catch (ParseException e) {
						// not a double!
						  System.out.println("Not Double");
					}
					
				}
				if(!found){
					
					//hej hej
					DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(dateStyle);
//					try {
//						
//						
//							
//						
//					} 
//					catch() {
//					
//					}
					
				}
					
			
			//Save value as String
				if(!found) {
					aliases.put(alias, value);
					System.out.println("String");
				}
				
				System.out.println("Alias stored!");
				}
			
		}
		private static void setLocale(String alias) {
			Locale list[] = DateFormat.getAvailableLocales();
			boolean found = false;
			
			for (Locale aLocale : list) {
	        	if (aLocale.toString().equals(alias))	{
	        		System.out.println("Locale is now set to:" + aLocale.toString());
					Locale.setDefault(aLocale);
	        		found = true;
	        		}
	        }
			if (!found)
				System.out.println("Invalid parameters!");
		}
}
