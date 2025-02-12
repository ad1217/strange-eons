package ca.cgjennings.apps.arkham.project;

import ca.cgjennings.apps.arkham.StrangeEons;
import java.io.IOException;
import java.util.List;
import static resources.Language.string;

/**
 * A task action that displays a window in order to simulate a printed deck of
 * cards. A virtual deck can be used to play test a deck without printing.
 *
 * @author Chris Jennings <https://cgjennings.ca/contact>
 * @since 3.0
 */
public class VirtualDeck extends TaskAction {

    @Override
    public String getLabel() {
        return string("pa-vdeck");
    }

    @Override
    public String getDescription() {
        return string("pa-vdeck-tt");
    }

    @Override
    public boolean perform(final Project project, final Task task, final Member member) {

        CopiesList copies;
        try {
            copies = new CopiesList(task);
        } catch (IOException e) {
            copies = new CopiesList();
        }
        List<Member> list = ProjectUtilities.listMatchingMembers(task, true, "eon");
        VirtualDeckDialog d = new VirtualDeckDialog(StrangeEons.getWindow(), list, copies);
        d.setVisible(true);
        return true;
    }

    @Override
    public boolean appliesTo(Project project, Task task, Member member) {
        if (member != null || task == null) {
            return false;
        }
        String type = task.getSettings().get(Task.KEY_TYPE);
        if (NewTaskType.DECK_TYPE.equals(type)) {
            return true;
        }
        if (NewTaskType.FACTORY_TYPE.equals(type)) {
            return true;
        }
        return false;
    }

}
