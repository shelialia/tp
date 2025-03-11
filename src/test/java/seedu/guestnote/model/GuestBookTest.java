package seedu.guestnote.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.guestnote.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.guestnote.testutil.Assert.assertThrows;
import static seedu.guestnote.testutil.TypicalPersons.ALICE;
import static seedu.guestnote.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.exceptions.DuplicatePersonException;
import seedu.guestnote.testutil.PersonBuilder;

public class GuestBookTest {

    private final GuestBook guestBook = new GuestBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), guestBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> guestBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        GuestBook newData = getTypicalAddressBook();
        guestBook.resetData(newData);
        assertEquals(newData, guestBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two guests with the same identity fields
        Guest editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Guest> newGuests = Arrays.asList(ALICE, editedAlice);
        GuestBookStub newData = new GuestBookStub(newGuests);

        assertThrows(DuplicatePersonException.class, () -> guestBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> guestBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(guestBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        guestBook.addPerson(ALICE);
        assertTrue(guestBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        guestBook.addPerson(ALICE);
        Guest editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(guestBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> guestBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = GuestBook.class.getCanonicalName() + "{guests=" + guestBook.getPersonList() + "}";
        assertEquals(expected, guestBook.toString());
    }

    /**
     * A stub ReadOnlyGuestBook whose guests list can violate interface constraints.
     */
    private static class GuestBookStub implements ReadOnlyGuestBook {
        private final ObservableList<Guest> guests = FXCollections.observableArrayList();

        GuestBookStub(Collection<Guest> guests) {
            this.guests.setAll(guests);
        }

        @Override
        public ObservableList<Guest> getPersonList() {
            return guests;
        }
    }

}
