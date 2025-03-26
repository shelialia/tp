package seedu.guestnote.model.guest;

import java.util.List;
import java.util.function.Predicate;

public class AnyFieldContainsKeywordsPredicate implements Predicate<Guest> {
    private final List<Predicate<Guest>> predicates;

    public AnyFieldContainsKeywordsPredicate(List<Predicate<Guest>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean test(Guest guest) {
        // Return true if any predicate matches
        for (Predicate<Guest> predicate : predicates) {
            if (predicate.test(guest)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {//Only prints out a summary of predicates instead of all predicates as it will be very long
        return getClass().getSimpleName() + "{predicateCount=" + predicates.size() + "}";
    }
}
