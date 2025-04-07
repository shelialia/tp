package seedu.guestnote.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.logic.commands.CommandTestUtil.VALID_REQUEST_EXTRAPILLOW;
import static seedu.guestnote.testutil.Assert.assertThrows;
import static seedu.guestnote.testutil.TypicalGuests.ALICE;
import static seedu.guestnote.testutil.TypicalGuests.getTypicalGuestNote;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.exceptions.DuplicateGuestException;
import seedu.guestnote.testutil.GuestBuilder;

public class GuestNoteTest {

    private final GuestNote guestNote = new GuestNote();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), guestNote.getGuestList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> guestNote.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyGuestNote_replacesData() {
        GuestNote newData = getTypicalGuestNote();
        guestNote.resetData(newData);
        assertEquals(newData, guestNote);
    }

    @Test
    public void resetData_withDuplicateGuests_throwsDuplicateGuestException() {
        // Two guests with the same identity fields
        Guest editedAlice = new GuestBuilder(ALICE).withRequests(VALID_REQUEST_EXTRAPILLOW)
                .build();
        List<Guest> newGuests = Arrays.asList(ALICE, editedAlice);
        GuestNoteStub newData = new GuestNoteStub(newGuests);

        assertThrows(DuplicateGuestException.class, () -> guestNote.resetData(newData));
    }

    @Test
    public void hasGuest_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> guestNote.hasGuest(null));
    }

    @Test
    public void hasGuest_guestNotInGuestNote_returnsFalse() {
        assertFalse(guestNote.hasGuest(ALICE));
    }

    @Test
    public void hasGuest_guestInGuestNote_returnsTrue() {
        guestNote.addGuest(ALICE);
        assertTrue(guestNote.hasGuest(ALICE));
    }

    @Test
    public void hasGuest_guestWithSameIdentityFieldsInAddressBook_returnsTrue() {
        guestNote.addGuest(ALICE);
        Guest editedAlice = new GuestBuilder(ALICE).withRequests(VALID_REQUEST_EXTRAPILLOW)
                .build();
        assertTrue(guestNote.hasGuest(editedAlice));
    }

    @Test
    public void getGuestList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> guestNote.getGuestList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected =
                GuestNote.class.getCanonicalName() + "{guests=" + guestNote.getGuestList() + "}";
        assertEquals(expected, guestNote.toString());
    }

    /**
     * A stub ReadOnlyGuestNote whose guests list can violate interface constraints.
     */
    private static class GuestNoteStub implements ReadOnlyGuestNote {
        private final ObservableList<Guest> guests = FXCollections.observableArrayList();

        GuestNoteStub(Collection<Guest> guests) {
            this.guests.setAll(guests);
        }

        @Override
        public ObservableList<Guest> getGuestList() {
            return guests;
        }
    }

}
