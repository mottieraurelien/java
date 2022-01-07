import java.util.HashSet;
import java.util.Set;

import static java.util.Set.of;

public final class Characters {

    public static final Set<String> FIGURES = of(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    );

    public static final Set<String> LOWERCASE_LETTERS = of(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    );

    public static final Set<String> UPPERCASE_LETTERS = of(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    );

    public static final Set<String> SPECIAL_CHARACTERS = of(
            "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-",
            ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^",
            "_", "`", "{", "|", "}", "~"
    );

    public static final Set<String> NOT_EASY_TO_SAY;
    public static final Set<String> NOT_EASY_TO_READ;

    static {

        NOT_EASY_TO_SAY = new HashSet<>();
        NOT_EASY_TO_SAY.addAll(FIGURES);
        NOT_EASY_TO_SAY.addAll(SPECIAL_CHARACTERS);

        NOT_EASY_TO_READ = new HashSet<>();
        NOT_EASY_TO_READ.add("I");
        NOT_EASY_TO_READ.add("l");
        NOT_EASY_TO_READ.add("O");
        NOT_EASY_TO_READ.add("0");

    }

}