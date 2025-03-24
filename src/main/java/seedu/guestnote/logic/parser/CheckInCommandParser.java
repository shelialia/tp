package seedu.guestnote.logic.parser;

import static seedu.guestnote.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.guestnote.commons.core.index.Index;
import seedu.guestnote.logic.commands.CheckInCommand;
import seedu.guestnote.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CheckInCommand object
 */
public class CheckInCommandParser implements Parser<CheckInCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckInCommand
     * and returns a CheckInCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckInCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CheckInCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckInCommand.MESSAGE_USAGE), pe);
        }
    }
}
