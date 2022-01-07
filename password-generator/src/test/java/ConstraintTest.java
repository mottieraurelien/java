import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class ConstraintTest {

    @ParameterizedTest
    @MethodSource("scenarios")
    void should_remove_the_right_characters_from_the_list_depending_on_the_constraint(
            final Constraint constraint, final Set<String> expectedElectedCharactersThatHaveBeenRemoved) {

        // [Arrange]
        final List<String> actualRemainingElectedCharacters = new ArrayList<>();
        for (final Requirement requirement : Requirement.ALL) requirement.fulfil(actualRemainingElectedCharacters);
        assertThat(actualRemainingElectedCharacters).isNotEmpty();
        final int expectedSize = actualRemainingElectedCharacters.size() - expectedElectedCharactersThatHaveBeenRemoved.size();

        // [Act]
        constraint.apply(actualRemainingElectedCharacters);

        // [Assert]
        assertThat(actualRemainingElectedCharacters).isNotEmpty();
        assertThat(actualRemainingElectedCharacters).hasSize(expectedSize);
        assertThat(actualRemainingElectedCharacters).doesNotContainAnyElementsOf(expectedElectedCharactersThatHaveBeenRemoved);

    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenarios() {
        return Stream.of(
                of(Constraint.EASY_TO_SAY, Characters.NOT_EASY_TO_SAY),
                of(Constraint.EASY_TO_READ, Characters.NOT_EASY_TO_READ)
        );
    }

}