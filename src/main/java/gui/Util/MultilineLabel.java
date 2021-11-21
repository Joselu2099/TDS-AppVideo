package gui.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MultilineLabel extends JTextArea {
    private static final long serialVersionUID = 1L;
    public MultilineLabel(String text){
        super(text);
        setEditable(false);
        setCursor(null);
        setOpaque(false);
        setFocusable(false);
        setFont(UIManager.getFont("Label.font"));
        setWrapStyleWord(true);
        setLineWrap(true);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setAlignmentY(JLabel.CENTER_ALIGNMENT);
    }
}
