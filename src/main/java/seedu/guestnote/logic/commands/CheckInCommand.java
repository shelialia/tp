package seedu.guestnote.logic.commands;

import seedu.guestnote.commons.core.index.Index;
import seedu.guestnote.commons.util.ToStringBuilder;
import seedu.guestnote.logic.Messages;
import seedu.guestnote.logic.commands.exceptions.CommandException;
import seedu.guestnote.model.Model;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.Status;

/**
 * Checks in a guest identified using it's displayed index from the guestnote book.
 */
public class CheckInCommand extends StatusChangeCommand {
    public static final String COMMAND_WORD = "check-in";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Checks in the guest identified by the index number used in the displayed guest list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Checked in: %1$s";
    public static final String MESSAGE_ALREADY_CHECKED_IN = "Guest is already checked in.";

    public CheckInCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Guest guest = getGuestByIndex(model);

        if (guest.getStatus() == Status.CHECKED_IN) {
            throw new CommandException(MESSAGE_ALREADY_CHECKED_IN);
        }

        Guest updatedGuest = updateGuestStatus(guest, Status.CHECKED_IN);
        updateModelWithGuest(model, guest, updatedGuest);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(updatedGuest)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckInCommand)) {
            return false;
        }

        CheckInCommand otherCheckInCommand = (CheckInCommand) other;
        return targetIndex.equals(otherCheckInCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
