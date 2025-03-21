package seedu.guestnote.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.guestnote.model.guest.Guest;

/**
 * An UI component that displays information of a {@code Guest}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on GuestBook level 4</a>
     */

    public final Guest guest;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane roomNumber;
    @FXML
    private FlowPane requests;


    /**
     * Creates a {@code PersonCode} with the given {@code Guest} and index to display.
     */
    public PersonCard(Guest guest, int displayedIndex) {
        super(FXML);
        this.guest = guest;
        id.setText(displayedIndex + ". ");
        name.setText(guest.getName().fullName);
        phone.setText(guest.getPhone().value);
        email.setText(guest.getEmail().value);
        roomNumber.getChildren().add(new Label(guest.getRoomNumber().roomNumber));
        final int[] counter = {1};
        guest.getRequests()
                .forEach(request -> {
                    Label requestLabel = new Label(counter[0] + ". " + request.requestName);
                    requests.getChildren().add(requestLabel);
                    counter[0]++;
                });
    }
}
