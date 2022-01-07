import java.util.List;
import java.util.Set;

import static java.util.Set.of;

public enum Requirement {

    FIGURES {
        @Override
        public void fulfil(final List<String> characters) {
            characters.addAll(Characters.FIGURES);
        }
    },

    LOWERCASE_LETTERS {
        @Override
        public void fulfil(final List<String> characters) {
            characters.addAll(Characters.LOWERCASE_LETTERS);
        }
    },

    UPPERCASE_LETTERS {
        @Override
        public void fulfil(final List<String> characters) {
            characters.addAll(Characters.UPPERCASE_LETTERS);
        }
    },

    SPECIAL_CHARACTERS {
        @Override
        public void fulfil(final List<String> characters) {
            characters.addAll(Characters.SPECIAL_CHARACTERS);
        }
    };

    public static final Set<Requirement> ALL = of(FIGURES, LOWERCASE_LETTERS, UPPERCASE_LETTERS, SPECIAL_CHARACTERS);

    public abstract void fulfil(final List<String> characters);

}