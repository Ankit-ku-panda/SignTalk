package com.example.signtalk;

import java.util.HashMap;
import java.util.List;

public class EmojiInterpreter {

    private HashMap<String, String> emojiDictionary;

    public EmojiInterpreter() {

        // THIS LINE WAS MISSING
        emojiDictionary = new HashMap<>();

        // meanings
        emojiDictionary.put("👋", "Hello");
        emojiDictionary.put("🙏", "Please");
        emojiDictionary.put("👍", "Yes");
        emojiDictionary.put("👎", "No");
        emojiDictionary.put("❤️", "I love you");
        emojiDictionary.put("🍚", "I am hungry");
        emojiDictionary.put("💧", "I need water");
        emojiDictionary.put("🚻", "I need bathroom");
        emojiDictionary.put("🛏️", "I want to sleep");
        emojiDictionary.put("📞", "Call someone");
    }

    public String translate(List<String> emojis) {

        StringBuilder sentence = new StringBuilder();

        for(String e : emojis){
            if(emojiDictionary.containsKey(e)){
                sentence.append(emojiDictionary.get(e)).append(" ");
            }
        }

        return sentence.toString();
    }
}
