package seedu.guestnote.logic.commands;

import static seedu.guestnote.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.guestnote.model.Model;
import seedu.guestnote.model.guest.NameContainsKeywordsPredicate;

/**
 * Lists all guests in the guest book.
 * This command supports listing all guests as well as filtering the list based on a search query.
 * It utilizes the model's filtered person list to display the guests.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private final NameContainsKeywordsPredicate predicate;

    public ListCommand() {
        this.predicate = null;
    }

    public ListCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the list command and returns the result.
     * If a search query is provided, the command filters the guest list by matching the guest's name or ID.
     * Otherwise, it displays all guests.
     *
     * @param model The {@code Model} containing the guest book data.
     * @return A {@link CommandResult} containing the success message for testing purpose.
     */
    @Override
    public CommandResult execute(Model model) {
        // Update the filtered list to show all guests.
        if (predicate == null) {
            // Default behavior: list all guests
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        } else {
            // Search behavior: filter list based on the predicate
            model.updateFilteredPersonList(predicate);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
