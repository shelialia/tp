package seedu.guestnote.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CheckInTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CheckIn(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CheckIn("32-05-2024"));
        assertThrows(IllegalArgumentException.class, () -> new CheckIn("29-02-2023")); // Invalid leap year
        assertThrows(IllegalArgumentException.class, () -> new CheckIn("00-10-2024")); // Invalid day
        assertThrows(IllegalArgumentException.class, () -> new CheckIn("10-00-2024")); // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new CheckIn("15/08/2024")); // Wrong format
    }

    @Test
    public void isValidDate() {
        // Valid dates
        assertTrue(StayDuration.isValidDate("12-04-2024"));
        assertTrue(StayDuration.isValidDate("01-01-2023"));
        assertTrue(StayDuration.isValidDate("29-02-2024")); // Leap year

        // Invalid dates
        assertFalse(StayDuration.isValidDate("")); // Empty date
        assertFalse(StayDuration.isValidDate(" ")); // Empty date with whitespace
        assertFalse(StayDuration.isValidDate("32-05-2024")); // Invalid day
        assertFalse(StayDuration.isValidDate("15/08/2024")); // Wrong format
        assertFalse(StayDuration.isValidDate("2024-04-12")); // Wrong format
        assertFalse(StayDuration.isValidDate("29-02-2023")); // Invalid leap year
        assertFalse(StayDuration.isValidDate("00-10-2024")); // Invalid day
        assertFalse(StayDuration.isValidDate("10-00-2024")); // Invalid month
    }
}
