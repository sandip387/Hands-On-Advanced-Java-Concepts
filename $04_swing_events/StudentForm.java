package $04_swing_events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// To handle events, our class must implements the appropriate "Listener" interface.
// For button clicks, that interface is ActionListener.
public class StudentForm extends JFrame implements ActionListener {

    // --- Component Declarations ---
    // We declare the components as private fields so they can be accessed by both
    // the constructor (for setup) and the actionPerformed method (for logic).
    private JTextField nameField, ageField;
    private JRadioButton maleButton, femaleButton;
    private JCheckBox sportsBox, musicBox, readingBox;
    private JButton submiButton, clearButton;
    private JTextArea resultArea;

    public StudentForm() {
        setTitle("Student Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1, 10, 10));

        add(createLabeledPanel("Name:", nameField = new JTextField(20)));
        add(createLabeledPanel("Age:", ageField = new JTextField(5)));

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(new JLabel("Gender:"));
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup(); // Ensures only one radio button can be selected
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        add(genderPanel);

        JPanel hobbyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hobbyPanel.add(new JLabel("Hobbies:"));
        sportsBox = new JCheckBox("Sports");
        musicBox = new JCheckBox("Music");
        readingBox = new JCheckBox("Reading");
        hobbyPanel.add(sportsBox);
        hobbyPanel.add(musicBox);
        hobbyPanel.add(readingBox);
        add(hobbyPanel);

        JPanel buttoPanel = new JPanel();
        submiButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        buttoPanel.add(submiButton);
        buttoPanel.add(clearButton);
        add(buttoPanel);

        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);
        // A JScrollPane is essential for text areas in case the text is too long to
        // fit.
        add(new JScrollPane(resultArea));

        // --- Event Listener Registration ---
        // We are telling our buttons that THIS object (the StudentForm instance) is the
        // one that will handle their ActionEvents.
        submiButton.addActionListener(this);
        clearButton.addActionListener(this);

        pack(); // Automatically sizes the window to fit all its components
        setLocationRelativeTo(null);
    }

    // A helper method to quickly create a panel with a label and a component.
    private JPanel createLabeledPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(labelText));
        panel.add(component);
        return panel;
    }

    // actionPerformed method is a single method from the ActionListener interface.
    // It's called automatically whenever a component we're listening to (our
    // buttons) is clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        // We use e.getSource() to figure out which component triggered the event.
        if (e.getSource() == submiButton) {
            // Use a StringBuilder for efficient string concatenation.
            StringBuilder result = new StringBuilder();
            result.append("Name: ").append(nameField.getText()).append("\n");
            result.append("Age: ").append(ageField.getText()).append("\n");

            // Check which radio button is selected.
            if (maleButton.isSelected()) {
                result.append("Gender: Male\n");
            } else if (femaleButton.isSelected()) {
                result.append("Gender: Female\n");
            }

            // Check all selected hobbies.
            result.append("Hobbies: ");
            if (sportsBox.isSelected())
                result.append("Sports ");
            if (musicBox.isSelected())
                result.append("Music ");
            if (readingBox.isSelected())
                result.append("Reading ");

            // Set the final string to the result area.
            resultArea.setText(result.toString());
        } else if (e.getSource() == clearButton) {
            nameField.setText("");
            ageField.setText("");

            // We can't unselect a ButtonGroup, so we have to create a new one to clear it.
            // A simpler way is to just ignore clearing it or use a "clearSelection" method
            // if available. For now, we'll just clear the text.
            sportsBox.setSelected(false);
            musicBox.setSelected(false);
            readingBox.setSelected(false);
            resultArea.setText("");
            nameField.requestFocus(); // A UX touch to move the cursor back to the first field.
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentForm().setVisible(true));
    }
}
