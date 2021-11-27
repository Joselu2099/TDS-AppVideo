package launcher;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import gui.LoginWindow;
import model.VideoRepository;

import javax.swing.*;
import java.awt.*;

public class Launcher {
    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/ArcPurple.theme.json"));
//				IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/DarkPurple.theme.json"));

                VideoRepository.getInstance();


                LoginWindow window = new LoginWindow();
                window.showWindow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
