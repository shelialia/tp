package seedu.guestnote.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.guestnote.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class StatusTest {

    @Test
    void testBookingStatus() {
        Status status = Status.BOOKING;
        assertEquals("BOOKING", status.name());
        assertEquals(0, status.ordinal());
    }

    @Test
    void testCheckedInStatus() {
        Status status = Status.CHECKED_IN;
        assertEquals("CHECKED_IN", status.name());
        assertEquals(1, status.ordinal());
    }

    @Test
    void testCheckedOutStatus() {
        Status status = Status.CHECKED_OUT;
        assertEquals("CHECKED_OUT", status.name());
        assertEquals(2, status.ordinal());
    }

    @Test
    void testValueOf() {
        assertEquals(Status.BOOKING, Status.valueOf("BOOKING"));
        assertEquals(Status.CHECKED_IN, Status.valueOf("CHECKED_IN"));
        assertEquals(Status.CHECKED_OUT, Status.valueOf("CHECKED_OUT"));
    }

    @Test
    void testValues() {
        Status[] statuses = Status.values();
        assertEquals(3, statuses.length);
        assertEquals(Status.BOOKING, statuses[0]);
        assertEquals(Status.CHECKED_IN, statuses[1]);
        assertEquals(Status.CHECKED_OUT, statuses[2]);
    }

    @Test
    void testValueOfNull() {
        assertThrows(NullPointerException.class, () -> {
            Status.valueOf(null);
        });
    }

    @Test
    void testValueOfInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            Status.valueOf("INVALID_STATUS");
        });
    }
}
