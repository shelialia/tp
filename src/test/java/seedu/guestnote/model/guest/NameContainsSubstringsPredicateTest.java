package seedu.guestnote.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.guestnote.testutil.GuestBuilder;

public class NameContainsSubstringsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsSubstringsPredicate firstPredicate =
                new NameContainsSubstringsPredicate(firstPredicateKeywordList);
        NameContainsSubstringsPredicate secondPredicate =
                new NameContainsSubstringsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsSubstringsPredicate firstPredicateCopy =
                new NameContainsSubstringsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different guest -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsSubstringsPredicate predicate =
                new NameContainsSubstringsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsSubstringsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsSubstringsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsSubstringsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsSubstringsPredicate predicate = new NameContainsSubstringsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GuestBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsSubstringsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new GuestBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and guestnote, but does not match name
        predicate = new NameContainsSubstringsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new GuestBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameContainsSubstringsPredicate predicate = new NameContainsSubstringsPredicate(keywords);

        String expected = NameContainsSubstringsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
