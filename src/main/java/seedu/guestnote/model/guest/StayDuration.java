package seedu.guestnote.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guestnote.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a StayDuration (CheckIn or CheckOut) in the guestnote book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class StayDuration {

    public static final String MESSAGE_CONSTRAINTS =
            "Stay duration should be in the format DD-MM-YYYY (e.g., 12-04-2024), and it should not be blank.";

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    protected final LocalDate date;

    /**
     * Constructs a {@code StayDuration} object.
     *
     * @param dateString A valid date string in DD-MM-YYYY format.
     */
    public StayDuration(String dateString) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(dateString, INPUT_FORMATTER);
    }

    /**
     * Returns true if a given string follows the DD-MM-YYYY date format.
     */
    public static boolean isValidDate(String test) {
        if (test == null || !test.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return false;
        }
        try {
            LocalDate date = LocalDate.parse(test, INPUT_FORMATTER);
            return test.equals(date.format(INPUT_FORMATTER));
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return date.format(OUTPUT_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StayDuration)) {
            return false;
        }

        StayDuration otherStayDuration = (StayDuration) other;
        return date.equals(otherStayDuration.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
