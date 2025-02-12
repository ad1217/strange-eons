package ca.cgjennings.ui.theme;

import java.awt.Color;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 * The Hydra theme is a built-in theme featuring blue highlights and an abstract
 * wave motif.
 *
 * @author Chris Jennings <https://cgjennings.ca/contact>
 * @since 2.0
 */
public class HydraTheme extends Theme {

    public HydraTheme() {
    }

    @Override
    public String getThemeName() {
        return "Hydra";
    }

    @Override
    public void modifyManagerDefaults(UIDefaults defaults) {
        UIManager.put(OVERRIDE_LAF_MESSAGE_ICONS, true);
        defaults.put(CONSOLE_BACKROUND, new Color(0x34_536b));
        defaults.put(CONSOLE_OUTPUT, new Color(0xf3_e193));
        defaults.put(CONSOLE_ERROR, new Color(0xf9_9d39));
        defaults.put(CONSOLE_SELECTION_BACKGROUND, new Color(0xf3_e193));
        defaults.put(CONSOLE_SELECTION_FOREGROUND, new Color(0x3d_4b28));
        defaults.put(CONSOLE_BACKGROUND_PAINTER, new DefaultConsolePainter());
    }

    @Override
    public void modifyLookAndFeelDefaults(UIDefaults defaults) {
        defaults.put("TitledBorder.titleColor", new Color(0x3d_4b28));
    }
}
