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
 * {@code CheckOutCommand}.
 */
public class CheckOutCommandTest {

    private Model model = new ModelManager(getTypicalGuestNote(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Guest guestToCheckOut = new GuestBuilder().withStatus(Status.CHECKED_IN).build();
        model.setGuest(model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased()),
                guestToCheckOut);
        CheckOutCommand checkOutCommand = new CheckOutCommand(INDEX_FIRST_GUEST);

        Guest checkedOutGuest = new GuestBuilder(guestToCheckOut).withStatus(Status.CHECKED_OUT).build();
        String expectedMessage = String.format(CheckOutCommand.MESSAGE_SUCCESS, Messages.format(checkedOutGuest));

        ModelManager expectedModel = new ModelManager(model.getGuestNote(), new UserPrefs());
        expectedModel.setGuest(guestToCheckOut, checkedOutGuest);

        assertCommandSuccess(checkOutCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGuestList().size() + 1);
        CheckOutCommand checkOutCommand = new CheckOutCommand(outOfBoundIndex);

        assertCommandFailure(checkOutCommand, model,
                Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
    }

    @Test
    public void execute_bookingStatus_throwsCommandException() {
        Guest guestToCheckOut = new GuestBuilder().withStatus(Status.BOOKED).build();
        model.setGuest(model.getFilteredGuestList().get(0), guestToCheckOut);
        CheckOutCommand checkOutCommand = new CheckOutCommand(INDEX_FIRST_GUEST);

        assertCommandFailure(checkOutCommand, model, CheckOutCommand.MESSAGE_NOT_CHECKED_IN);
    }

    @Test
    public void execute_alreadyCheckedOut_throwsCommandException() {
        Guest guestToCheckOut = new GuestBuilder().withStatus(Status.CHECKED_OUT).build();
        model.setGuest(model.getFilteredGuestList().get(0), guestToCheckOut);
        CheckOutCommand checkOutCommand = new CheckOutCommand(INDEX_FIRST_GUEST);

        assertCommandFailure(checkOutCommand, model, CheckOutCommand.MESSAGE_ALREADY_CHECKED_OUT);
    }

    @Test
    public void equals() {
        CheckOutCommand checkOutFirstCommand = new CheckOutCommand(INDEX_FIRST_GUEST);
        CheckOutCommand checkOutSecondCommand = new CheckOutCommand(INDEX_SECOND_GUEST);

        // same object -> returns true
        assertTrue(checkOutFirstCommand.equals(checkOutFirstCommand));

        // same values -> returns true
        CheckOutCommand checkOutFirstCommandCopy = new CheckOutCommand(INDEX_FIRST_GUEST);
        assertTrue(checkOutFirstCommand.equals(checkOutFirstCommandCopy));

        // different types -> returns false
        assertFalse(checkOutFirstCommand.equals(1));

        // null -> returns false
        assertFalse(checkOutFirstCommand.equals(null));

        // different guest -> returns false
        assertFalse(checkOutFirstCommand.equals(checkOutSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        CheckOutCommand checkOutCommand = new CheckOutCommand(targetIndex);
        String expected = CheckOutCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, checkOutCommand.toString());
    }
}
