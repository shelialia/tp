package seedu.guestnote.logic.commands;

import static seedu.guestnote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guestnote.testutil.TypicalGuests.getTypicalGuestNote;

import org.junit.jupiter.api.Test;

import seedu.guestnote.model.GuestNote;
import seedu.guestnote.model.Model;
import seedu.guestnote.model.ModelManager;
import seedu.guestnote.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyGuestNote_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyGuestNote_success() {
        Model model = new ModelManager(getTypicalGuestNote(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalGuestNote(), new UserPrefs());
        expectedModel.setGuestNote(new GuestNote());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
