package seedu.guestnote.model.guest;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.guestnote.commons.util.StringUtil;

/**
 * Predicate that tests whether a field of a Guest object contains any of the given keywords.
 * <p>
 * This class is used to test whether a Guest object's field (e.g., name, phone, email, etc.)
 * contains any of the given keywords.
 * </p>
 */
public class FieldContainsKeywordsPredicate<T> implements Predicate<Guest> {
    private final List<String> keywords;
    private final Function<Guest, T> fieldExtractor;

    /**
     * Constructor for FieldContainsKeywordsPredicate.
     * @param fieldExtractor Function that extracts the field to be tested from a Guest object.
     * @param keywords List of keywords to test against the field.
     */
    public FieldContainsKeywordsPredicate(Function<Guest, T> fieldExtractor, List<String> keywords) {
        this.keywords = keywords;
        this.fieldExtractor = fieldExtractor;
    }

    @Override
    public boolean test(Guest guest) {
        T fieldValue = fieldExtractor.apply(guest);
        if (fieldValue instanceof Optional) {
            Optional<?> optionalValue = (Optional<?>) fieldValue;
            if (!optionalValue.isPresent()) {
                return false;
            }
            fieldValue = (T) optionalValue.get();
        }
        if (fieldValue == null) {
            return false;
        }
        if (fieldValue.getClass().isArray()) {
            Object[] arr = (Object[]) fieldValue;
            return arrayContainsKeyword(arr, keywords);
        } else {
            String fieldString = fieldValue.toString().trim();
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(fieldString, keyword.trim()));
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
     * Helper method that checks if the concatenated string of array elements contains
     * the full phrase formed by joining all keywords.
     * This ensures that for array fields (like requests), the complete phrase is matched.
     */
    private boolean arrayContainsKeyword(Object[] arr, List<String> keywords) {
        StringBuilder sb = new StringBuilder();
        for (Object element : arr) {
            if (element != null) {
                sb.append(element.toString().toLowerCase()).append(" ");
            }
        }
        String combined = sb.toString();
        // Join all keywords with a space to form the full search phrase.
        String joinedKeywords = String.join(" ", keywords).toLowerCase();
        return combined.contains(joinedKeywords);
    }
    @Override
    public String toString() {
        return getClass().getCanonicalName() + "{keywords=" + keywords + "}";
    }
}
