package gui;


import tds.video.VideoWeb;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoPlayerWindow extends JFrame {

    private static VideoWeb videoWeb;
    private JPanel contentPane;

    public VideoPlayerWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 760, 650);

        contentPane = (JPanel) getContentPane();
        contentPane.setSize(new Dimension(760, 630)); //20 de barra de título
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Sin título", JLabel.CENTER);
        titulo.setMinimumSize(new Dimension(760, 60));
        titulo.setPreferredSize(new Dimension(760, 60));
        titulo.setMaximumSize(new Dimension(760, 60));
        Font ftit = new Font("Arial", Font.BOLD, 24);
        titulo.setFont(ftit);
        //titulo.setAlignmentX(0.5f); //centrar
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel botones = new JPanel();
        botones.setMinimumSize(new Dimension(760, 60));
        botones.setPreferredSize(new Dimension(760, 60));
        botones.setMaximumSize(new Dimension(760, 60));
        botones.setLayout(new FlowLayout());

        JButton btUno = new JButton("Musica");
        JButton btDos = new JButton("Pelicula");
        JButton btTres = new JButton("Sorpresa");
        JButton btCancelar = new JButton("Cancelar");
        btCancelar.setBackground(Color.RED);
        btCancelar.setForeground(Color.WHITE);

        botones.add(btUno);
        botones.add(btDos);
        botones.add(btTres);
        botones.add(Box.createHorizontalStrut(50));
        botones.add(btCancelar);

        JLabel reprod = new JLabel("reproduciendo:");
        reprod.setAlignmentX(0.5f);
        JLabel miniatura = new JLabel();
        miniatura.setAlignmentX(0.5f);

        JLabel copyright = new JLabel(videoWeb.getVersion());
        copyright.setAlignmentX(0.5f);
        contentPane.add(titulo);
        contentPane.add(videoWeb);
        contentPane.add(botones);
        contentPane.add(reprod);
        contentPane.add(miniatura);
        contentPane.add(copyright);

        btUno.addActionListener(e -> {
            titulo.setText("Rasputin");
            miniatura.setIcon(videoWeb.getThumb("https://www.youtube.com/watch?v=rk7ITikbhs4"));
            videoWeb.playVideo("https://www.youtube.com/watch?v=hnRphfqIvsM");
            validate();
        });
        btDos.addActionListener(e -> {
            titulo.setText("Jacinto Durante Representante");
            miniatura.setIcon(videoWeb.getThumb("https://www.youtube.com/watch?v=EdVMSYomYJY"));
            videoWeb.playVideo("https://www.youtube.com/watch?v=EdVMSYomYJY");
            validate();
        });
        btTres.addActionListener(e -> {
            titulo.setText("Si tu padre sabe de efectos especiales");
            miniatura.setIcon(videoWeb.getThumb("https://www.youtube.com/watch?v=0243Z0YXPpY"));
            videoWeb.playVideo("https://www.youtube.com/watch?v=0243Z0YXPpY");
            validate();
        });
        btCancelar.addActionListener(e -> {
            titulo.setText("Sin título");
            miniatura.setIcon(null);
            videoWeb.cancel();
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                videoWeb = new VideoWeb();
                VideoPlayerWindow frame = new VideoPlayerWindow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

