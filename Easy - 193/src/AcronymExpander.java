import java.util.HashMap;

/*
 * 
 During online gaming (or any video game that requires teamwork) , there is often times that you need to speak to your teammates. Given the nature of the game, it may be inconvenient to say full sentences and it's for this reason that a lot of games have acronyms in place of sentences that are regularly said.
Example
gg : expands to 'Good Game'
brb : expands to 'be right back'
and so on...
This is even evident on IRC's and other chat systems.
However, all this abbreviated text can be confusing and intimidating for someone new to a game. They're not going to instantly know what 'gl hf all'(good luck have fun all) means. It is with this problem that you come in.
You are tasked with converting an abbreviated sentence into its full version.
*/

public class AcronymExpander {

	public static void main(String[] args) {
		
		String text = "brb guys, I gtg, gg, lolita";
		String parsedtext= parse(text);
		
		System.out.println(parsedtext);
	}
	
	private static String parse(String text) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("lol", "laugh out loud");
		map.put("brb", "be right back");
		map.put("gg", "good game");
		map.put("gtg", "gotta go");
		
		StringBuilder temp = new StringBuilder(text); //Converts String into StringBuilder
		String a[] = temp.toString().split("\\s+|(?=[,.])");  //Splits the string by blank spaces
		
		for (int i = 0; i < a.length; i++) { // Loops thru each word
			for (String key : map.keySet()) { // Check if each word contains the key
				if (a[i].contains(key)){ //If it contains an acronym it will replace it
					a[i]=map.get(key);
				}
			}
		}
		temp = new StringBuilder(); // Clears the String Builder
		
		for (int j = 0; j < a.length; j++) { //Converts array into String
			temp.append(a[j]);
			temp.append(" ");
		}
		return temp.toString();
	}
}
