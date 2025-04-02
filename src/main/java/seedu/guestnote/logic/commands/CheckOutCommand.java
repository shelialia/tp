package seedu.guestnote.logic.commands;

import seedu.guestnote.commons.core.index.Index;
import seedu.guestnote.commons.util.ToStringBuilder;
import seedu.guestnote.logic.Messages;
import seedu.guestnote.logic.commands.exceptions.CommandException;
import seedu.guestnote.model.Model;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.Status;

/**
 * Checks out a guest identified using it's displayed index from the guestnote book.
 */
public class CheckOutCommand extends StatusChangeCommand {
    public static final String COMMAND_WORD = "check-out";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Checks out the guest identified by the index number used in the displayed guest list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Checked out: %1$s";
    public static final String MESSAGE_NOT_CHECKED_IN = "Guest has not checked in.";
    public static final String MESSAGE_ALREADY_CHECKED_OUT = "Guest has already checked out.";

    public CheckOutCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Guest guest = getGuestByIndex(model);

        if (guest.getStatus() == Status.BOOKED) {
            throw new CommandException(MESSAGE_NOT_CHECKED_IN);
        }

        if (guest.getStatus() == Status.CHECKED_OUT) {
            throw new CommandException(MESSAGE_ALREADY_CHECKED_OUT);
        }

        Guest updatedGuest = updateGuestStatus(guest, Status.CHECKED_OUT);
        updateModelWithGuest(model, guest, updatedGuest);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(updatedGuest)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckOutCommand)) {
            return false;
        }

        CheckOutCommand otherCheckOutCommand = (CheckOutCommand) other;
        return targetIndex.equals(otherCheckOutCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
