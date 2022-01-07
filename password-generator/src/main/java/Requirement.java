import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Requirement {

    FIGURES {
        @Override
        public void fulfil(final List<String> characters) {
            // TODO
        }
    },

    LOWERCASE_LETTERS {
        @Override
        public void fulfil(final List<String> characters) {
            // TODO
        }
    },

    UPPERCASE_LETTERS {
        @Override
        public void fulfil(final List<String> characters) {
            // TODO
        }
    },

    SPECIAL_CHARACTERS {
        @Override
        public void fulfil(final List<String> characters) {
            // TODO
        }
    };

    // TODO
    public static final Set<Requirement> ALL = new HashSet<>();

    public abstract void fulfil(final List<String> characters);

}