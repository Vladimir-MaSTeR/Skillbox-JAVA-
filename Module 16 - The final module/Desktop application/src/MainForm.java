import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel mainPanel;

    private JLabel familyNameLabel;
    private JLabel middleNameLabel;
    private JLabel nameLabel;
    private JLabel FIOLabel;

    private JTextField nameTextField;
    private JTextField familyNameTextField;
    private JTextField middleNameTextField;
    private JTextField FIOTextField;

    private JButton collapseButton;
    private JButton expandButton;


    public MainForm() {

        collapseButton.addActionListener(new Action() {
            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public void putValue(String s, Object o) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {

            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (familyNameTextField.getText().length() == 0 || nameTextField.getText().length() == 0 || middleNameTextField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(mainPanel,
                                         "Ошибка ввода данных! Заполните все поля",
                                             "ОШИБКА",
                                                  JOptionPane.ERROR_MESSAGE);

                } else {
                    String fullName = nameTextField.getText() + " " + familyNameTextField.getText()  + " " + middleNameTextField.getText();

                    familyNameLabel.setVisible(false);
                    familyNameTextField.setVisible(false);
                    nameLabel.setVisible(false);
                    nameTextField.setVisible(false);
                    middleNameLabel.setVisible(false);
                    middleNameTextField.setVisible(false);
                    collapseButton.setVisible(false);

                    FIOLabel.setVisible(true);
                    FIOTextField.setVisible(true);
                    expandButton.setVisible(true);

                    FIOTextField.setText(fullName);
                }

            }
        });

        expandButton.addActionListener(new Action() {
            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public void putValue(String s, Object o) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {

            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String regexFIO = "\\w+\\s\\w+\\s\\w+";

                if (!FIOTextField.getText().matches(regexFIO)) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Ошибка ввода данных! Введите полное Ф.И.О",
                            "ОШИБКА",
                            JOptionPane.ERROR_MESSAGE);

                } else {

                    String[] splitName = FIOTextField.getText().split("\\s");

                    expandButton.setVisible(false);
                    FIOLabel.setVisible(false);
                    FIOTextField.setVisible(false);

                    familyNameLabel.setVisible(true);
                    familyNameTextField.setVisible(true);
                    nameLabel.setVisible(true);
                    nameTextField.setVisible(true);
                    middleNameLabel.setVisible(true);
                    middleNameTextField.setVisible(true);
                    collapseButton.setVisible(true);

                    nameTextField.setText(splitName[0]);
                    familyNameTextField.setText(splitName[1]);
                    middleNameTextField.setText(splitName[2]);
                }

            }
        });
    }

    public JPanel getMainPanel() {
        FIOLabel.setVisible(false);
        FIOTextField.setVisible(false);
        expandButton.setVisible(false);

        return mainPanel;
    }


}
