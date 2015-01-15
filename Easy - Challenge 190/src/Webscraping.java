import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


/* http://www.reddit.com/r/dailyprogrammer/comments/2nauiv/20141124_challenge_190_easy_webscraping_sentiments/
 * 
 */

public class Webscraping {

    static ArrayList<String> happy = new ArrayList<String>();
    static ArrayList<String> sad = new ArrayList<String>();
    static int counter;
    static int totalhappy;
    static int totalsad;
	
	public static void main(String[] args) throws IOException {
		
		String videourl= "https://www.youtube.com/watch?v=PhG6kuTuW7Y";
		URL url= new URL("https://plus.googleapis.com/u/0/_/widget/render/comments?first_party_property=YOUTUBE&href=" + videourl);
		
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        
        happy.add("love");
        happy.add("loved");
        happy.add("like");
        happy.add("liked");
        happy.add("awesome");
        happy.add("amazing");
        happy.add("good");
        happy.add("great");
        happy.add("excellent");

        sad.add("hate");
        sad.add("hated");
        sad.add("dislike");
        sad.add("disliked");
        sad.add("awful");
        sad.add("terrible");
        sad.add("bad");
        sad.add("painful");
        sad.add("worst");
        
        
        while ((inputLine = in.readLine()) != null){ //Finds comments
        	if (inputLine.contains("<div class=\"Ct\">")){
        		
        		int a = inputLine.indexOf("<div class=\"Ct\">") + 16;
        		inputLine = new String (inputLine.substring(a));
        		
        		int b = inputLine.indexOf("﻿</div>");
        		inputLine = new String (inputLine.substring(0,b));
        		
        		search(inputLine);
        		counter++;
        	}
        }
       
        in.close();
        
        String feelings = "happy";
        
        if(totalhappy<totalsad)
        	feelings = new String("sad");
        
        System.out.println(
        		"From a sample size of " + 
        		counter + " Persons. This sentence is mostly "  + feelings + 		
        		". It contained " + totalhappy + " amount of Happy keywords and " +
        		totalsad + " amount of sad keywords. The general feelings towards this video were " + feelings);
    }
	
	private static void search(String inputLine) {

		String a[] = inputLine.split("\\s");
	
		for (String wordtosearch : happy) {  //Outer loop to not waste resources counting each
			if(inputLine.contains(wordtosearch)){
				innersearch(a);
			}
		}
		
		for (String wordtosearch : sad) {
			if(inputLine.contains(wordtosearch)){
				innersearch(a);
			}
		}		
	}

	private static void innersearch(String[] a) {
		
		for (int i = 0; i < a.length; i++) { //outer loop, each word
			for (String wordtosearch : happy) {  //inner loop searching happy words
				if(a[i].contains(wordtosearch)){
					totalhappy++; // adds 1 to total happy
				}
			}
		}
		
		for (int i = 0; i < a.length; i++) { //outer loop, each word
			for (String wordtosearch : sad) {  //inner loop searching sad words
				if(a[i].contains(wordtosearch)){
					totalsad++; // adds 1 to total sad
				}
			}
		}
	}
}
