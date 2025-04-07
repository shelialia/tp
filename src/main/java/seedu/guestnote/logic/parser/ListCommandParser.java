package seedu.guestnote.logic.parser;

import static seedu.guestnote.logic.parser.CliSyntax.PREFIX_REQUEST;

import java.util.Arrays;

import seedu.guestnote.logic.commands.ListCommand;
import seedu.guestnote.logic.parser.exceptions.ParseException;
import seedu.guestnote.model.guest.NameContainsSubstringsPredicate;

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {

    @Override
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListCommand(); // Default list command
        }

        // Tokenize by whitespace
        String[] tokens = trimmedArgs.split("\\s+");
        long rqCount = Arrays.stream(tokens)
                             .filter(token -> token.equalsIgnoreCase(String.valueOf(PREFIX_REQUEST)))
                             .count();

        if (rqCount > 1) {
            throw new ParseException("Too many " + PREFIX_REQUEST + " tokens. Only one is allowed.");
        }

        boolean hasRq = rqCount == 1;
        StringBuilder filterBuilder = new StringBuilder();

        for (String token : tokens) {
            if (token.equalsIgnoreCase(String.valueOf(PREFIX_REQUEST))) {
                continue;
            }
            if (token.toLowerCase().contains(String.valueOf(PREFIX_REQUEST))) {
                throw new ParseException(PREFIX_REQUEST + " must be separated by whitespace.");
            }
            filterBuilder.append(token).append(" ");
        }

        String filterPart = filterBuilder.toString().trim();

        if (hasRq && filterPart.isEmpty()) {
            return new ListCommand(true); // List guests with requests only
        }

        if (hasRq) {
            return new ListCommand(true,
                new NameContainsSubstringsPredicate(Arrays.asList(filterPart.split("\\s+"))));
        }

        return new ListCommand(new NameContainsSubstringsPredicate(Arrays.asList(filterPart.split("\\s+"))));
    }
}
