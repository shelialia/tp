package seedu.guestnote.model.guest;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * Tests whether the field of a Guest object contains any of the given keywords.
     * @param guest The Guest object to test.
     * @return true if the field contains any of the keywords, false otherwise.
     */
    @Override
    public boolean test(Guest guest) {
        T fieldValue = fieldExtractor.apply(guest);
        if (fieldValue instanceof Optional<?> optionalValue) { //pattern matching
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
            String temp = fieldValue.toString();
            String fieldString = temp.trim();
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
     * keywords on a word-by-word basis.
     * This ensures that for array fields (like requests), the complete phrase is matched.
     */
    private boolean arrayContainsKeyword(Object[] arr, List<String> keywords) {
        // Join keywords into a normalized search phrase.
        String normalizedSearch = String.join(" ", keywords).toLowerCase().trim()
                .replaceAll("\\s+", " ");
        // Prepare a regex pattern with word boundaries.
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(normalizedSearch) + "\\b");

        // Check each element of the array.
        for (Object element : arr) {
            if (element != null) {
                String normalizedElement = element.toString().toLowerCase().trim()
                        .replaceAll("\\s+", " ");
                Matcher matcher = pattern.matcher(normalizedElement);
                if (matcher.find()) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return getClass().getCanonicalName() + "{keywords=" + keywords + "}";
    }
}
