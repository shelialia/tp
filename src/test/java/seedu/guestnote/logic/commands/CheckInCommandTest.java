package seedu.guestnote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.guestnote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guestnote.testutil.TypicalGuests.getTypicalGuestNote;
import static seedu.guestnote.testutil.TypicalIndexes.INDEX_FIRST_GUEST;
import static seedu.guestnote.testutil.TypicalIndexes.INDEX_SECOND_GUEST;

import org.junit.jupiter.api.Test;

import seedu.guestnote.commons.core.index.Index;
import seedu.guestnote.logic.Messages;
import seedu.guestnote.model.Model;
import seedu.guestnote.model.ModelManager;
import seedu.guestnote.model.UserPrefs;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.Status;
import seedu.guestnote.testutil.GuestBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CheckInCommand}.
 */
public class CheckInCommandTest {

    private Model model = new ModelManager(getTypicalGuestNote(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Guest guestToCheckIn = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased());
        CheckInCommand checkInCommand = new CheckInCommand(INDEX_FIRST_GUEST);

        Guest checkedInGuest =
                new GuestBuilder(guestToCheckIn).withStatus(Status.CHECKED_IN).build();
        String expectedMessage = String.format(CheckInCommand.MESSAGE_SUCCESS, Messages.format(checkedInGuest));

        ModelManager expectedModel = new ModelManager(model.getGuestNote(), new UserPrefs());
        expectedModel.setGuest(guestToCheckIn, checkedInGuest);

        assertCommandSuccess(checkInCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGuestList().size() + 1);
        CheckInCommand checkInCommand = new CheckInCommand(outOfBoundIndex);

        assertCommandFailure(checkInCommand, model, Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyCheckedIn_throwsCommandException() {
        Guest guestToCheckIn = new GuestBuilder().withStatus(Status.CHECKED_IN).build();
        model.setGuest(model.getFilteredGuestList().get(0), guestToCheckIn);
        CheckInCommand checkInCommand = new CheckInCommand(INDEX_FIRST_GUEST);

        assertCommandFailure(checkInCommand, model, CheckInCommand.MESSAGE_ALREADY_CHECKED_IN);
    }

    @Test
    public void execute_alreadyCheckedOut_throwsCommandException() {
        Guest guestToCheckIn = new GuestBuilder().withStatus(Status.CHECKED_OUT).build();
        model.setGuest(model.getFilteredGuestList().get(0), guestToCheckIn);
        CheckInCommand checkInCommand = new CheckInCommand(INDEX_FIRST_GUEST);

        assertCommandFailure(checkInCommand, model, CheckInCommand.MESSAGE_ALREADY_CHECKED_OUT);
    }

    @Test
    public void equals() {
        CheckInCommand checkInFirstCommand = new CheckInCommand(INDEX_FIRST_GUEST);
        CheckInCommand checkInSecondCommand = new CheckInCommand(INDEX_SECOND_GUEST);

        // same object -> returns true
        assertTrue(checkInFirstCommand.equals(checkInFirstCommand));

        // same values -> returns true
        CheckInCommand checkInFirstCommandCopy = new CheckInCommand(INDEX_FIRST_GUEST);
        assertTrue(checkInFirstCommand.equals(checkInFirstCommandCopy));

        // different types -> returns false
        assertFalse(checkInFirstCommand.equals(1));

        // null -> returns false
        assertFalse(checkInFirstCommand.equals(null));

        // different guest -> returns false
        assertFalse(checkInFirstCommand.equals(checkInSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        CheckInCommand checkInCommand = new CheckInCommand(targetIndex);
        String expected = CheckInCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, checkInCommand.toString());
    }
}
