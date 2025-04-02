package seedu.guestnote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.guestnote.commons.exceptions.DataLoadingException;
import seedu.guestnote.model.ReadOnlyGuestNote;
import seedu.guestnote.model.ReadOnlyUserPrefs;
import seedu.guestnote.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends GuestNoteStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getGuestNoteFilePath();

    @Override
    Optional<ReadOnlyGuestNote> readGuestNote() throws DataLoadingException;

    @Override
    void saveGuestNote(ReadOnlyGuestNote guestNote) throws IOException;

}
