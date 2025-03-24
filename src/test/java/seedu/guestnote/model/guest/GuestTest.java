package seedu.guestnote.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.guestnote.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.guestnote.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.guestnote.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.guestnote.testutil.Assert.assertThrows;
import static seedu.guestnote.testutil.TypicalGuests.ALICE;
import static seedu.guestnote.testutil.TypicalGuests.BOB;

import org.junit.jupiter.api.Test;

import seedu.guestnote.testutil.GuestBuilder;

public class GuestTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Guest guest = new GuestBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> guest.getRequests().remove(0));
    }

    @Test
    public void isSameGuest() {
        // same object -> returns true
        assertTrue(ALICE.isSameGuest(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameGuest(null));

        // same phone number, different name and email -> should return true
        Guest editedAlice = new GuestBuilder(ALICE).withName(VALID_NAME_BOB).withEmail(VALID_EMAIL_BOB).build();

        assertTrue(ALICE.isSameGuest(editedAlice));

        // same email, different name and phone number -> should return true
        editedAlice = new GuestBuilder(ALICE).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();
        assertTrue(ALICE.isSameGuest(editedAlice));

        // different phone and email -> should return false
        editedAlice = new GuestBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameGuest(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Guest editedBob = new GuestBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameGuest(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Guest aliceCopy = new GuestBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different guest -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Guest editedAlice = new GuestBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new GuestBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new GuestBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new GuestBuilder(ALICE).withRequests(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Guest.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", roomNumber=" + ALICE.getRoomNumber()
                + ", requests=" + ALICE.getRequests()
                + "}";
        assertEquals(expected, ALICE.toString());
    }
}
