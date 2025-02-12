package ca.cgjennings.apps.arkham.deck.item;

import ca.cgjennings.apps.arkham.ColourDialog;
import java.awt.Color;
import java.awt.Component;
import static resources.Language.string;

/**
 * The style panel for editing filled shape properties.
 *
 * @author Chris Jennings <https://cgjennings.ca/contact>
 * @since 3.0
 */
@SuppressWarnings("serial")
class ShapeStylePanel extends AbstractStylePanel<ShapeStyle> implements ShapeStyle {

    /**
     * Creates new shape style editing panel.
     */
    public ShapeStylePanel() {
        initComponents();
    }

    @Override
    public String getTitle() {
        return string("style-l-title-shape");
    }

    @Override
    public Color getFillColor() {
        return colourLabel.getBackground();
    }

    @Override
    public void setFillColor(Color color) {
        colourLabel.setBackground(color);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colourLabel = new ColourDialog.ColourButton();
        colourButton = new javax.swing.JButton();

        colourLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        colourLabel.setContentAreaFilled(false);
        colourLabel.setPreferredSize(new java.awt.Dimension(24, 24));
        colourLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colourControlActionPerformed(evt);
            }
        });

        colourButton.setText(string("style-li-colour")); // NOI18N
        colourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colourControlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(colourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(colourButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(colourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colourButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void colourControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colourControlActionPerformed
        ColourDialog d = ColourDialog.getSharedDialog();
        d.setSelectedColor(colourLabel.getBackground());
        d.setLocationRelativeTo((Component) evt.getSource());
        d.setVisible(true);
        if (d.getSelectedColor() != null) {
            colourLabel.setBackground(d.getSelectedColor());
            styleChanged();
        }
    }//GEN-LAST:event_colourControlActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton colourButton;
    private javax.swing.JButton colourLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public int getPanelGroup() {
        return 1_000;
    }
}
