package seedu.guestnote.model.guest;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FieldContainsKeywordsPredicate<T> implements Predicate<Guest> {
    private final List<String> keywords;
    private final Function<Guest, T> fieldExtractor;

    public FieldContainsKeywordsPredicate(Function<Guest, T> fieldExtractor, List<String> keywords) {
        this.keywords = keywords;
        this.fieldExtractor = fieldExtractor;
    }

    @Override
    public boolean test(Guest guest) {
        T fieldValue = fieldExtractor.apply(guest);
        if (fieldValue == null) {
            return false;
        }
        if (fieldValue.getClass().isArray()) {
            Object[] arr = (Object[]) fieldValue;
            return arrayContainsKeyword(arr, keywords);
        } else {
            String fieldString = fieldValue.toString().toLowerCase();
            return keywords.stream().anyMatch(keyword -> fieldString.contains(keyword.toLowerCase()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FieldContainsKeywordsPredicate)) {
            return false;
        }

        FieldContainsKeywordsPredicate<?> otherPredicate = (FieldContainsKeywordsPredicate<?>) other;
        return keywords.equals(otherPredicate.keywords);
    }
    /**
     * Helper method that checks if any keyword is contained in the concatenated string of array elements.
     * This method avoids nested loops by first concatenating all non-null elements into one string.
     */
    private boolean arrayContainsKeyword(Object[] arr, List<String> keywords) {
        StringBuilder sb = new StringBuilder();
        for (Object element : arr) {
            if (element != null) {
                sb.append(element.toString().toLowerCase()).append(" ");
            }
        }
        String combined = sb.toString();
        for (String keyword : keywords) {
            if (combined.contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return getClass().getCanonicalName() + "{keywords=" + keywords + "}";
    }
}
