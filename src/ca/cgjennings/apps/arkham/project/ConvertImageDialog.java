package ca.cgjennings.apps.arkham.project;

import ca.cgjennings.imageio.IIOWritePanel;
import ca.cgjennings.platform.AgnosticDialog;
import ca.cgjennings.platform.PlatformSupport;
import ca.cgjennings.ui.fcpreview.ResourcePreviewer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.swing.Timer;
import static resources.Language.string;
import resources.Settings;

/**
 * The dialog used by the convert image action to display image conversion
 * options. Also shows a preview of the output if there is only one selected
 * file.
 *
 * @author Chris Jennings <https://cgjennings.ca/contact>
 */
@SuppressWarnings("serial")
class ConvertImageDialog extends javax.swing.JDialog implements AgnosticDialog {

    private final String format;

    /**
     * Creates new form ConvertImageDialog
     */
    public ConvertImageDialog(java.awt.Frame parent, String format) {
        super(parent, true);
        this.format = format;
        initComponents();
        getRootPane().setDefaultButton(okBtn);
        setReplaceable(false);
        setPreviewOptions(false, null, null, null);
        param.addPropertyChangeListener(IIOWritePanel.PARAMETERS_PROPERTY, (PropertyChangeEvent evt) -> {
            previewIsOutOfDate = true;
        });
        Timer update = new Timer(500, (ActionEvent e) -> {
            if (previewIsOutOfDate && previewWriter != null) {
                getGlassPane().setVisible(true);
                try {
                    previewer.showPreview("");
                } finally {
                    getGlassPane().setVisible(false);
                    previewIsOutOfDate = false;
                }
            }
        });
        update.setRepeats(true);
        update.start();

        PlatformSupport.makeAgnosticDialog(this, okBtn, cancelBtn);
    }

    public void setImageWriteParam(ImageWriteParam iwp) {
        param.setImageWriteParam(iwp);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        param = new ca.cgjennings.imageio.IIOWritePanel();
        cancelBtn = new javax.swing.JButton();
        okBtn = new javax.swing.JButton();
        replaceCheck = new javax.swing.JCheckBox();
        previewer =  new CompressPreviewer() ;
        outsizeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(string( "pa-ci-title" ) + " (" + format.toUpperCase( Locale.CANADA ) + ")" );

        param.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cancelBtn.setText(string( "cancel" )); // NOI18N

        okBtn.setText(string( "pa-ci-ok" )); // NOI18N

        replaceCheck.setText(string( "pa-ci-replace" )); // NOI18N
        replaceCheck.setEnabled(false);

        previewer.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        outsizeLabel.setFont(outsizeLabel.getFont().deriveFont(outsizeLabel.getFont().getSize()-1f));
        outsizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outsizeLabel.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(outsizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(replaceCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                        .addComponent(okBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(previewer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                            .addComponent(param, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(param, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(previewer, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(outsizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(okBtn)
                    .addComponent(replaceCheck))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton okBtn;
    private javax.swing.JLabel outsizeLabel;
    private ca.cgjennings.imageio.IIOWritePanel param;
    private ca.cgjennings.ui.fcpreview.ResourcePreviewer previewer;
    private javax.swing.JCheckBox replaceCheck;
    // End of variables declaration//GEN-END:variables

    public IIOWritePanel getControlPanel() {
        return param;
    }

    @Override
    public void handleOKAction(ActionEvent e) {
        ok = true;
        dispose();
    }

    @Override
    public void handleCancelAction(ActionEvent e) {
        dispose();
    }

    public boolean showDialog() {
        ok = false;

        setVisible(true);

        if (ok && canReplace) {
            Settings.getUser().set(ConvertImage.KEY_REPLACE_ORIGINAL, replaceCheck.isSelected() ? "yes" : "no");
        }

        return ok;
    }

    private boolean ok;

    public void setReplaceable(boolean canReplaceFile) {
        canReplace = canReplaceFile;
        if (canReplace) {
            replaceCheck.setEnabled(true);
            replaceCheck.setSelected(Settings.getUser().getYesNo(ConvertImage.KEY_REPLACE_ORIGINAL));
        } else {
            replaceCheck.setEnabled(false);
            replaceCheck.setSelected(false);
        }
    }
    private boolean canReplace;

    public void setPreviewOptions(boolean show, String ext, File source, ImageWriter w) {
        if (!show) {
            previewer.setVisible(false);
            outsizeLabel.setVisible(false);
            previewType = null;
            previewSource = null;
            previewWriter = null;
        } else {
            previewer.setVisible(true);
            outsizeLabel.setVisible(true);
            previewType = ext;
            previewSource = source;
            previewWriter = w;
            previewFormatter = NumberFormat.getPercentInstance();
            previewFormatter.setMinimumFractionDigits(1);
            previewFormatter.setMaximumFractionDigits(1);
            try {
                previewer.setPreviewImage(ImageIO.read(source));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private File previewSource;
    private String previewType;
    private ImageWriter previewWriter;
    private boolean previewIsOutOfDate = false;
    private NumberFormat previewFormatter;

    private String getErrorMessage(Throwable t, File f) {
        if (t instanceof IllegalArgumentException) {
            return string("pa-ci-err-bitrate");
        }
        return string("prj-err-convert", f.getName());
    }

    private class CompressPreviewer extends ResourcePreviewer {

        public CompressPreviewer() {
            setZoomable(true);
            setLoadProgressDelay(333);
            viewer.setScaleResetAutomatically(false);
        }

        @Override
        protected BufferedImage createPreviewImage(Object f) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageWriteParam iwp = param.getImageWriteParam();
            try {
                ConvertImage.Converter.convert(previewSource, previewType, out, previewWriter, iwp);
                byte[] data = out.toByteArray();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                BufferedImage comp = ImageIO.read(in);
                if (comp != null) {
                    final long size = data.length;
                    EventQueue.invokeLater(() -> {
                        long oldSize = previewSource.length();
                        double delta = size / (double) oldSize;
                        String deltaString = previewFormatter.format(delta);
                        if (delta > 1d) {
                            deltaString = "<span style='color:#990000'><b>" + deltaString + "</b></span>";
                        }
                        outsizeLabel.setText(string("pa-ci-newsize",
                                ProjectUtilities.formatByteSize(oldSize), previewType.toUpperCase(Locale.CANADA),
                                ProjectUtilities.formatByteSize(size), format.toUpperCase(Locale.CANADA),
                                deltaString
                        ));
                    });
                }
                return comp;
            } catch (IllegalArgumentException e) {
                EventQueue.invokeLater(() -> {
                    outsizeLabel.setText("<html><font color=red>" + string("pa-ci-err-bitrate"));
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
