package seedu.guestnote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.guestnote.commons.core.LogsCenter;
import seedu.guestnote.commons.exceptions.DataLoadingException;
import seedu.guestnote.model.ReadOnlyGuestBook;
import seedu.guestnote.model.ReadOnlyUserPrefs;
import seedu.guestnote.model.UserPrefs;

/**
 * Manages storage of GuestBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private GuestBookStorage guestBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code GuestBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(GuestBookStorage guestBookStorage, UserPrefsStorage userPrefsStorage) {
        this.guestBookStorage = guestBookStorage;
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


    // ================ GuestBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return guestBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyGuestBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(guestBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyGuestBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return guestBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyGuestBook addressBook) throws IOException {
        saveAddressBook(addressBook, guestBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyGuestBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        guestBookStorage.saveAddressBook(addressBook, filePath);
    }

}
