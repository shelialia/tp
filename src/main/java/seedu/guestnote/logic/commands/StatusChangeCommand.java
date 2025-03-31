package seedu.guestnote.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.guestnote.commons.core.index.Index;
import seedu.guestnote.logic.Messages;
import seedu.guestnote.logic.commands.exceptions.CommandException;
import seedu.guestnote.model.Model;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.Phone;
import seedu.guestnote.model.guest.Status;
import seedu.guestnote.model.request.UniqueRequestList;

/**
 * Abstract command for changing the status of a guest.
 */
public abstract class StatusChangeCommand extends Command {
    protected final Index targetIndex;

    public StatusChangeCommand(Index targetIndex) {
        this.targetIndex = requireNonNull(targetIndex);
    }

    protected Guest getGuestByIndex(Model model) throws CommandException {
        List<Guest> lastShownList = model.getFilteredGuestList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
        }

        return lastShownList.get(targetIndex.getZeroBased());
    }

    protected Guest updateGuestStatus(Guest guest, Status newStatus) {
        UniqueRequestList updatedRequests = new UniqueRequestList();
        updatedRequests.setRequests(guest.getRequests());

        return new Guest(
                guest.getName(),
                guest.getPhone().orElse(new Phone("")),
                guest.getEmail(),
                guest.getRoomNumber(),
                newStatus,
                updatedRequests
        );
    }

    protected void updateModelWithGuest(Model model, Guest oldGuest, Guest newGuest) {
        model.setGuest(oldGuest, newGuest);
    }
}
