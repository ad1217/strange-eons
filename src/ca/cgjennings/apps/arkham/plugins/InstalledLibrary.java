package ca.cgjennings.apps.arkham.plugins;

import static ca.cgjennings.apps.arkham.plugins.PluginRoot.*;
import ca.cgjennings.graphics.ImageUtilities;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.Icon;
import resources.Language;
import resources.ResourceKit;

/**
 * Represents an installed library. Generally, much less information is
 * available about libraries than about other kinds of bundle objects because
 * library bundles do not contain specific plug-ins.
 *
 * @author Chris Jennings <https://cgjennings.ca/contact>
 * @since 3.0
 * @see BundleInstaller#getInstalledLibraries()
 */
public final class InstalledLibrary extends InstalledBundleObject {

    private String name;
    private String desc;
    private Icon icon;
    private BufferedImage image;

    InstalledLibrary(PluginBundle bundle) throws IOException {
        super(bundle);

        PluginRoot root = getPluginRoot();
        if (root != null) {
            name = root.getLocalizedClientProperty(CLIENT_KEY_NAME);
            desc = root.getLocalizedClientProperty(CLIENT_KEY_DESCRIPTION);
            String imageSrc = root.getClientProperty(CLIENT_KEY_IMAGE);
            if (imageSrc != null) {
                image = ResourceKit.getImage(imageSrc);
                icon = ImageUtilities.createIconForSize(image, 18);
            }
        }

        // place defaults in any fields we couldn't fill in
        if (name == null) {
            // create name from file name
            name = bundle.getFile().getName();
            // make the name more human-friendly:
            // - remove core- and .selibrary, insert spaces into camel case names
            if (name.startsWith("core-")) {
                name = name.substring("core-".length());
            }
            boolean lastWasLowerCase = false;
            StringBuilder b = new StringBuilder(name.length());
            for (int i = 0; i < name.length(); ++i) {
                char c = name.charAt(i);
                if (c == '.') {
                    break;
                }
                if (lastWasLowerCase && (Character.isUpperCase(c) || Character.isDigit(c))) {
                    b.append(' ');
                }
                b.append(c);
                lastWasLowerCase = Character.isLowerCase(c);
            }
            name = b.toString();
        }

        if (desc == null) {
            desc = Language.string("plug-lib-desc");
        }

        if (icon == null) {
            icon = PluginBundle.getIcon(bundle.getFile(), true);
        }

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public BufferedImage getRepresentativeImage() {
        if (image == null) {
            image = ImageUtilities.iconToImage(PluginBundle.getIcon("selibrary", true));
        }
        return image;
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    boolean isLoaded() {
        return true;
    }
}
