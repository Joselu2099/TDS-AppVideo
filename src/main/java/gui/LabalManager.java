package gui;

import com.formdev.flatlaf.IntelliJTheme;
import gui.Util.SwapLayoutPanelWrapper;
import launcher.Launcher;
import model.Label;
import model.Video;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

public class LabalManager extends JPanel {
    Video video;
    private static final JComboBox<Label> comboBox = new JComboBox<>(Label.values());
    Consumer<Label> addCallback;
    Consumer<Label> deleteCallback;

    SwapLayoutPanelWrapper swapLayoutPanelWrapper = new SwapLayoutPanelWrapper();

    public JButton tagButtonFactory(String labelName){
        JButton btn = new JButton(labelName);
        btn.addActionListener(b->{
            Label l = Label.valueOf(((JButton)b.getSource()).getText());
            Arrays.stream(getComponents()).filter(v -> v instanceof JButton && ((JButton) v)
                    .getText().equals(l.name())).findAny().ifPresent(this::remove);
            if (deleteCallback != null && l != null){
                deleteCallback.accept(l);
                swapLayoutPanelWrapper.swap(getTagPanel(video.getLabels()));
            }
        });
        return btn;
    };

    private JPanel getTagPanel(Set<Label> labelSet){
        JPanel tagPanel = new JPanel();

        tagPanel.setLayout(new FlowLayout());
        video.getLabels().stream().forEach(label -> tagPanel.add(tagButtonFactory(label.name())));
        JButton addBtn = new JButton("+");
        addBtn.addActionListener(l->showAddDialog());
        tagPanel.add(addBtn);
        return tagPanel;
    }

    public LabalManager(Video video,Consumer<Label> addCallback,Consumer<Label> deleteCallback) {
        this.video = video;
        this.addCallback = addCallback;
        this.deleteCallback = deleteCallback;
        comboBox.setEditable(true);

        // TAGS
        swapLayoutPanelWrapper.swap(getTagPanel(video.getLabels()));
        add(swapLayoutPanelWrapper.getPanel() );
    }

    private void showAddDialog() {
//        JOptionPane.showMessageDialog(null, comboBox, "Seleccióna la etiqueta que quieres añadir:",
//                JOptionPane.QUESTION_MESSAGE);
        Label l = (Label)JOptionPane.showInputDialog(
                this,
                "Seleccióna la etiqueta que quieres añadir:",
                "Añadir etiqueta",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Label.values(),
                null);
//        System.out.println(Label.values()[comboBox.getSelectedIndex()]);
        System.out.println(l);
        if (addCallback != null && l != null){
            addCallback.accept(l);
            swapLayoutPanelWrapper.swap(getTagPanel(video.getLabels()));
            this.setVisible(true);
        }
//            callback.accept(Label.values()[comboBox.getSelectedIndex()]);
    }

    public static void main(String[] args){
        IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/ArcPurple.theme.json"));
//        IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/DarkPurple.theme.json"));
        EventQueue.invokeLater(() -> {
            try {
                Video v = new Video("https://www.youtube.com/watch?v=XKfgdkcIUxw");
                v.addLabels(Label.INFANTIL);
                v.addLabels(Label.VIDEOCLIP);
                JFrame f = new JFrame();
                LabalManager manager = new LabalManager(v,v::addLabels,v::removeLabels);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setContentPane(manager);
                f.setBounds(0, 0, 800, 600);
                f.setVisible(true);
                manager.showAddDialog();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
