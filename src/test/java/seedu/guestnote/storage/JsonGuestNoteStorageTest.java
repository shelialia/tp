package seedu.guestnote.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.guestnote.testutil.Assert.assertThrows;
import static seedu.guestnote.testutil.TypicalGuests.ALICE;
import static seedu.guestnote.testutil.TypicalGuests.HOON;
import static seedu.guestnote.testutil.TypicalGuests.IDA;
import static seedu.guestnote.testutil.TypicalGuests.getTypicalGuestNote;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.guestnote.commons.exceptions.DataLoadingException;
import seedu.guestnote.model.GuestNote;
import seedu.guestnote.model.ReadOnlyGuestNote;

public class JsonGuestNoteStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonGuestNoteStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readGuestNote_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readGuestNote(null));
    }

    private java.util.Optional<ReadOnlyGuestNote> readGuestNote(String filePath) throws Exception {
        return new JsonGuestNoteStorage(Paths.get(filePath)).readGuestNote(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readGuestNote("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readGuestNote(
                "notJsonFormatGuestNote" + ".json"));
    }

    @Test
    public void readGuestNote_invalidGuestGuestNote_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readGuestNote("invalidGuestGuestNote.json"));
    }

    @Test
    public void readGuestNote_invalidAndValidGuestGuestNote_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readGuestNote("invalidAndValidGuestGuestNote.json"));
    }

    @Test
    public void readAndSaveGuestNote_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempGuestNote.json");
        GuestNote original = getTypicalGuestNote();
        JsonGuestNoteStorage jsonGuestNoteStorage = new JsonGuestNoteStorage(filePath);

        // Save in new file and read back
        jsonGuestNoteStorage.saveGuestNote(original, filePath);
        ReadOnlyGuestNote readBack = jsonGuestNoteStorage.readGuestNote(filePath).get();
        assertEquals(original, new GuestNote(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGuest(HOON);
        original.removeGuest(ALICE);
        jsonGuestNoteStorage.saveGuestNote(original, filePath);
        readBack = jsonGuestNoteStorage.readGuestNote(filePath).get();
        assertEquals(original, new GuestNote(readBack));

        // Save and read without specifying file path
        original.addGuest(IDA);
        jsonGuestNoteStorage.saveGuestNote(original); // file path not specified
        readBack = jsonGuestNoteStorage.readGuestNote().get(); // file path not specified
        assertEquals(original, new GuestNote(readBack));

    }

    @Test
    public void saveGuestNote_nullGuestNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGuestNote(null, "SomeFile.json"));
    }

    /**
     * Saves {@code guestNote} at the specified {@code filePath}.
     */
    private void saveGuestNote(ReadOnlyGuestNote guestNote, String filePath) {
        try {
            new JsonGuestNoteStorage(Paths.get(filePath))
                    .saveGuestNote(guestNote, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveGuestNote_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGuestNote(new GuestNote(), null));
    }
}
