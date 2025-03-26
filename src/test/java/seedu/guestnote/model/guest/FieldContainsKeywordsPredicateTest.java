package seedu.guestnote.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.guestnote.model.request.Request;
import seedu.guestnote.model.request.UniqueRequestList;

public class FieldContainsKeywordsPredicateTest {

    /**
     * Helper method to create a Guest with dummy values for phone, email, room number, and status.
     * The 'requests' parameter is a vararg of Strings representing each request.
     */
    private Guest createGuest(String name, String... requests) {
        UniqueRequestList uniqueRequests = new UniqueRequestList();
        for (String req : requests) {
            // Assuming Request has a constructor that accepts a String
            uniqueRequests.add(new Request(req));
        }
        return new Guest(new Name(name), new Phone("12345678"), new Email("dummy@example.com"),
                new RoomNumber("10-01"), Status.CHECKED_IN, uniqueRequests);
    }

    @Test
    public void equals() {
        List<String> firstKeywordList = Collections.singletonList("Alice");
        List<String> secondKeywordList = Arrays.asList("Alice", "Bob");

        FieldContainsKeywordsPredicate<String> firstPredicate =
                new FieldContainsKeywordsPredicate<>(guest -> guest.getName().toString(), firstKeywordList);
        FieldContainsKeywordsPredicate<String> secondPredicate =
                new FieldContainsKeywordsPredicate<>(guest -> guest.getName().toString(), secondKeywordList);
        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FieldContainsKeywordsPredicate<String> firstPredicateCopy =
                new FieldContainsKeywordsPredicate<>(guest -> guest.getName().toString(), firstKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_fieldContainsKeywords_returnsTrue() {
        // One keyword for a simple field (name)
        FieldContainsKeywordsPredicate<String> predicate = new FieldContainsKeywordsPredicate<>(
                guest -> guest.getName().toString(), Collections.singletonList("Alice"));
        assertTrue(predicate.test(createGuest("Alice Bob")));

        // Multiple keywords for a simple field (name)
        predicate = new FieldContainsKeywordsPredicate<>(
                guest -> guest.getName().toString(), Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(createGuest("Alice Bob")));

        // Mixed-case keywords for a simple field
        predicate = new FieldContainsKeywordsPredicate<>(
                guest -> guest.getName().toString(), Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(createGuest("Alice Bob")));
    }

    @Test
    public void test_fieldDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword for a simple field (name)
        FieldContainsKeywordsPredicate<String> predicate =
                new FieldContainsKeywordsPredicate<>(
                        guest -> guest.getName().toString(), Collections.singletonList("Carol"));
        assertFalse(predicate.test(createGuest("Alice Bob")));

        // Empty keyword list should return false
        predicate = new FieldContainsKeywordsPredicate<>(guest -> guest.getName().toString(), Collections.emptyList());
        assertFalse(predicate.test(createGuest("Alice")));
    }

    @Test
    public void test_arrayFieldContainsKeywords_returnsTrue() {
        FieldContainsKeywordsPredicate<String[]> predicate =
                new FieldContainsKeywordsPredicate<>(Guest::getRequestsArray, Collections.singletonList("wifi"));
        // Create a guest with requests including \"Need wifi\"
        assertTrue(predicate.test(createGuest("Alice", "Need wifi", "Extra pillow")));
    }

    @Test
    public void test_arrayFieldDoesNotContainKeywords_returnsFalse() {
        FieldContainsKeywordsPredicate<String[]> predicate =
                new FieldContainsKeywordsPredicate<>(Guest::getRequestsArray, Collections.singletonList("breakfast"));
        // Create a guest whose requests do not contain \"breakfast\"
        assertFalse(predicate.test(createGuest("Alice", "Need wifi", "Extra pillow")));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        FieldContainsKeywordsPredicate<String> predicate =
                new FieldContainsKeywordsPredicate<>(guest -> guest.getName().toString(), keywords);
        String expected = FieldContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
