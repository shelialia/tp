package seedu.guestnote.logic.commands;

import static seedu.guestnote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guestnote.logic.commands.CommandTestUtil.showGuestAtIndex;
import static seedu.guestnote.testutil.TypicalGuests.getTypicalGuestNote;
import static seedu.guestnote.testutil.TypicalIndexes.INDEX_FIRST_GUEST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.guestnote.model.Model;
import seedu.guestnote.model.ModelManager;
import seedu.guestnote.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGuestNote(), new UserPrefs());
        expectedModel = new ModelManager(model.getGuestNote(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showGuestAtIndex(model, INDEX_FIRST_GUEST);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
