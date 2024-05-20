import java.util.Map;
import java.util.TreeMap;

public class DictionaryData {
	// TreeMap<String, String> dictionary = new TreeMap<>();
	private static DictionaryData instance;
	private TreeMap<String, String> dictionary;

	private DictionaryData() {
		 dictionary = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	}

	public static DictionaryData getInstance() {
		if (instance == null) {
			instance = new DictionaryData();
		}
		return instance;
	}
	
	public TreeMap<String, String> getDictionary() {
        return dictionary;
    }

	public void addWord(String word, String definition) {
		// trim leading and trailing spaces from text
		word = word.trim();
		definition = definition.trim();
		
		// if word is not empty - add to dictionary
		if (word != null && !(word.isEmpty())) {
			dictionary.put(word, definition);
			System.out.println("Word added: " + word);
		}
	}

	public String getDefinition(String word) {
		System.out.println( dictionary.get(word));
		return dictionary.get(word);
	}

	public void removeWord(String word) {
		if (dictionary.containsKey(word)) {
			dictionary.remove(word);
			System.out.println("Word removed: " + word);
		} else {
			System.out.println("Word not found: " + word);
		}
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
	
    public void clearAllWords() {
        dictionary.clear();
    }

}
