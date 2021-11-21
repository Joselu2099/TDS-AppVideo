package gui.Util;

import javax.swing.*;
import java.awt.*;

// Safe wrapper of CardLayout for hotswap panel.
public class SwapLayoutPanelWrapper {
    private JPanel panel;
    private CardLayout cardLayout;

    public SwapLayoutPanelWrapper() {
        panel = new JPanel();
        cardLayout = new CardLayout();
        panel.setLayout(cardLayout);
    }

    public void swap(Component component){
        panel.add(component); // Add new component/panel
        cardLayout.next(panel); // Switch to next
        if (panel.getComponents().length > 1){
            panel.remove(0); // Delete old
        }
    }

    public JPanel getPanel(){
        return panel;
    }
}
