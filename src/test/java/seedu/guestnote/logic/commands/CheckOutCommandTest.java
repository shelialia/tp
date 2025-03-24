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
 * {@code CheckOutCommand}.
 */
public class CheckOutCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Guest guestToCheckOut = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        CheckOutCommand checkOutCommand = new CheckOutCommand(INDEX_FIRST_PERSON);

        Guest checkedOutGuest = new PersonBuilder(guestToCheckOut).withStatus(Status.CHECKED_OUT).build();
        String expectedMessage = String.format(CheckOutCommand.MESSAGE_SUCCESS, Messages.format(checkedOutGuest));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(guestToCheckOut, checkedOutGuest);

        assertCommandSuccess(checkOutCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CheckOutCommand checkOutCommand = new CheckOutCommand(outOfBoundIndex);

        assertCommandFailure(checkOutCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyCheckedOut_throwsCommandException() {
        Guest guestToCheckOut = new PersonBuilder().withStatus(Status.CHECKED_OUT).build();
        model.setPerson(model.getFilteredPersonList().get(0), guestToCheckOut);
        CheckOutCommand checkOutCommand = new CheckOutCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(checkOutCommand, model, CheckOutCommand.MESSAGE_ALREADY_CHECKED_OUT);
    }

    @Test
    public void equals() {
        CheckOutCommand checkOutFirstCommand = new CheckOutCommand(INDEX_FIRST_PERSON);
        CheckOutCommand checkOutSecondCommand = new CheckOutCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(checkOutFirstCommand.equals(checkOutFirstCommand));

        // same values -> returns true
        CheckOutCommand checkOutFirstCommandCopy = new CheckOutCommand(INDEX_FIRST_PERSON);
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
