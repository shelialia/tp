package seedu.guestnote.logic.commands;

import static seedu.guestnote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.guestnote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guestnote.testutil.TypicalGuests.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.guestnote.logic.Messages;
import seedu.guestnote.model.Model;
import seedu.guestnote.model.ModelManager;
import seedu.guestnote.model.UserPrefs;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.testutil.GuestBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newGuest_success() {
        Guest validGuest = new GuestBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addGuest(validGuest);

        assertCommandSuccess(new AddCommand(validGuest), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validGuest)),
                expectedModel);
    }

    @Test
    public void execute_duplicateGuest_throwsCommandException() {
        Guest guestInList = model.getAddressBook().getGuestList().get(0);
        assertCommandFailure(new AddCommand(guestInList), model,
                AddCommand.MESSAGE_DUPLICATE_GUEST);
    }

}
