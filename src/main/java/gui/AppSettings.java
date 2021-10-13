package gui;

import com.formdev.flatlaf.IntelliJTheme;
import launcher.Launcher;

public class AppSettings {
    private static AppSettings instance;

    private boolean isNightMode = false;

    public static AppSettings getInstance(){
        if (instance == null){
            instance = new AppSettings();
        }
        return instance;
    }

    public boolean isNightMode() {
        return isNightMode;
    }

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
        if (isNightMode){
            IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/DarkPurple.theme.json"));
        }else {
            IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/ArcPurple.theme.json"));
        }
    }
}
