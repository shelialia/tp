package seedu.guestnote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.guestnote.commons.core.LogsCenter;
import seedu.guestnote.commons.exceptions.DataLoadingException;
import seedu.guestnote.model.ReadOnlyGuestNote;
import seedu.guestnote.model.ReadOnlyUserPrefs;
import seedu.guestnote.model.UserPrefs;

/**
 * Manages storage of GuestNote data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private GuestNoteStorage guestNoteStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code GuestNoteStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(GuestNoteStorage guestNoteStorage, UserPrefsStorage userPrefsStorage) {
        this.guestNoteStorage = guestNoteStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ GuestNote methods ==============================

    @Override
    public Path getGuestNoteFilePath() {
        return guestNoteStorage.getGuestNoteFilePath();
    }

    @Override
    public Optional<ReadOnlyGuestNote> readGuestNote() throws DataLoadingException {
        return readGuestNote(guestNoteStorage.getGuestNoteFilePath());
    }

    @Override
    public Optional<ReadOnlyGuestNote> readGuestNote(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return guestNoteStorage.readGuestNote(filePath);
    }

    @Override
    public void saveGuestNote(ReadOnlyGuestNote guestNote) throws IOException {
        saveGuestNote(guestNote, guestNoteStorage.getGuestNoteFilePath());
    }

    @Override
    public void saveGuestNote(ReadOnlyGuestNote guestNote, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        guestNoteStorage.saveGuestNote(guestNote, filePath);
    }

}
