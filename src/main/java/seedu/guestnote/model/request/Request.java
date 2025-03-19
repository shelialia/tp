package seedu.guestnote.model.request;

import static java.util.Objects.requireNonNull;
import static seedu.guestnote.commons.util.AppUtil.checkArgument;

/**
 * Represents a Request in the guestnote book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Request {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String tagName;

    /**
     * Constructs a {@code Request}.
     *
     * @param tagName A valid request name.
     */
    public Request(String tagName) {
        requireNonNull(tagName);
        System.out.println(tagName + "here I am");
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid request name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Request)) {
            return false;
        }

        Request otherRequest = (Request) other;
        return tagName.equals(otherRequest.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
