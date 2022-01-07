import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Elector {

    public List<String> elect(final Set<Requirement> requirements, final Constraint constraint) {

        final List<String> electedCharacters = new ArrayList<>();

        for (final Requirement requirement : requirements) requirement.fulfil(electedCharacters);

        if (constraint != null) constraint.apply(electedCharacters);

        if (electedCharacters.isEmpty())
            throw new IllegalStateException("Some characters are required to generate a password.");

        return electedCharacters;

    }

}