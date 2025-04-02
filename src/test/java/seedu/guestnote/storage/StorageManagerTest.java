package seedu.guestnote.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.guestnote.testutil.TypicalGuests.getTypicalGuestNote;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.guestnote.commons.core.GuiSettings;
import seedu.guestnote.model.GuestNote;
import seedu.guestnote.model.ReadOnlyGuestNote;
import seedu.guestnote.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonGuestNoteStorage guestNoteStorage = new JsonGuestNoteStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(guestNoteStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void guestNoteReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonGuestNoteStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonGuestNoteStorageTest} class.
         */
        GuestNote original = getTypicalGuestNote();
        storageManager.saveGuestNote(original);
        ReadOnlyGuestNote retrieved = storageManager.readGuestNote().get();
        assertEquals(original, new GuestNote(retrieved));
    }

    @Test
    public void getGuestNoteFilePath() {
        assertNotNull(storageManager.getGuestNoteFilePath());
    }

}
