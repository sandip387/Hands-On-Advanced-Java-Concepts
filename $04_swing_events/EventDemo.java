package $04_swing_events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventDemo extends JFrame implements MouseListener, KeyListener {
    private JTextField numberField1, numberField2, resultField;
    private JLabel statusLabel;

    public EventDemo() {
        setTitle("Mouse and Key Event Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("First Number:"));
        numberField1 = new JTextField();
        add(numberField1);

        add(new JLabel("Second Number:"));
        numberField2 = new JTextField();
        add(numberField2);

        add(new JLabel("Result:"));
        resultField = new JTextField();
        resultField.setEditable(false); // User can't type in the result field
        add(resultField);

        add(new JLabel("Status:"));
        statusLabel = new JLabel("Ready. Enter numbers and use your mouse.");
        add(statusLabel);

        // --- Event Listener Registration ---
        // Registering this class to listen for mouse events on both text fields.
        numberField1.addMouseListener(this);
        numberField2.addMouseListener(this);

        // To listen for key events, the component must be "focusable".
        // We will add the listener to the entire frame.
        this.addKeyListener(this);
        this.setFocusable(true); // Allows the frame itself to receive key presses

        pack();
        setLocationRelativeTo(null);
    }

    // --- Methods from the MouseListener Interface ---

    @Override
    public void mousePressed(MouseEvent e) {
        statusLabel.setText("Mouse button PRESSED. Calculating sum...");
        calculateSum();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        statusLabel.setText("Mouse button RELEASED. Calculating difference...");
        calculateDifference();
    }

    // We are not using the other mouse events, but because we implemented
    // MouseListener, we MUST provide empty implementations for them.
    // This is where a MouseAdapter would be cleaner.
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // --- Methods from the KeyListener Interface ---

    @Override
    public void keyPressed(KeyEvent e) {
        // This method is called when any key is pressed down. We can check which key it
        // was using its "virtual key code".
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            statusLabel.setText("Escape key pressed. Exiting...");
            // A small delay to see the message before closing.
            Timer timer = new Timer(500, (event) -> System.exit(0));
            timer.setRepeats(false);
            timer.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void calculateSum() {
        try {
            double num1 = Double.parseDouble(numberField1.getText());
            double num2 = Double.parseDouble(numberField2.getText());
            resultField.setText(String.valueOf(num1 + num2));

        } catch (NumberFormatException ex) {
            resultField.setText("Error");
            statusLabel.setText("Invalid input. Please enter numbers.");
        }
    }

    private void calculateDifference() {
        try {
            double num1 = Double.parseDouble(numberField1.getText());
            double num2 = Double.parseDouble(numberField2.getText());
            resultField.setText(String.valueOf(num1 - num2));

        } catch (NumberFormatException ex) {
            resultField.setText("Error");
            statusLabel.setText("Invalid input. Please enter numbers.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventDemo().setVisible(true));
    }
}
