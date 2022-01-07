import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BuilderTest {

    private static final int LENGTH_TWELVE_CHARS = 12;
    private static final int LENGTH_FOURTEEN_CHARS = 14;
    private static final int LENGTH_FIFTEEN_CHARS = 15;

    @Test
    void should_throw_an_error_when_not_having_enough_elected_characters_to_build_a_password_with_the_required_length() {

        // [Arrange]
        final Builder builder = new Builder();
        final List<String> electedCharacters = prepare();

        // [Act / Assert]
        assertThatThrownBy(() -> builder.build(LENGTH_FIFTEEN_CHARS, electedCharacters))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("More characters are required to ensure unique characters in the password.");

    }

    @Test
    void should_not_use_all_elected_characters_when_the_required_length_is_lower() {

        // [Arrange]
        final Builder builder = new Builder();
        final List<String> electedCharacters = prepare();
        final int numberOfRemainingElectedCharacters = electedCharacters.size() - LENGTH_TWELVE_CHARS;

        // [Act]
        final Password actual = builder.build(LENGTH_TWELVE_CHARS, electedCharacters);

        // [Assert]
        assertThat(actual).isNotNull();
        assertThat(actual.created()).isBefore(now());
        assertThat(actual.text()).hasSize(LENGTH_TWELVE_CHARS);
        assertThat(electedCharacters).hasSize(numberOfRemainingElectedCharacters);

    }

    @Test
    void should_use_all_elected_characters_when_the_required_length_is_greater() {

        // [Arrange]
        final Builder builder = new Builder();
        final List<String> electedCharacters = prepare();

        // [Act]
        final Password actual = builder.build(LENGTH_FOURTEEN_CHARS, electedCharacters);

        // [Assert]
        assertThat(actual).isNotNull();
        assertThat(actual.created()).isBefore(now());
        assertThat(actual.text()).hasSize(LENGTH_FOURTEEN_CHARS);
        assertThat(electedCharacters).isEmpty();

    }

    private static List<String> prepare() {
        return new ArrayList<>(
                asList("a", "b", "c", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o")
        );
    }

}