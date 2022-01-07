import java.util.List;

public enum Constraint {

    EASY_TO_SAY {
        @Override
        public void apply(final List<String> characters) {
            characters.removeAll(Characters.NOT_EASY_TO_SAY);
        }
    },

    EASY_TO_READ {
        @Override
        public void apply(final List<String> characters) {
            characters.removeAll(Characters.NOT_EASY_TO_READ);
        }
    };

    public abstract void apply(final List<String> characters);

}