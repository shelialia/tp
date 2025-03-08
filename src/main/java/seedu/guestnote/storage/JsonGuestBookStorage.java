package seedu.guestnote.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.guestnote.commons.core.LogsCenter;
import seedu.guestnote.commons.exceptions.DataLoadingException;
import seedu.guestnote.commons.exceptions.IllegalValueException;
import seedu.guestnote.commons.util.FileUtil;
import seedu.guestnote.commons.util.JsonUtil;
import seedu.guestnote.model.ReadOnlyGuestBook;

/**
 * A class to access GuestBook data stored as a json file on the hard disk.
 */
public class JsonGuestBookStorage implements GuestBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonGuestBookStorage.class);

    private Path filePath;

    public JsonGuestBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGuestBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyGuestBook> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableGuestBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableGuestBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyGuestBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyGuestBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyGuestBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGuestBook(addressBook), filePath);
    }

}
