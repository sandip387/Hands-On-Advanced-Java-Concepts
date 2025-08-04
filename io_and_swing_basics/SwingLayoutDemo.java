package io_and_swing_basics;

import javax.swing.*;
import java.awt.*;

// Having your main application class extend JFrame is a common practice to mean your class IS-A window.
public class SwingLayoutDemo extends JFrame {

    // The constructor is the perfect place to set up all the UI components.
    public SwingLayoutDemo() {
        // --- Basic Window Setup ---
        setTitle("Layout Manager Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // IMPORTANT: This makes the program exit when you close the
                                                        // window
        setLocationRelativeTo(null); // Helper to center the window on the screen when it first opens.

        // --- Layout Management ---
        // BorderLayout divides the window into 5 regions: NORTH(up), SOUTH(down),
        // EAST(right), WEST(left), CENTER
        setLayout(new BorderLayout(10, 10)); // The numbers add 10px gaps between regions

        // --- Adding Components to the Regions ---
        // SwingConstants.CENTER tells the label to center its text.
        add(new JLabel("North Region", SwingConstants.CENTER), BorderLayout.NORTH);
        add(new JLabel("South Region", SwingConstants.CENTER), BorderLayout.SOUTH);
        add(new JLabel("East Region", SwingConstants.CENTER), BorderLayout.EAST);
        add(new JLabel("West Region", SwingConstants.CENTER), BorderLayout.WEST);

        // --- Nested Panels ---
        // You can only add ONE component directly to each BorderLayout region.
        // JPanel are used to put multiple components in one region.
        JPanel centerPanel = new JPanel();

        // FlowLayout is default for JPanel and it places components one after another
        // in a row, left-to-right.
        centerPanel.setLayout(new FlowLayout());

        centerPanel.add(new JButton("Button 1"));
        centerPanel.add(new JButton("Button 2"));
        centerPanel.add(new JButton("Button 3"));

        add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // IMPORTANT: All Swing applications should be started this way.
        // This ensures that the UI is created and updated on a special thread called
        // the Event Dispatch Thread (EDT), preventing potential bugs.
        SwingUtilities.invokeLater(() -> {
            SwingLayoutDemo frame = new SwingLayoutDemo();
            frame.setVisible(true);
        });

        // SwingUtilities.invokeLater(new Runnable() {
        //     @Override
        //     public void run() {
        //         SwingLayoutDemo frome = new SwingLayoutDemo();
        //         frame.setVisible(ture);
        //     }
        // });
    }
}
