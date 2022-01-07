import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

public record Generator(Elector elector, Builder builder, Deque<Password> history) {

    public Password generate(final int length, final Set<Requirement> requirements, final Constraint constraint) {

        // TODO
        return null;

    }

    public Deque<Password> history() {

        // TODO
        return new LinkedList<>();

    }

}