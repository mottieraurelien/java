import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class RequirementTest {

    @ParameterizedTest
    @MethodSource("scenarios")
    void should_fill_the_elected_characters_list_with_the_right_characters_depending_on_the_requirement(
            final Requirement requirement, final Set<String> expectedElectedCharacters) {

        // [Arrange]
        final List<String> actualElectedCharacters = new ArrayList<>();

        // [Act]
        requirement.fulfil(actualElectedCharacters);

        // [Assert]
        final int expectedSize = expectedElectedCharacters.size();
        assertThat(actualElectedCharacters).hasSize(expectedSize);
        assertThat(actualElectedCharacters).containsAll(expectedElectedCharacters);

    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenarios() {
        return Stream.of(
                of(Requirement.FIGURES, Characters.FIGURES),
                of(Requirement.LOWERCASE_LETTERS, Characters.LOWERCASE_LETTERS),
                of(Requirement.UPPERCASE_LETTERS, Characters.UPPERCASE_LETTERS),
                of(Requirement.SPECIAL_CHARACTERS, Characters.SPECIAL_CHARACTERS)
        );
    }

}