package ca.cgjennings.apps.arkham.dialog;

import ca.cgjennings.apps.arkham.BusyDialog;
import ca.cgjennings.apps.arkham.StrangeEons;
import ca.cgjennings.apps.arkham.plugins.BundleInstaller;
import ca.cgjennings.platform.AgnosticDialog;
import ca.cgjennings.platform.PlatformSupport;
import ca.cgjennings.ui.TreeLabelExposer;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.JButton;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import resources.Language;
import static resources.Language.string;

/**
 * A browser interface for selecting an image from the program's (and plug-ins')
 * image resources.
 *
 * @author Chris Jennings <https://cgjennings.ca/contact>
 */
@SuppressWarnings("serial")
public class ImageResourceBrowser extends javax.swing.JDialog implements AgnosticDialog {

    public ImageResourceBrowser() {
        this(StrangeEons.getWindow(), true);
    }

    /**
     * Creates new form ImageResourceBrowser
     */
    public ImageResourceBrowser(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        new TreeLabelExposer(resourceTree);
        resourceTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        setLocationRelativeTo(parent);
        PlatformSupport.makeAgnosticDialog(this, okBtn, cancelBtn);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pathLabelMenu = new javax.swing.JPopupMenu();
        copyLabelItem = new javax.swing.JMenuItem();
        cancelBtn = new javax.swing.JButton();
        okBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resourceTree = new javax.swing.JTree();
        pathLabel = new javax.swing.JLabel();
        imageViewer = new ca.cgjennings.ui.fcpreview.ResourcePreviewer();

        copyLabelItem.setText(string( "copy" )); // NOI18N
        copyLabelItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyLabelItemActionPerformed(evt);
            }
        });
        pathLabelMenu.add(copyLabelItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(string( "rbd-title" )); // NOI18N

        cancelBtn.setText(string( "cancel" )); // NOI18N

        okBtn.setText(string( "rbd-ok" )); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        resourceTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        resourceTree.setRootVisible(false);
        resourceTree.setToggleClickCount(1);
        resourceTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                resourceTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(resourceTree);

        pathLabel.setFont(pathLabel.getFont().deriveFont(pathLabel.getFont().getSize()-1f));
        pathLabel.setText("     ");
        pathLabel.setComponentPopupMenu(pathLabelMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imageViewer, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pathLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(okBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                    .addComponent(imageViewer, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(okBtn)
                    .addComponent(pathLabel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void resourceTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_resourceTreeValueChanged
            String path = getSelectedResource();
            JButton ok = PlatformSupport.getAgnosticOK(true, okBtn, cancelBtn);
            if (path != null) {
                imageViewer.showPreview(path);
                pathLabel.setText("res://" + path);
            } else {
                pathLabel.setText(" ");
            }
            copyLabelItem.setEnabled(path != null);
            ok.setEnabled(path != null);
	}//GEN-LAST:event_resourceTreeValueChanged

	private void copyLabelItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyLabelItemActionPerformed
            StringSelection sel = new StringSelection(pathLabel.getText());
            getToolkit().getSystemClipboard().setContents(sel, sel);
	}//GEN-LAST:event_copyLabelItemActionPerformed

    // only needed when running Strange Eons from the IDE, so no progress info is used
    private void addImagesFromFolder(File folder, Set<String> resources) {
        for (File child : folder.listFiles()) {
            if (child.isDirectory()) {
                addImagesFromFolder(child, resources);
            } else {
                String name = child.getAbsolutePath().replace(File.separatorChar, '/');
                int pos = name.indexOf("resources/");
                if (pos >= 0 && matchFile(name)) {
                    resources.add(name.substring(pos + "resources/".length()));
                }
            }
        }
    }

    private void addImagesFromBundles(final List<File> jars, final Set<String> resources) {
        new BusyDialog(StrangeEons.getWindow(), string("rbd-scan"), () -> {
            try {
                for (File f : jars) {
                    BusyDialog.getCurrentDialog().setStatusText(f.getName());
                    ZipFile zip = null;
                    try {
                        zip = new ZipFile(f);
                        Enumeration<? extends ZipEntry> entries = zip.entries();
                        while (entries.hasMoreElements()) {
                            ZipEntry e = entries.nextElement();
                            String name1 = e.getName();
                            if (name1.startsWith("resources/") && matchFile(name1)) {
                                resources.add(e.getName().substring("resources/".length()));
                            }
                        }
                    }catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (zip != null) {
                            try {
                                zip.close();
                            } catch (IOException e) {
                            }
                        }
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            if (treeModel == null) {
                Set<String> resourceList = new HashSet<>();
                LinkedList<File> jars = new LinkedList<>();
                File seLib = BundleInstaller.getApplicationLibrary();
                if (seLib.isDirectory()) {
                    addImagesFromFolder(seLib, resourceList);
                } else {
                    jars.add(seLib);
                }
                jars.addAll(Arrays.asList(BundleInstaller.getDiscoveredBundleFiles()));
                addImagesFromBundles(jars, resourceList);

                String[] resources = resourceList.toArray(new String[resourceList.size()]);
                Arrays.sort(resources, Language.getInterface().getCollator());
                HashMap<String, DefaultMutableTreeNode> folders = new HashMap<>();

                DefaultMutableTreeNode root = new DefaultMutableTreeNode();
                DefaultMutableTreeNode parent;
                for (String s : resources) {
                    int i = s.lastIndexOf('/');
                    String folder = "";
                    String file = s;
                    if (i >= 0) {
                        folder = s.substring(0, i);
                        file = s.substring(i + 1);
                    }
                    parent = folders.get(folder);
                    if (parent == null) {
                        parent = new DefaultMutableTreeNode(folder, true);
                        root.add(parent);
                        folders.put(folder, parent);
                    }
                    parent.add(new DefaultMutableTreeNode(file, false));
                }
                treeModel = new DefaultTreeModel(root);
                resourceTree.setModel(treeModel);
            }
            PlatformSupport.getAgnosticOK(true, okBtn, cancelBtn)
                    .setEnabled(StrangeEons.getApplication().getMarkupTarget() != null);
        }
        super.setVisible(visible);
    }

    private DefaultTreeModel treeModel;

    public String getSelectedResource() {
        TreePath sel = resourceTree.getSelectionPath();
        if (sel != null) {
            Object[] path = sel.getPath();
            if (path.length < 3) {
                return null;
            }
            StringBuilder b = new StringBuilder(100);
            b.append(path[path.length - 2]);
            if (b.length() > 0) {
                b.append('/');
            }
            b.append(path[path.length - 1]);
            return b.toString();
        }
        return null;
    }

    private static boolean matchFile(String name) {
        name = name.toLowerCase(Locale.ENGLISH);
        return name.endsWith(".png") || name.endsWith(".jpg")
                || name.endsWith(".jp2") || name.endsWith(".gif");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JMenuItem copyLabelItem;
    private ca.cgjennings.ui.fcpreview.ResourcePreviewer imageViewer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okBtn;
    private javax.swing.JLabel pathLabel;
    private javax.swing.JPopupMenu pathLabelMenu;
    private javax.swing.JTree resourceTree;
    // End of variables declaration//GEN-END:variables

    @Override
    public void handleOKAction(ActionEvent e) {
//		if( StrangeEons.getWindow().canInsertMarkup() ) {
//			String path = getSelectedResource();
//			if( path != null )
//				StrangeEons.getWindow().insertMarkup( "<image \"res://" + path + "\">" );
//		}
        selection = "res://" + getSelectedResource();
        if (isModal()) {
            dispose();
        }
    }

    @Override
    public void handleCancelAction(ActionEvent e) {
        dispose();
    }

    public String showDialog() {
        selection = null;
        setVisible(true);
        return selection;
    }
    private String selection;
}
