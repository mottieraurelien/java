import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeneratorTest {

    private static final int VALID_LENGTH = 12;

    private static final Password FIRST_PASSWORD = new Password("first_password", now());
    private static final Password SECOND_PASSWORD = new Password("second_password", now());
    private static final Password THIRD_PASSWORD = new Password("third_password", now());
    private static final Password FOURTH_PASSWORD = new Password("fourth_password", now());

    @Mock
    private Elector electorMock;

    @Mock
    private Builder builderMock;

    @Mock
    private Deque<Password> historyMock;

    @Mock
    private List<String> electedCharactersMock;

    @Test
    void should_throw_an_exception_when_the_provided_length_is_too_low() {

        // [Arrange]
        final Generator generator = new Generator(electorMock, builderMock, historyMock);

        // [Act / Assert]
        assertThatThrownBy(() -> generator.generate(7, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The password length must equal or be greater then 10.");

    }

    @Test
    void should_throw_an_exception_when_the_provided_length_is_too_high() {

        // [Arrange]
        final int invalidLength = 100;
        final Generator generator = new Generator(electorMock, builderMock, historyMock);

        // [Act / Assert]
        assertThatThrownBy(() -> generator.generate(invalidLength, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The password length must equal or be lower than 99.");

    }

    @Test
    void should_throw_an_exception_when_the_provided_set_of_requirements_is_null() {

        // [Arrange]
        final Generator generator = new Generator(electorMock, builderMock, historyMock);

        // [Act / Assert]
        assertThatThrownBy(() -> generator.generate(VALID_LENGTH, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The requirements must be not null and contain at least one requirement.");

    }

    @Test
    void should_throw_an_exception_when_the_provided_set_of_requirements_is_empty() {

        // [Arrange]
        final Generator generator = new Generator(electorMock, builderMock, historyMock);

        // [Act / Assert]
        assertThatThrownBy(() -> generator.generate(VALID_LENGTH, new HashSet<>(), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The requirements must be not null and contain at least one requirement.");

    }

    @Test
    void should_add_the_password_to_the_history_when_the_password_has_been_built() {

        // [Arrange]
        when(electorMock.elect(Requirement.ALL, null)).thenReturn(electedCharactersMock);
        when(builderMock.build(VALID_LENGTH, electedCharactersMock)).thenReturn(FIRST_PASSWORD);
        final Generator generator = new Generator(electorMock, builderMock, historyMock);

        // [Act]
        final Password actual = generator.generate(VALID_LENGTH, Requirement.ALL, null);

        // [Assert]
        assertThat(actual).isEqualTo(FIRST_PASSWORD);
        verify(historyMock).addFirst(FIRST_PASSWORD);

    }

    @Test
    void should_return_the_most_recent_passwords_when_consulting_the_password_history() {

        // [Arrange]
        final Deque<Password> passwords = new LinkedList<>();
        passwords.addFirst(FIRST_PASSWORD);
        passwords.addFirst(SECOND_PASSWORD);
        passwords.addFirst(THIRD_PASSWORD);
        passwords.addFirst(FOURTH_PASSWORD);
        when(historyMock.iterator()).thenReturn(passwords.iterator());
        final Generator generator = new Generator(null, null, historyMock);

        // [Act]
        final Deque<Password> actualHistory = generator.history();

        // [Assert]
        assertThat(actualHistory).hasSize(3);

        final Password actualMostRecentPassword = actualHistory.pop();
        assertThat(actualMostRecentPassword).isEqualTo(FOURTH_PASSWORD);

        final Password actualSecondMostRecentPassword = actualHistory.pop();
        assertThat(actualSecondMostRecentPassword).isEqualTo(THIRD_PASSWORD);

        final Password actualThirdMostRecentPassword = actualHistory.pop();
        assertThat(actualThirdMostRecentPassword).isEqualTo(SECOND_PASSWORD);

        assertThat(actualHistory).doesNotContain(FIRST_PASSWORD);

    }

}