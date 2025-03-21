package seedu.guestnote.model.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RequestTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Request(null));
    }

    @Test
    public void constructor_invalidRequestName_throwsIllegalArgumentException() {
        String invalidRequestName = "";
        assertThrows(IllegalArgumentException.class, () -> new Request(invalidRequestName));
    }

    @Test
    public void isValidRequestName() {
        // null request name
        assertThrows(NullPointerException.class, () -> Request.isValidRequestName(null));
    }

    @Test
    public void isValidRequestName_additionalTests() {
        // invalid request names
        assertFalse(Request.isValidRequestName("")); // empty string
        assertFalse(Request.isValidRequestName(" ")); // spaces only
        assertFalse(Request.isValidRequestName("!@#")); // non-alphanumeric characters

        // valid request names
        assertTrue(Request.isValidRequestName("Extra Towels"));
        assertTrue(Request.isValidRequestName("Late checkout"));
        assertTrue(Request.isValidRequestName("Room Cleaning"));
    }

    @Test
    public void isSameRequest() {
        Request request = new Request("Extra Towels");

        // same object -> returns true
        assertTrue(request.isSameRequest(request));

        // null -> returns false
        assertFalse(request.isSameRequest(null));

        // same request name -> returns true
        Request sameRequest = new Request("Extra Towels");
        assertTrue(request.isSameRequest(sameRequest));

        // different request name -> returns false
        Request differentRequest = new Request("Late checkout");
        assertFalse(request.isSameRequest(differentRequest));
    }

    @Test
    public void equalsMethod() {
        Request request = new Request("Room Cleaning");

        // same object -> returns true
        assertTrue(request.equals(request));

        // null -> returns false
        assertFalse(request.equals(null));

        // different type -> returns false
        assertFalse(request.equals("Room Cleaning"));

        // same request name -> returns true
        Request sameRequest = new Request("Room Cleaning");
        assertTrue(request.equals(sameRequest));

        // different request name -> returns false
        Request differentRequest = new Request("Extra Towels");
        assertFalse(request.equals(differentRequest));
    }

    @Test
    public void toStringMethod() {
        Request request = new Request("Late checkout");
        assertEquals("[Late checkout]", request.toString());
    }

    @Test
    public void hashCodeMethod() {
        Request request1 = new Request("Room Cleaning");
        Request request2 = new Request("Room Cleaning");

        assertEquals(request1.hashCode(), request2.hashCode());
    }
}
