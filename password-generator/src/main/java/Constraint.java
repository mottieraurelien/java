import java.util.List;

public enum Constraint {

    EASY_TO_SAY {
        @Override
        public void apply(final List<String> characters) {
            // TODO
        }
    },

    EASY_TO_READ {
        @Override
        public void apply(final List<String> characters) {
            // TODO
        }
    };

    public abstract void apply(final List<String> characters);

}