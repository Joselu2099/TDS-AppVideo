package gui;

import javax.swing.*;
import java.awt.*;

public class UIUtils {
    public static void makeButtonDefault(JPanel p, JButton b){
        p.getRootPane().setDefaultButton(b);
    }
    public static void makeButtonDefault(JFrame f, JButton b){
        f.getRootPane().setDefaultButton(b);
    }
    public static Color getPanelBackground(){
        return UIManager.getColor("Panel.background");
    }
    public static Color getPanelForeground(){
        return UIManager.getColor("Panel.foreground");
    }
    public static Color getButtonBackground(){
        return UIManager.getColor("Button.background");
    }
    public static Color getButtonForeground(){
        return UIManager.getColor("Button.foreground");
    }
    public static Color getDefaultButtonForeground(){
        return UIManager.getColor("Button.default.foreground");
    }
    public static Color getDefaultButtonBackground(){
        return UIManager.getColor("Button.default.background");
    }
    public static Color getFocusedBorder(){
        return UIManager.getColor("focusedBorderColor");
    }
}
