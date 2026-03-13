package cz.wz.marysidy.island.util;

import java.util.Map;

public class EmojiRegistry {
    private static final Map<String, String> EMOJIS = Map.ofEntries(
            Map.entry("Anaconda", "🐍"),
            Map.entry("Bear", "🐻"),
            Map.entry("Boar", "🐗"),
            Map.entry("Buffalo", "🐃"),
            Map.entry("Caterpillar", "🐛"),
            Map.entry("Deer", "🦌"),
            Map.entry("Duck", "🦆"),
            Map.entry("Eagle", "🦅"),
            Map.entry("Fox", "🦊"),
            Map.entry("Goat", "🐐"),
            Map.entry("Horse", "🐎"),
            Map.entry("Mouse", "🐭"),
            Map.entry("Rabbit", "🐰"),
            Map.entry("Sheep", "🐑"),
            Map.entry("Wolf", "🐺"),
            Map.entry("Grass", "🌿")
    );
    private EmojiRegistry() {}

    public static String get(String name) {
        return EMOJIS.getOrDefault(name, "");
    }
}
