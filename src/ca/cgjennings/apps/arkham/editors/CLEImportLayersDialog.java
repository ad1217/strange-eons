package ca.cgjennings.apps.arkham.editors;

import ca.cgjennings.platform.AgnosticDialog;
import ca.cgjennings.platform.PlatformSupport;
import ca.cgjennings.ui.DocumentEventAdapter;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import static resources.Language.string;

/**
 * Import PSD layers into a card editor layout.
 *
 * @author Chris Jennings <https://cgjennings.ca/contact>
 */
@SuppressWarnings("serial")
class CLEImportLayersDialog extends javax.swing.JDialog implements AgnosticDialog {

    /**
     * Creates new form CLEImportLayersDialog
     */
    public CLEImportLayersDialog(java.awt.Frame parent, CardLayoutEditor.InternalImage[] folders) {
        super(parent, true);
        initComponents();
        getRootPane().setDefaultButton(okBtn);
        PlatformSupport.makeAgnosticDialog(this, okBtn, cancelBtn);

        DocumentEventAdapter li = new DocumentEventAdapter() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateOKBtn();
            }
        };
        fileField.getDocument().addDocumentListener(li);

        destCombo.setModel(new DefaultComboBoxModel(folders));
    }

    private void updateOKBtn() {
        JButton ok = PlatformSupport.getAgnosticOK(true, okBtn, cancelBtn);
        boolean enable = true;

        if (!new File(fileField.getText()).exists()) {
            enable = false;
        }

        okBtn.setEnabled(enable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        destCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        deleteExistingCheck = new javax.swing.JCheckBox();
        cancelBtn = new javax.swing.JButton();
        okBtn = new javax.swing.JButton();
        fileField = new ca.cgjennings.ui.JFileField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(string( "cle-import-title" )); // NOI18N

        jLabel1.setText(string( "cle-is-import-folder" )); // NOI18N

        destCombo.setMaximumRowCount(12);

        jLabel2.setText(string( "cle-is-file" )); // NOI18N

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getSize()-1f));
        jLabel3.setText(string( "cle-import-info" )); // NOI18N

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getSize()-1f));
        jLabel4.setText(string( "cle-import-info2" )); // NOI18N

        deleteExistingCheck.setText(string( "cle-import-replace" )); // NOI18N

        cancelBtn.setText(string( "cancel" )); // NOI18N

        okBtn.setText(string( "cle-import-ok" )); // NOI18N

        fileField.setFileType(ca.cgjennings.ui.JFileField.FileType.GENERIC);
        fileField.setGenericFileTypeDescription(string("cle-import-fc-desc")); // NOI18N
        fileField.setGenericFileTypeExtensions(new String[] {"psd"});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteExistingCheck)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileField, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                            .addComponent(destCombo, 0, 322, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(okBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelBtn, okBtn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(destCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(deleteExistingCheck)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(okBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public File showDialog() {
        selectedFile = null;
        setVisible(true);
        return selectedFile;
    }
    private File selectedFile;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JCheckBox deleteExistingCheck;
    private javax.swing.JComboBox destCombo;
    private ca.cgjennings.ui.JFileField fileField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton okBtn;
    // End of variables declaration//GEN-END:variables

    @Override
    public void handleOKAction(ActionEvent e) {
        File f = new File(fileField.getText());
        if (f.exists()) {
            selectedFile = f;
            dispose();
        }
    }

    @Override
    public void handleCancelAction(ActionEvent e) {
        dispose();
    }

    public boolean isReplaceSelected() {
        return deleteExistingCheck.isSelected();
    }

    public CardLayoutEditor.InternalImage getDestination() {
        if (destCombo.getSelectedItem() == null) {
            destCombo.setSelectedIndex(0);
        }
        return (CardLayoutEditor.InternalImage) destCombo.getSelectedItem();
    }
}
