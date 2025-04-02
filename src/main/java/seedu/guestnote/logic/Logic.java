package seedu.guestnote.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.guestnote.commons.core.GuiSettings;
import seedu.guestnote.logic.commands.CommandResult;
import seedu.guestnote.logic.commands.exceptions.CommandException;
import seedu.guestnote.logic.parser.exceptions.ParseException;
import seedu.guestnote.model.ReadOnlyGuestNote;
import seedu.guestnote.model.guest.Guest;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the GuestNote.
     *
     * @see seedu.guestnote.model.Model#getGuestNote()
     */
    ReadOnlyGuestNote getGuestNote();

    /** Returns an unmodifiable view of the filtered list of guests */
    ObservableList<Guest> getFilteredGuestList();

    /**
     * Returns the user prefs' guestnote book file path.
     */
    Path getGuestNoteFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
