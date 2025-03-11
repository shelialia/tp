package seedu.guestnote.model.request;

import static seedu.guestnote.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RequestTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Request(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Request(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null request name
        assertThrows(NullPointerException.class, () -> Request.isValidTagName(null));
    }

}
