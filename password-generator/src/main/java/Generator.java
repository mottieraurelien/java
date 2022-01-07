import java.util.*;

public record Generator(Elector elector, Builder builder, Deque<Password> history) {

    private static final int LIMIT = 3;

    public Password generate(final int length, final Set<Requirement> requirements, final Constraint constraint) {

        check(length, requirements);

        final List<String> electedCharacters = this.elector.elect(requirements, constraint);

        final Password password = this.builder.build(length, electedCharacters);

        this.history.addFirst(password);

        return password;
    }

    private static void check(final int length, final Set<Requirement> requirements) {

        if (length < 10)
            throw new IllegalArgumentException("The password length must equal or be greater then 10.");

        if (length > 99)
            throw new IllegalArgumentException("The password length must equal or be lower than 99.");

        if (requirements == null || requirements.isEmpty())
            throw new IllegalArgumentException("The requirements must be not null and contain at least one requirement.");

    }

    public Deque<Password> history() {
        final Deque<Password> lastGeneratedPassword = new LinkedList<>();
        final Iterator<Password> iterator = this.history.iterator();
        while (iterator.hasNext() && lastGeneratedPassword.size() < LIMIT) {
            final Password currentPassword = iterator.next();
            lastGeneratedPassword.addLast(currentPassword);
        }
        return lastGeneratedPassword;
    }

}