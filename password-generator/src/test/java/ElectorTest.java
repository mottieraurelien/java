import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.Set.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ElectorTest {

    @Test
    void should_throw_an_exception_when_the_requirements_are_cancelled_by_the_easy_to_say_constraint() {

        // [Arrange]
        final Elector elector = new Elector();

        // [Act]
        assertThatThrownBy(() -> elector.elect(of(Requirement.FIGURES, Requirement.SPECIAL_CHARACTERS), Constraint.EASY_TO_SAY))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Some characters are required to generate a password.");

    }

    @Test
    void should_not_remove_any_elected_character_when_no_constraint_has_been_defined() {

        // [Arrange]
        final Elector elector = new Elector();
        final Set<Requirement> requirements = Requirement.ALL;

        // [Act]
        final List<String> actual = elector.elect(requirements, null);

        // [Assert] that the returned list contains items (RequirementTest and ConstraintTest are more accurate).
        assertThat(actual).isNotEmpty();

    }

    @Test
    void should_remove_unsayable_characters_from_the_elected_characters_when_applying_the_easy_to_say_constraint() {

        // [Arrange]
        final Elector elector = new Elector();
        final Set<Requirement> requirements = of(Requirement.FIGURES);

        // [Act]
        final List<String> actual = elector.elect(requirements, Constraint.EASY_TO_READ);

        // [Assert] that the returned list contains items (RequirementTest and ConstraintTest are more accurate).
        assertThat(actual).isNotEmpty();

    }

    @Test
    void should_remove_unreadable_characters_from_the_elected_characters_when_applying_the_easy_to_read_constraint() {

        // [Arrange]
        final Elector elector = new Elector();
        final Set<Requirement> requirements = of(Requirement.LOWERCASE_LETTERS, Requirement.UPPERCASE_LETTERS, Requirement.FIGURES);

        // [Act]
        final List<String> actual = elector.elect(requirements, Constraint.EASY_TO_READ);

        // [Assert] that the returned list contains items (RequirementTest and ConstraintTest are more accurate).
        assertThat(actual).isNotEmpty();

    }

}