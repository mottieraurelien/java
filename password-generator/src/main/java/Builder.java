import java.util.List;
import java.util.Random;

import static java.time.LocalDateTime.now;

public class Builder {

    private static final Random RANDOM = new Random();

    public Password build(final int length, final List<String> electedCharacters) {

        if (length > electedCharacters.size())
            throw new IllegalStateException("More characters are required to ensure unique characters in the password.");

        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            final int bound = electedCharacters.size();
            final int selectedIndex = RANDOM.nextInt(bound);
            final String selectedChar = electedCharacters.get(selectedIndex);
            builder.append(selectedChar);
            electedCharacters.remove(selectedChar);
        }

        return new Password(builder.toString(), now());

    }

}