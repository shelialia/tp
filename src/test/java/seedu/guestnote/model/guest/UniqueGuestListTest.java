package seedu.guestnote.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.guestnote.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.guestnote.testutil.Assert.assertThrows;
import static seedu.guestnote.testutil.TypicalGuests.ALICE;
import static seedu.guestnote.testutil.TypicalGuests.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.guestnote.model.guest.exceptions.DuplicateGuestException;
import seedu.guestnote.model.guest.exceptions.GuestNotFoundException;
import seedu.guestnote.testutil.GuestBuilder;

public class UniqueGuestListTest {

    private final UniqueGuestList uniqueGuestList = new UniqueGuestList();

    @Test
    public void contains_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.contains(null));
    }

    @Test
    public void contains_guestNotInList_returnsFalse() {
        assertFalse(uniqueGuestList.contains(ALICE));
    }

    @Test
    public void contains_guestInList_returnsTrue() {
        uniqueGuestList.add(ALICE);
        assertTrue(uniqueGuestList.contains(ALICE));
    }

    @Test
    public void contains_guestWithSameIdentityFieldsInList_returnsTrue() {
        uniqueGuestList.add(ALICE);
        Guest editedAlice = new GuestBuilder(ALICE).withRequests(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueGuestList.contains(editedAlice));
    }

    @Test
    public void add_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.add(null));
    }

    @Test
    public void add_duplicateGuest_throwsDuplicateGuestException() {
        uniqueGuestList.add(ALICE);
        assertThrows(DuplicateGuestException.class, () -> uniqueGuestList.add(ALICE));
    }

    @Test
    public void setGuest_nullTargetGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.setGuest(null, ALICE));
    }

    @Test
    public void setGuest_nullEditedGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.setGuest(ALICE, null));
    }

    @Test
    public void setGuest_targetGuestNotInList_throwsGuestNotFoundException() {
        assertThrows(GuestNotFoundException.class, () -> uniqueGuestList.setGuest(ALICE, ALICE));
    }

    @Test
    public void setGuest_editedGuestIsSameGuest_success() {
        uniqueGuestList.add(ALICE);
        uniqueGuestList.setGuest(ALICE, ALICE);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        expectedUniqueGuestList.add(ALICE);
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuest_editedGuestHasSameIdentity_success() {
        uniqueGuestList.add(ALICE);
        Guest editedAlice = new GuestBuilder(ALICE).withRequests(VALID_TAG_FRIEND)
                .build();
        uniqueGuestList.setGuest(ALICE, editedAlice);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        expectedUniqueGuestList.add(editedAlice);
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuest_editedGuestHasDifferentIdentity_success() {
        uniqueGuestList.add(ALICE);
        uniqueGuestList.setGuest(ALICE, BOB);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        expectedUniqueGuestList.add(BOB);
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuest_editedGuestHasNonUniqueIdentity_throwsDuplicateGuestException() {
        uniqueGuestList.add(ALICE);
        uniqueGuestList.add(BOB);
        assertThrows(DuplicateGuestException.class, () -> uniqueGuestList.setGuest(ALICE, BOB));
    }

    @Test
    public void remove_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.remove(null));
    }

    @Test
    public void remove_guestDoesNotExist_throwsGuestNotFoundException() {
        assertThrows(GuestNotFoundException.class, () -> uniqueGuestList.remove(ALICE));
    }

    @Test
    public void remove_existingGuest_removesGuest() {
        uniqueGuestList.add(ALICE);
        uniqueGuestList.remove(ALICE);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuests_nullUniqueGuestList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.setGuests((UniqueGuestList) null));
    }

    @Test
    public void setGuests_uniqueGuestList_replacesOwnListWithProvidedUniqueGuestList() {
        uniqueGuestList.add(ALICE);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        expectedUniqueGuestList.add(BOB);
        uniqueGuestList.setGuests(expectedUniqueGuestList);
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuests_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.setGuests((List<Guest>) null));
    }

    @Test
    public void setGuests_list_replacesOwnListWithProvidedList() {
        uniqueGuestList.add(ALICE);
        List<Guest> guestList = Collections.singletonList(BOB);
        uniqueGuestList.setGuests(guestList);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        expectedUniqueGuestList.add(BOB);
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuests_listWithDuplicateGuests_throwsDuplicateGuestException() {
        List<Guest> listWithDuplicateGuests = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateGuestException.class, () -> uniqueGuestList.setGuests(listWithDuplicateGuests));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueGuestList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueGuestList.asUnmodifiableObservableList().toString(), uniqueGuestList.toString());
    }
}
