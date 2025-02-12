package ca.cgjennings.apps.arkham.project;

import ca.cgjennings.apps.arkham.StrangeEons;
import ca.cgjennings.apps.arkham.dialog.ErrorDialog;
import ca.cgjennings.platform.AgnosticDialog;
import ca.cgjennings.platform.PlatformSupport;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import static resources.Language.string;

/**
 * Dialog for the project packaging task action.
 *
 * @author Chris Jennings <https://cgjennings.ca/contact>
 */
@SuppressWarnings("serial")
class PackagingDialog extends javax.swing.JDialog implements AgnosticDialog {

    private boolean currentFormatIsFolder;
    private final Project proj;

    /**
     * Creates new form PackagingDialog
     */
    public PackagingDialog() {
        super(StrangeEons.getWindow(), true);
        initComponents();

        proj = StrangeEons.getWindow().getOpenProject();
        if (proj == null) {
            throw new IllegalStateException("No project is open");
        }
        currentFormatIsFolder = proj.getPackageFile() == null;

        if (currentFormatIsFolder) {
            folderBtn.setSelected(true);
        } else {
            pkgBtn.setSelected(true);
        }

        getRootPane().setDefaultButton(okBtn);
        PlatformSupport.makeAgnosticDialog(this, okBtn, cancelBtn);
        formatBtnAction(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.ButtonGroup formatGroup = new javax.swing.ButtonGroup();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        folderBtn = new javax.swing.JRadioButton();
        pkgBtn = new javax.swing.JRadioButton();
        cancelBtn = new javax.swing.JButton();
        okBtn = new javax.swing.JButton();
        errLabel = new javax.swing.JLabel();
        outLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(string( "pa-l-packaging" )); // NOI18N

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.gray));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/application/prefs-project.png"))); // NOI18N

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD, jLabel2.getFont().getSize()+1));
        jLabel2.setText(string( "pa-l-packaging-info" )); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        formatGroup.add(folderBtn);
        folderBtn.setText(string( "prj-cb-new-pkg-0" )); // NOI18N
        folderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatBtnAction(evt);
            }
        });

        formatGroup.add(pkgBtn);
        pkgBtn.setText(string( "prj-cb-new-pkg-1" )); // NOI18N
        pkgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatBtnAction(evt);
            }
        });

        cancelBtn.setText(string( "cancel" )); // NOI18N

        okBtn.setText(string( "pa-l-packaging-ok" )); // NOI18N

        errLabel.setForeground(java.awt.Color.red);
        errLabel.setText(" ");

        outLabel.setFont(outLabel.getFont().deriveFont(outLabel.getFont().getSize()-1f));
        outLabel.setText(" ");
        outLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pkgBtn)
                            .addComponent(folderBtn)
                            .addComponent(errLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelBtn, okBtn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(folderBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pkgBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(okBtn))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(456, 250));
    }// </editor-fold>//GEN-END:initComponents

	private void formatBtnAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatBtnAction
            JButton change = PlatformSupport.getAgnosticOK(true, okBtn, cancelBtn);
            String outText = " ";
            String errText = " ";
            if (folderBtn.isSelected()) {
                if (currentFormatIsFolder) {
                    change.setEnabled(false);
                } else {
                    change.setEnabled(true);
                    File f = folderFileFromPackage();
                    outText = f.getPath();
                    if (f.exists()) {
                        errText = string("pa-l-packaging-exists");
                    }
                }
            } else {
                if (!currentFormatIsFolder) {
                    change.setEnabled(false);
                } else {
                    change.setEnabled(true);
                    File f = packageFileFromFolder();
                    outText = f.getPath();
                    if (f.exists()) {
                        errText = string("pa-l-packaging-exists");
                    }
                }
            }
            outLabel.setText(outText);
            errLabel.setText(errText);
	}//GEN-LAST:event_formatBtnAction

    private File packageFileFromFolder() {
        return new File(proj.getFile().getParent(), proj.getFile().getName().trim() + ".seproject");
    }

    private File folderFileFromPackage() {
        return new File(proj.getPackageFile().getParent(), proj.getFile().getName());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel errLabel;
    private javax.swing.JRadioButton folderBtn;
    private javax.swing.JButton okBtn;
    private javax.swing.JLabel outLabel;
    private javax.swing.JRadioButton pkgBtn;
    // End of variables declaration//GEN-END:variables

    @Override
    public void handleOKAction(ActionEvent evt) {
        dispose();

        File target, old;
        try {
            if (folderBtn.isSelected()) {
                old = proj.getPackageFile();
                target = folderFileFromPackage();
                if (target.exists()) {
                    ProjectUtilities.deleteAll(target);
                }
                Project.unpackage(old, target.getParentFile());
            } else {
                old = proj.getFile();
                target = packageFileFromFolder();
                if (target.exists()) {
                    ProjectUtilities.deleteAll(target);
                }
                proj.toPackage(target);
            }
        } catch (IOException e) {
            ErrorDialog.displayError(string("pa-l-packaging-err"), e);
            return;
        }
        StrangeEons.getWindow().closeProject();
        if (StrangeEons.getWindow().setOpenProject(target)) {
            ProjectUtilities.deleteAll(old);
        }
    }

    @Override
    public void handleCancelAction(ActionEvent e) {
        dispose();
    }
}
