package seedu.guestnote.logic.parser;

import static seedu.guestnote.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.guestnote.commons.core.index.Index;
import seedu.guestnote.logic.commands.CheckOutCommand;
import seedu.guestnote.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CheckOutCommand object
 */
public class CheckOutCommandParser implements Parser<CheckOutCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckOutCommand
     * and returns a CheckOutCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckOutCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CheckOutCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckOutCommand.MESSAGE_USAGE), pe);
        }
    }
}
