package seedu.guestnote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.guestnote.commons.exceptions.DataLoadingException;
import seedu.guestnote.model.GuestBook;
import seedu.guestnote.model.ReadOnlyGuestBook;

/**
 * Represents a storage for {@link GuestBook}.
 */
public interface GuestBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns GuestBook data as a {@link ReadOnlyGuestBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyGuestBook> readAddressBook() throws DataLoadingException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyGuestBook> readAddressBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyGuestBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyGuestBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyGuestBook)
     */
    void saveAddressBook(ReadOnlyGuestBook addressBook, Path filePath) throws IOException;

}
