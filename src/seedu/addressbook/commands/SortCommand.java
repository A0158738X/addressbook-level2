package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a sorted list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    public CommandResult execute() {
        List<ReadOnlyPerson> sortedPersons = sortName(addressBook.getAllPersons().immutableListView());
        return new CommandResult(getMessageForPersonSortedListShownSummary(sortedPersons), sortedPersons);
    }

    public List<ReadOnlyPerson> sortName(List<ReadOnlyPerson> list) {

        NameComparator nameComparator = new NameComparator();
        List<ReadOnlyPerson> sortedContacts = new ArrayList<>();
        for (ReadOnlyPerson person: addressBook.getAllPersons()) {
            sortedContacts.add(person);
        }
        Collections.sort(sortedContacts, nameComparator);

        return sortedContacts;
    }

    class NameComparator implements Comparator<ReadOnlyPerson> {
         public int compare(ReadOnlyPerson p1, ReadOnlyPerson p2) {
                        return p1.getName().fullName.toUpperCase().compareTo(p2.getName().fullName.toUpperCase());
         }
     }
}
