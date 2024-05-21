import java.util.Map;
import java.util.TreeMap;
/**
 * DictionaryData class for managing the dictionary logic and storing the dictionary data.
 */
public class DictionaryData {
	//  singleton instance of DictionaryData for use in controller classes
	private static DictionaryData instance;
	
	 // TreeMap for storing and sorting the dictionary content
	private TreeMap<String, String> dictionary;

	/* Default constructor for DictionaryData object */
	private DictionaryData() {
		 // create a case-insensitive dictionary
		 dictionary = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	}

	/* Gets the singleton instance of DictionaryData for consistent usage across classes */
	public static DictionaryData getInstance() {
		//  if no instance exists, create a new one
		if (instance == null) 
			instance = new DictionaryData();
		
		// return the instance
		return instance;
	}
	
	/* Gets the dictionary */
	public TreeMap<String, String> getDictionary() {
        return dictionary;
    }

	/* Adds new word to the dictionary */
	public void addWord(String word, String definition) {
		// trim leading and trailing spaces from text
		word = word.trim();
		definition = definition.trim();
		
		if (word != null && !(word.isEmpty()) && 
				definition != null && !(definition.isEmpty())) {
			
			// if word and definition are not empty - add to dictionary
			dictionary.put(word, definition);
			System.out.println("Word added: " + word);
		}
	}

	/* Gets the definition of the specified word */
	public String getDefinition(String word) {
		return dictionary.get(word);
	} 

	/* remove the specified word from the dictionary */
	public void removeWord(String word) {
		// if the word exists in the dictionary - remove it
		if (dictionary.containsKey(word)) {
			dictionary.remove(word);
			System.out.println("Word removed: " + word);
		} else {
			System.out.println("Word not found: " + word);
		}
	}

	public void autoFill() {
		
		addWord("Apple" , "A round fruit with shiny red or green skin "
				+ "that is fairly hard and white inside.");
		addWord("Apple-cheeked" , "Having round pink cheeks and looking healthy.");
		addWord("Applejack" , "a strong alcoholic drink made from cider "
				+ "(= an alcoholic drink made from the juice of apples).");
		addWord("Clinomania" , "An excessive desire to stay in bed.");
		addWord("Apricity" , "The warmth of the sun in winter.");
		addWord("Maple" , "The most delightful and charming cat any living being has encountered.");
		addWord(" Cryptomnesia" , "When you forget that you’ve forgotten something, "
				+ "and perceive it as a new, original thought. ");
		addWord("Petrichor " , "A distinctive smell produced by rainfall on dry ground. ");
		addWord("   Aurora" , "The dawn in the early morning.");
		addWord("Zenith" , "	The highest, most successful point of a situation.");
		addWord("Bumbershoot" , "Just a cool word for 'umbrella'.");
		addWord("Abibliophobia" , "A phobia of running out of things to read.");
		addWord("Ailurophile" , "A person who loves cats.");
		addWord("Tergiversation" , "The art of twisting around someone’s statements.");
		addWord("Sonder" , "The realization that each passerby has a life full of experiences, "
				+ "emotions, and problems, just like you.");
		addWord("Nadir" , "The lowest point in a situation.");
		addWord("Mondegreen" , "Incorrectly hearing or interpreting a song’s lyrics.");
		addWord("Pluviophile" , "Someone who loves rain.");
		addWord("Petrichor" , "The smell of earth after the rain.");
		addWord("Defenestration" , "Throwing someone out of the window.");
		addWord("Kismet" , "    Preordained by a force of fate or destiny.");
		addWord("Ken" , "Range of what one can know or understand.");
		addWord("Scrumdiddlyumptious " , "extremely tasty; delicious.");
		addWord("Kaput" , "ruined; done for; demolished.");
		addWord("Supercalifragilisticexpialidocious" , "extraordinarily good; wonderful.");
		addWord("long-word" , "Just to test the text area text wrap\nyou can ignore it \n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ " \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n bla bla bla ");
		
		addWord("Moona" , "the epitome of cuteness and charm, a creature so delightful that "
				+ "it instantly warms the hearts of anyone who encounters it. "
				+ "Moona is a small, fluffy being, with a soft, velvety coat that glistens like moonlight. "
				+ "Its fur is a gentle shade of silver, with delicate patches of warm, amber hues, "
				+ "giving it a uniquely charming and picturesque appearance.");
	}
	
	public void displayAllWords() {
		if (dictionary.isEmpty()) {
			System.out.println("Dictionary is empty.");
		} else {
			System.out.println("Dictionary contents:");
			for (Map.Entry<String, String> entry : dictionary.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
		}

	}
	
	/* Clears all dictionary content */ 
    public void clearAllWords() {
        dictionary.clear();
    }
} // end of class DictionaryData
