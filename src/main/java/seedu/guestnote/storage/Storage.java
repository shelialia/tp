package seedu.guestnote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.guestnote.commons.exceptions.DataLoadingException;
import seedu.guestnote.model.ReadOnlyGuestBook;
import seedu.guestnote.model.ReadOnlyUserPrefs;
import seedu.guestnote.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends GuestBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyGuestBook> readAddressBook() throws DataLoadingException;

    @Override
    void saveAddressBook(ReadOnlyGuestBook addressBook) throws IOException;

}
