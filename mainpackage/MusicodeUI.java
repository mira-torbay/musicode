package mainpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.LineUnavailableException;

public class MusicodeUI extends JFrame {
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton encodeButton;
    private JButton decodeButton;
    private JButton playButton;
    private JComboBox<String> keySelector;
    private Key key;

    public MusicodeUI() {
        setTitle("Musicode Encoder/Decoder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Key selection
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Key:"));
        String[] keys = {"Cmaj", "Am", "Fmaj", "Dm", "Bbmaj", "Gm", "Ebmaj", "Cm", "Abmaj", "Fm", "Dbmaj", "Bbm", "Gbmaj", "Ebm", "Abm"};
        keySelector = new JComboBox<>(keys);
        keySelector.setSelectedIndex(0);
        key = new Key((String) keySelector.getSelectedItem());
        keySelector.addActionListener(e -> key = new Key((String) keySelector.getSelectedItem()));
        topPanel.add(keySelector);
        add(topPanel, BorderLayout.NORTH);

        // Input and output areas
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        inputArea = new JTextArea(5, 40);
        inputArea.setBorder(BorderFactory.createTitledBorder("Input (Plaintext or Notes)"));
        outputArea = new JTextArea(5, 40);
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
        outputArea.setEditable(false);
        centerPanel.add(new JScrollPane(inputArea));
        centerPanel.add(new JScrollPane(outputArea));
        add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        encodeButton = new JButton("Encode");
        decodeButton = new JButton("Decode");
        playButton = new JButton("Play");
        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);
        buttonPanel.add(playButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputArea.getText();
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(MusicodeUI.this, "Input is empty.");
                    return;
                }
                String tones = key.encode(input);
                outputArea.setText(tones);
            }
        });

        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputArea.getText();
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(MusicodeUI.this, "Input is empty.");
                    return;
                }
                String message = key.decode(input);
                outputArea.setText(message);
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tones = outputArea.getText();
                if (tones.isEmpty()) {
                    JOptionPane.showMessageDialog(MusicodeUI.this, "Output is empty. Encode or decode first.");
                    return;
                }
                try {
                    Tone.initAudioLine();
                    key.playTones(tones);
                    Tone.closeAudioLine();
                } catch (LineUnavailableException ex) {
                    JOptionPane.showMessageDialog(MusicodeUI.this, "Audio line unavailable: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MusicodeUI().setVisible(true);
        });
    }
} 