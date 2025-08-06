package $04_swing_events;

import javax.swing.*;
import java.awt.event.*;

public class MenuDemo extends JFrame implements ActionListener {

    private JTextArea textArea;

    public MenuDemo() {
        setTitle("Menu Demo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // The JMenuBar is the container that holds all the menus.
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        // Mnemonic: A keyboard shortcut to open the menu itself (Alt + F).
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem newItem = new JMenuItem("New");
        // Accelerator: A global keyboard shortcut that works even if the menu isn't
        // open
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newItem.addActionListener(this); // Register listener

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(this);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X); // Mnemonic for the item itself (press X when menu is open)
        exitItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator(); // Adds a visual separating line in the menu
        fileMenu.add(exitItem);

        // Add the Menu to the Menu Bar
        menuBar.add(fileMenu);

        // Add the Menu Bar for the Frame
        setJMenuBar(menuBar);

        textArea = new JTextArea();
        add(new JScrollPane(textArea));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // We can get the text of the item that was clicked.
        String command = e.getActionCommand();

        switch (command) {
            case "New":
                textArea.setText("");
                break;

            case "Save":
                // As this is just a demo, we will show simple message dialog. However, this
                // would open a file dialog in a real app.
                JOptionPane.showMessageDialog(this, "File saved successfully!", "Save",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Exit":
                int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm Exit",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;

            default:
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuDemo().setVisible(true));
    }
}
