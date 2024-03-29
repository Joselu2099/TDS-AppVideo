package gui;

import com.formdev.flatlaf.IntelliJTheme;
import controller.AppVideo;
import gui.Util.SwapLayout;
import gui.Util.WrapLayout;
import launcher.Launcher;
import model.Label;
import model.Video;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

public class LabelEditorPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Consumer<Label> addCallback;
    Consumer<Label> deleteCallback;


//    SwapLayoutPanelWrapper swapLayoutPanelWrapper = new SwapLayoutPanelWrapper();

    SwapLayout swapLayout;

    Set<Label> labels;

    public LabelEditorPanel(Set<Label> labels, Consumer<Label> addCallback, Consumer<Label> deleteCallback) {
        swapLayout = new SwapLayout(this);
        this.setLayout(swapLayout);
        this.labels = new TreeSet<>( labels);
        this.addCallback = addCallback;
        this.deleteCallback = deleteCallback;

        // TAGS
        this.add(getTagPanel(labels));
//        add(swapLayoutPanelWrapper.getPanel() );
    }

    public JButton tagButtonFactory(String labelName){
        JButton btn = new JButton(labelName);
        btn.addActionListener(b->{
            Label l = Label.valueOf(((JButton)b.getSource()).getText());
            Arrays.stream(getComponents()).filter(v -> v instanceof JButton && ((JButton) v)
                    .getText().equals(l.name())).findAny().ifPresent(this::remove);
            if (deleteCallback != null && l != null){
                labels.remove(l);
                deleteCallback.accept(l);
                add(getTagPanel(labels));
//                swapLayoutPanelWrapper.swap(getTagPanel(labels));
            }
        });
        return btn;
    }

    private JPanel getTagPanel(Set<Label> labelSet){
        JPanel tagPanel = new JPanel();

        tagPanel.setLayout(new WrapLayout());
        labelSet.forEach(label -> tagPanel.add(tagButtonFactory(label.name())));
        JButton addBtn = new JButton("Añadir etiqueta");
        addBtn.setIcon(new ImageIcon(LabelEditorPanel.class.getResource("/images/icons8-filter-24.png")));
        addBtn.addActionListener(l->showAddDialog());
        tagPanel.add(addBtn);
        return tagPanel;
    }


    private void showAddDialog() {
        //JOptionPane.showMessageDialog(null, comboBox, "Seleccióna la etiqueta que quieres añadir:",
        //JOptionPane.QUESTION_MESSAGE);
        JComboBox<String> comboBox = new JComboBox<>(AppVideo.getInstance().getExistingLabelSet().stream().map(Label::name).toArray(String[]::new));
        comboBox.setEditable(true);
        SwingUtilities.invokeLater(()->{
            comboBox.getEditor().selectAll();
            comboBox.requestFocusInWindow();
        });

        String[] options = {  "Ok","Cancel" };
        int status = JOptionPane.showOptionDialog(this,comboBox,"Selecciona etiqueta: ",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("/images/multimediavideoplayer_128px.png"),options,options[0]);
//                JOptionPane.showMessageDialog(this, comboBox, "Seleccióna etiqueta:",
//
//                JOptionPane.QUESTION_MESSAGE, new ImageIcon("/images/multimediavideoplayer_128px.png"));

//        JOptionPane.showMessageDialog(this, comboBox, "Seleccióna etiqueta:",
//                JOptionPane.QUESTION_MESSAGE, new ImageIcon("/images/multimediavideoplayer_128px.png"));
//        String input = (String)JOptionPane.showInputDialog(
//                this,
//                "Seleccióna la etiqueta que quieres añadir:",
//                "Añadir etiqueta",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                AppVideo.getInstance().getExistingLabelSet().stream().map(Label::name).toArray(),
//                "tag");
        Label l = Label.valueOf((String) comboBox.getSelectedItem());
        if (addCallback != null && l != null && status == JOptionPane.OK_OPTION){
            labels.add(l);
            addCallback.accept(l);
//            swapLayoutPanelWrapper.swap(getTagPanel(labels));
            add(getTagPanel(labels));
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
                Set<Label> labelSet = v.getLabels();
                LabelEditorPanel manager = new LabelEditorPanel(labelSet,
                        l->{v.addLabels(l);labelSet.add(l);},
                        l->{v.removeLabels(l);labelSet.remove(l);});
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
