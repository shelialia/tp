package seedu.guestnote.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.guestnote.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.guestnote.commons.exceptions.IllegalValueException;
import seedu.guestnote.commons.util.JsonUtil;
import seedu.guestnote.model.GuestBook;
import seedu.guestnote.testutil.TypicalGuests;

public class JsonSerializableGuestBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableGuestBookTest");
    private static final Path TYPICAL_GUESTS_FILE = TEST_DATA_FOLDER.resolve("typicalGuestsGuestBook.json");
    private static final Path INVALID_GUEST_FILE = TEST_DATA_FOLDER.resolve("invalidGuestGuestBook.json");
    private static final Path DUPLICATE_GUEST_FILE = TEST_DATA_FOLDER.resolve("duplicateGuestGuestBook.json");

    @Test
    public void toModelType_typicalGuestsFile_success() throws Exception {
        JsonSerializableGuestBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_GUESTS_FILE,
                JsonSerializableGuestBook.class).get();
        GuestBook guestBookFromFile = dataFromFile.toModelType();
        GuestBook typicalGuestsGuestBook = TypicalGuests.getTypicalAddressBook();
        assertEquals(guestBookFromFile, typicalGuestsGuestBook);
    }

    @Test
    public void toModelType_invalidGuestFile_throwsIllegalValueException() throws Exception {
        JsonSerializableGuestBook dataFromFile = JsonUtil.readJsonFile(INVALID_GUEST_FILE,
                JsonSerializableGuestBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGuests_throwsIllegalValueException() throws Exception {
        JsonSerializableGuestBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GUEST_FILE,
                JsonSerializableGuestBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableGuestBook.MESSAGE_DUPLICATE_GUEST,
                dataFromFile::toModelType);
    }

}
