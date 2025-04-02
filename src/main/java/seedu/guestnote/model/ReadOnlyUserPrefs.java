package seedu.guestnote.model;

import java.nio.file.Path;

import seedu.guestnote.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getGuestNoteFilePath();
}
