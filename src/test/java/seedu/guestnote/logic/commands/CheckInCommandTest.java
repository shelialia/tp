package seedu.guestnote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.guestnote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guestnote.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.guestnote.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.guestnote.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.guestnote.commons.core.index.Index;
import seedu.guestnote.logic.Messages;
import seedu.guestnote.model.Model;
import seedu.guestnote.model.ModelManager;
import seedu.guestnote.model.UserPrefs;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.Status;
import seedu.guestnote.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CheckInCommand}.
 */
public class CheckInCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Guest guestToCheckIn = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        CheckInCommand checkInCommand = new CheckInCommand(INDEX_FIRST_PERSON);

        Guest checkedInGuest = new PersonBuilder(guestToCheckIn).withStatus(Status.CHECKED_IN).build();
        String expectedMessage = String.format(CheckInCommand.MESSAGE_SUCCESS, Messages.format(checkedInGuest));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(guestToCheckIn, checkedInGuest);

        assertCommandSuccess(checkInCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CheckInCommand checkInCommand = new CheckInCommand(outOfBoundIndex);

        assertCommandFailure(checkInCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyCheckedIn_throwsCommandException() {
        Guest guestToCheckIn = new PersonBuilder().withStatus(Status.CHECKED_IN).build();
        model.setPerson(model.getFilteredPersonList().get(0), guestToCheckIn);
        CheckInCommand checkInCommand = new CheckInCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(checkInCommand, model, CheckInCommand.MESSAGE_ALREADY_CHECKED_IN);
    }

    @Test
    public void execute_alreadyCheckedOut_throwsCommandException() {
        Guest guestToCheckIn = new PersonBuilder().withStatus(Status.CHECKED_OUT).build();
        model.setPerson(model.getFilteredPersonList().get(0), guestToCheckIn);
        CheckInCommand checkInCommand = new CheckInCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(checkInCommand, model, CheckInCommand.MESSAGE_ALREADY_CHECKED_OUT);
    }

    @Test
    public void equals() {
        CheckInCommand checkInFirstCommand = new CheckInCommand(INDEX_FIRST_PERSON);
        CheckInCommand checkInSecondCommand = new CheckInCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(checkInFirstCommand.equals(checkInFirstCommand));

        // same values -> returns true
        CheckInCommand checkInFirstCommandCopy = new CheckInCommand(INDEX_FIRST_PERSON);
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
