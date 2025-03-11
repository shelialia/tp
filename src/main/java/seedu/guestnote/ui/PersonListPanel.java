package seedu.guestnote.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.guestnote.commons.core.LogsCenter;
import seedu.guestnote.model.guest.Guest;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Guest> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Guest> guestList) {
        super(FXML);
        personListView.setItems(guestList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Guest} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Guest> {
        @Override
        protected void updateItem(Guest guest, boolean empty) {
            super.updateItem(guest, empty);

            if (empty || guest == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(guest, getIndex() + 1).getRoot());
            }
        }
    }

}
