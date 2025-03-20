package seedu.guestnote.testutil;

import seedu.guestnote.model.request.Request;

/**
 * A utility class containing a list of {@code Request} objects to be used in tests.
 */
public class TypicalRequests {

    public static final Request EXTRA_TOWELS = new Request("Extra Towels");
    public static final Request LATE_CHECKOUT = new Request("Late checkout");

    private TypicalRequests() {} // prevents instantiation
}
