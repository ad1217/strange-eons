package ca.cgjennings.apps.arkham.dialog;

import ca.cgjennings.apps.arkham.AbstractGameComponentEditor;
import ca.cgjennings.apps.arkham.Length;
import static ca.cgjennings.apps.arkham.Length.CM;
import static ca.cgjennings.apps.arkham.Length.IN;
import ca.cgjennings.apps.arkham.deck.item.CustomTile;
import ca.cgjennings.apps.arkham.deck.item.PageItem.SnapClass;
import ca.cgjennings.apps.arkham.deck.item.PageItem.SnapTarget;
import ca.cgjennings.platform.AgnosticDialog;
import ca.cgjennings.platform.PlatformSupport;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.EnumSet;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import static resources.Language.string;
import resources.StrangeImage;

/**
 * Dialog to choose an image to insert via markup.
 *
 * @author Chris Jennings <https://cgjennings.ca/contact>
 */
@SuppressWarnings("serial")
public final class InsertImageDialog extends javax.swing.JDialog implements AgnosticDialog, java.awt.event.ActionListener, java.awt.event.FocusListener {

    private NumberFormat formatter;
    private boolean tileCreateMode;

    public InsertImageDialog(java.awt.Frame parent, boolean modal) {
        this(parent, modal, false);
    }

    /**
     * Creates new form InsertImageDialog
     */
    public InsertImageDialog(java.awt.Frame parent, boolean modal, boolean tileCreateMode) {
        super(parent, modal);
        initComponents();
        getRootPane().setDefaultButton(okBtn);

        this.tileCreateMode = tileCreateMode;
        if (tileCreateMode) {
            lockAspect.setVisible(false);
            setTitle(string("iid-title-tile"));
            okBtn.setText(string("iid-l-ok-tile"));
            alignmentLabel.setVisible(false);
            alignCombo.setVisible(false);
        } else {
            snapClassLabel.setVisible(false);
            snapClassCombo.setVisible(false);
            infoLabel.setText(" ");
        }

        formatter = NumberFormat.getNumberInstance();
        PlatformSupport.makeAgnosticDialog(this, okBtn, cancelBtn);

        AbstractGameComponentEditor.localizeComboBoxLabels(unitCombo, null);
        AbstractGameComponentEditor.localizeComboBoxLabels(snapClassCombo, null);
        AbstractGameComponentEditor.localizeComboBoxLabels(alignCombo, null);

        acceptBorder = widthField.getBorder();
        units = Length.getDefaultUnit();
        unitCombo.setSelectedIndex(units);
    }

    public void setTile(CustomTile tile) {
        if (!tileCreateMode) {
            throw new IllegalStateException("not in tile editing mode");
        }
        this.tile = tile;

        final String path = tile.getIdentifier();
        fileField.setText(path);
        snapClassCombo.setSelectedIndex(0);

        changeImage();

        SwingUtilities.invokeLater(() -> {
            units = Length.PT;
            uWidth = InsertImageDialog.this.tile.getWidth();
            uHeight = InsertImageDialog.this.tile.getHeight();
            
            convertUnits(unitCombo.getSelectedIndex());
            updateFieldsFromDimensions();
        });
    }
    private CustomTile tile;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileURLGroup = new javax.swing.ButtonGroup();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        okBtn = new javax.swing.JButton();
        infoLabel = new javax.swing.JLabel();
        previewPanel = new javax.swing.JPanel();
        paramPanel = new javax.swing.JPanel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        widthField = new javax.swing.JTextField();
        unitCombo = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        lockAspect = new javax.swing.JCheckBox();
        heightField = new javax.swing.JTextField();
        snapClassLabel = new javax.swing.JLabel();
        snapClassCombo = new javax.swing.JComboBox();
        alignmentLabel = new javax.swing.JLabel();
        alignCombo = new javax.swing.JComboBox();
        fileField = new ca.cgjennings.ui.JFileField();
        imagePanel = new javax.swing.JPanel();
        imageViewer = new ca.cgjennings.apps.arkham.ImageViewer();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(string("iid-title")); // NOI18N

        jLabel2.setText(string("iid-l-instructions")); // NOI18N

        cancelBtn.setText(string("cancel")); // NOI18N

        okBtn.setText(string("iid-l-ok")); // NOI18N

        infoLabel.setFont(infoLabel.getFont().deriveFont(infoLabel.getFont().getSize()-2f));
        infoLabel.setText("        ");

        previewPanel.setLayout(new java.awt.BorderLayout());

        jLabel4.setText(string("iid-l-width-by-height")); // NOI18N

        widthField.setColumns(5);
        widthField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        widthField.setEnabled(false);
        widthField.addFocusListener(this);

        unitCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "iid-cb-unit0", "iid-cb-unit1", "iid-cb-unit2" }));
        unitCombo.addActionListener(this);

        jLabel3.setText("Image Dimensions");

        lockAspect.setSelected(true);
        lockAspect.setText(string("iid-b-lock-aspect")); // NOI18N

        heightField.setColumns(5);
        heightField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        heightField.setEnabled(false);
        heightField.addFocusListener(this);

        snapClassLabel.setText(string("iid-l-snap-class")); // NOI18N

        snapClassCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "iid-cb-snap-nc", "iid-cb-snap0", "iid-cb-snap1", "iid-cb-snap2" }));
        snapClassCombo.addActionListener(this);

        alignmentLabel.setText(string( "iid-alignment" )); // NOI18N

        alignCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "iid-align0", "iid-align1", "iid-align2", "iid-align3", "iid-align4" }));
        alignCombo.setSelectedIndex(2);

        javax.swing.GroupLayout paramPanelLayout = new javax.swing.GroupLayout(paramPanel);
        paramPanel.setLayout(paramPanelLayout);
        paramPanelLayout.setHorizontalGroup(
            paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paramPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(snapClassLabel)
                    .addComponent(alignmentLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paramPanelLayout.createSequentialGroup()
                        .addComponent(widthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unitCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lockAspect)
                    .addComponent(snapClassCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alignCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        paramPanelLayout.setVerticalGroup(
            paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paramPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(widthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lockAspect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(snapClassLabel)
                    .addComponent(snapClassCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(alignmentLabel)
                    .addComponent(alignCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileField.setFileType(ca.cgjennings.ui.JFileField.FileType.PORTRAIT);
        fileField.addActionListener(this);
        fileField.addFocusListener(this);

        imagePanel.setBorder(new javax.swing.border.LineBorder(java.awt.Color.gray, 1, true));
        imagePanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout imageViewerLayout = new javax.swing.GroupLayout(imageViewer);
        imageViewer.setLayout(imageViewerLayout);
        imageViewerLayout.setHorizontalGroup(
            imageViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        imageViewerLayout.setVerticalGroup(
            imageViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        imagePanel.add(imageViewer, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(paramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fileField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 385, Short.MAX_VALUE)
                    .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 385, Short.MAX_VALUE)))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelBtn, okBtn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(infoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cancelBtn)
                            .addComponent(okBtn)))
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 133, Short.MAX_VALUE)
                    .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 134, Short.MAX_VALUE)))
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == unitCombo) {
            InsertImageDialog.this.unitComboActionPerformed(evt);
        }
        else if (evt.getSource() == snapClassCombo) {
            InsertImageDialog.this.snapClassComboActionPerformed(evt);
        }
        else if (evt.getSource() == fileField) {
            InsertImageDialog.this.fileFieldActionPerformed(evt);
        }
    }

    public void focusGained(java.awt.event.FocusEvent evt) {
    }

    public void focusLost(java.awt.event.FocusEvent evt) {
        if (evt.getSource() == widthField) {
            InsertImageDialog.this.widthFieldFocusLost(evt);
        }
        else if (evt.getSource() == heightField) {
            InsertImageDialog.this.heightFieldFocusLost(evt);
        }
        else if (evt.getSource() == fileField) {
            InsertImageDialog.this.fileFieldFocusLost(evt);
        }
    }// </editor-fold>//GEN-END:initComponents
    private void heightFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_heightFieldFocusLost
        double d = getValidatedDimension(heightField);
        if (d > 0d) {
            if (lockAspect.isSelected()) {
                double w = pxWidth / pxHeight * d;
                widthField.setText(formatter.format(w));
            }
            heightField.setText(formatter.format(d));
        }
    }//GEN-LAST:event_heightFieldFocusLost

    private void widthFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_widthFieldFocusLost
        double d = getValidatedDimension(widthField);
        if (d > 0d) {
            if (lockAspect.isSelected()) {
                double h = pxHeight / pxWidth * d;
                heightField.setText(formatter.format(h));
            }
            widthField.setText(formatter.format(d));
        }
    }//GEN-LAST:event_widthFieldFocusLost

    private double getValidatedDimension(JTextField source) {
        if (acceptBorder == null) {
            return 1d;
        } // still initializing components

        String text = source.getText();
        double value = -1d;
        try {
            value = formatter.parse(text).doubleValue();
            if (value <= 0d) {
                value = -1d;
            }
        } catch (ParseException e) {
        }
        source.setBorder(value == -1d ? rejectBorder : acceptBorder);
        return value;
    }
    private Border acceptBorder;
    private Border rejectBorder = BorderFactory.createLineBorder(Color.RED, 2);

    private void unitComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitComboActionPerformed
        if (unitCombo.getSelectedIndex() >= 0) {
            updateDimensionsFromFields();
            convertUnits(unitCombo.getSelectedIndex());
            updateFieldsFromDimensions();
        }
    }//GEN-LAST:event_unitComboActionPerformed

    private void updateDimensionsFromFields() {
        double w = getValidatedDimension(widthField);
        double h = getValidatedDimension(heightField);
        if (w > 0d) {
            uWidth = w;
            uHeight = h;
        }
    }

    private String getFormattedUnit(double value) {
        return String.format(Locale.US, "%.2f%s", value, (units == IN) ? "in" : (units == CM) ? "cm" : "pt");
    }

private void snapClassComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snapClassComboActionPerformed
    int i = snapClassCombo.getSelectedIndex();
    if (i >= 1) {
        infoLabel.setText(string("iid-snap-desc" + (i - 1)));
    } else {
        infoLabel.setText("    ");
    }
}//GEN-LAST:event_snapClassComboActionPerformed

private void fileFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fileFieldFocusLost
    // reload the file only if the path has changed since the last load
    // this is distinct from actively loading the file with Enter (firing an action event)
    // this is intended to help when entering file URLs
    if (!getImagePath().equals(mostRecentlyLoadedPath)) {
        changeImage();
    }
}//GEN-LAST:event_fileFieldFocusLost

private void fileFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileFieldActionPerformed
    changeImage();
}//GEN-LAST:event_fileFieldActionPerformed

    private String getImagePath() {
        return fileField.getText();
    }

    private BufferedImage getCurrentImage() {
        String path = getImagePath();
        mostRecentlyLoadedPath = path;
        if (path.length() == 0 && tileCreateMode) {
            path = CustomTile.PLACEHOLDER_IDENTIFIER;
        }
        return StrangeImage.get(path).asBufferedImage();
    }
    private String mostRecentlyLoadedPath = "";

    private void changeImage() {
        Component glass = getGlassPane();
        try {
            glass.setVisible(true);
            glass.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            BufferedImage im = getCurrentImage();

            pxWidth = im.getWidth(null);
            pxHeight = im.getHeight(null);
            initSize();
            imageViewer.setImage(im);
        } finally {
            glass.setCursor(Cursor.getDefaultCursor());
            glass.setVisible(false);
        }
        okBtn.setEnabled(true);
        widthField.setEnabled(true);
        heightField.setEnabled(true);
    }

    private void initSize() {
        uWidth = pxWidth / 150;
        uHeight = pxHeight / 150;
        int trueUnits = units;
        units = IN;
        convertUnits(trueUnits);
        updateFieldsFromDimensions();
    }

    /**
     * Convert the current image dimensions and units into another unit.
     */
    private void convertUnits(int newUnits) {
        if (newUnits != units) {
            uWidth = Length.convert(uWidth, units, newUnits);
            uHeight = Length.convert(uHeight, units, newUnits);
            units = newUnits;
//            // convert current unit to inches
//            float conversion = 1f / getConversionFactor( units );
//            uWidth *= conversion;
//            uHeight *= conversion;
//
//            // convert inches to new unit
//            if( newUnits != UNIT_IN ) {
//                conversion = getConversionFactor( newUnits );
//                uWidth *= conversion;
//                uHeight *= conversion;
//            }
//            units = newUnits;
        }
    }

    private void updateFieldsFromDimensions() {
        widthField.setText(formatter.format(uWidth));
        heightField.setText(formatter.format(uHeight));
    }

    private double pxWidth, pxHeight;
    private double uWidth = 1d, uHeight = 1d;
    private int units = 0;
    private String markup;

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            markup = null;
        }
        super.setVisible(visible);
    }

    /**
     * Returns the markup that will insert the selected image, or
     * {@code null} if the operation was cancelled.
     *
     * @return markup for the selected image, or {@code null}
     */
    public String getMarkupString() {
        return markup;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox alignCombo;
    private javax.swing.JLabel alignmentLabel;
    private javax.swing.JButton cancelBtn;
    private ca.cgjennings.ui.JFileField fileField;
    private javax.swing.ButtonGroup fileURLGroup;
    private javax.swing.JTextField heightField;
    private javax.swing.JPanel imagePanel;
    private ca.cgjennings.apps.arkham.ImageViewer imageViewer;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JCheckBox lockAspect;
    private javax.swing.JButton okBtn;
    private javax.swing.JPanel paramPanel;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JComboBox snapClassCombo;
    private javax.swing.JLabel snapClassLabel;
    private javax.swing.JComboBox unitCombo;
    private javax.swing.JTextField widthField;
    // End of variables declaration//GEN-END:variables
    private boolean okPressed;

    public boolean showDialog() {
        okPressed = false;
        setVisible(true);
        return okPressed;
    }

    @Override
    public void handleOKAction(ActionEvent e) {
        double w = getValidatedDimension(widthField);
        double h = getValidatedDimension(heightField);
        if (w <= 0d || h <= 0d) {
            (w <= 0d ? widthField : heightField).requestFocusInWindow();
            Toolkit.getDefaultToolkit().beep();
            return;
        }

        String url = getImagePath();
        if (tileCreateMode) {
            tile.setIdentifier(url);
            if (url == null || url.isEmpty()) {
                url = CustomTile.PLACEHOLDER_IDENTIFIER;
            }
            BufferedImage im = StrangeImage.get(url).asBufferedImage();
            updateDimensionsFromFields();
            convertUnits(IN);
            double dpi = im.getWidth() / uWidth;
            tile.setResolution(dpi);

            SnapClass current = tile.getSnapClass();
            switch (snapClassCombo.getSelectedIndex()) {
                case 0: // do not change
                    break;
                case 1: // tile
                    tile.setSnapClass(SnapClass.SNAP_TILE);
                    tile.setClassesSnappedTo(EnumSet.of(SnapClass.SNAP_TILE, SnapClass.SNAP_PAGE_GRID));
                    break;
                case 2: // overlay
                    tile.setSnapClass(SnapClass.SNAP_OVERLAY);
                    tile.setClassesSnappedTo(SnapClass.SNAP_SET_NONE);
                    break;
                case 3: // inlay
                    tile.setSnapClass(SnapClass.SNAP_INLAY);
                    tile.setClassesSnappedTo(EnumSet.of(SnapClass.SNAP_TILE, SnapClass.SNAP_INLAY));
                    tile.setSnapTarget(SnapTarget.TARGET_MIXED);
                    break;
                default:
                    throw new AssertionError("unknown snap setting");
            }
        } else {
            markup = "<image \"";
            markup += url;
            markup += "\"";
            markup += " " + getFormattedUnit(w);
            if (!lockAspect.isSelected()) {
                markup += " " + getFormattedUnit(h);
            }
            int align = alignCombo.getSelectedIndex();
            switch (align) {
                case 0:
                    markup += " top";
                    break;
                case 1:
                    markup += " center";
                    break;
                // case 2: markup += " baseline"; break;
                case 3:
                    markup += " hanging";
                    break;
                case 4:
                    markup += " bottom";
                    break;
            }
            markup += ">";
        }

        Length.setDefaultUnit(unitCombo.getSelectedIndex());

        okPressed = true;
        dispose();
    }

    @Override
    public void handleCancelAction(ActionEvent e) {
        Length.setDefaultUnit(unitCombo.getSelectedIndex());
        dispose();
    }
}
