package seedu.guestnote.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StayDurationTest {

    @Test
    public void constructor_validDate_success() {
        StayDuration checkIn = new CheckIn("12-04-2024");
        StayDuration checkOut = new CheckOut("15-04-2024");

        assertEquals("2024-04-12", checkIn.toString());
        assertEquals("2024-04-15", checkOut.toString());
    }

    @Test
    public void constructor_invalidDate_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new CheckIn("32-04-2024")); // Invalid day
        assertThrows(IllegalArgumentException.class, () -> new CheckOut("12-13-2024")); // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new CheckIn("abcd-ef-ghij")); // Invalid format
        assertThrows(IllegalArgumentException.class, () -> new CheckOut("2024-04-12")); // Wrong format (YYYY-MM-DD)
        assertThrows(NullPointerException.class, () -> new CheckIn(null)); // Null input
    }

    @Test
    public void isValidDate_validDates_returnTrue() {
        assertTrue(StayDuration.isValidDate("01-01-2024"));
        assertTrue(StayDuration.isValidDate("29-02-2024")); // Leap year
        assertTrue(StayDuration.isValidDate("31-12-2023"));
    }

    @Test
    public void isValidDate_invalidDates_returnFalse() {
        assertFalse(StayDuration.isValidDate("32-01-2024")); // Invalid day
        assertFalse(StayDuration.isValidDate("12-13-2024")); // Invalid month
        assertFalse(StayDuration.isValidDate("2024-01-01")); // Wrong format
        assertFalse(StayDuration.isValidDate("abc-def-ghij")); // Completely wrong format
        assertFalse(StayDuration.isValidDate(null)); // Null input
    }

    @Test
    public void toString_correctFormat() {
        StayDuration checkIn = new CheckIn("12-04-2024");
        assertEquals("2024-04-12", checkIn.toString());

        StayDuration checkOut = new CheckOut("15-04-2024");
        assertEquals("2024-04-15", checkOut.toString());
    }

    @Test
    public void equalsAndHashCode_consistency() {
        StayDuration checkIn1 = new CheckIn("12-04-2024");
        StayDuration checkIn2 = new CheckIn("12-04-2024");
        StayDuration checkOut = new CheckOut("12-04-2024");
        StayDuration differentCheckIn = new CheckIn("13-04-2024");

        // Reflexivity
        assertTrue(checkIn1.equals(checkIn1));

        // Symmetry
        assertTrue(checkIn1.equals(checkIn2));
        assertTrue(checkIn2.equals(checkIn1));

        // Transitivity
        StayDuration checkIn3 = new CheckIn("12-04-2024");
        assertTrue(checkIn1.equals(checkIn2));
        assertTrue(checkIn2.equals(checkIn3));
        assertTrue(checkIn1.equals(checkIn3));

        // Consistency
        assertTrue(checkIn1.equals(checkIn2));
        assertTrue(checkIn1.equals(checkIn2));

        // Null check
        assertFalse(checkIn1.equals(null));

        // Different dates
        assertFalse(checkIn1.equals(differentCheckIn));

        // Different types
        assertFalse(checkIn1.equals(checkOut));

        assertEquals(checkIn1.hashCode(), checkIn2.hashCode());
        assertNotEquals(checkIn1.hashCode(), differentCheckIn.hashCode());
    }
}
