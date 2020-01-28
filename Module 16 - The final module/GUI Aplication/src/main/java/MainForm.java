import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {

    private JPanel mainPanel;
    private JPanel onePanel;
    private JPanel twoPanel;
    private JPanel textPanelOne;
    private JPanel buttonPanelOne;
    private JPanel textPanelTwo;
    private JPanel buttonPanelTwo;

    private JButton collapseButton;
    private JButton expandButton;

    private JLabel nameLabel;
    private JLabel lastLabel;
    private JLabel middleNameLabel;
    private JLabel fullLabel;

    private JTextField nameText;
    private JTextField lastNameText;
    private JTextField middleNameText;
    private JTextField fullText;



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

                if (nameText.getText().length() == 0 || lastNameText.getText().length() == 0 || middleNameText.getText().length() == 0) {
                    JOptionPane.showMessageDialog(onePanel,
                            "Ошибка ввода данных! Заполните все поля",
                            "ОШИБКА",
                            JOptionPane.ERROR_MESSAGE);

                } else {
                    String fullName = nameText.getText() + " " + lastNameText.getText() + " " + middleNameText.getText();

                    onePanel.setVisible(false);
                    twoPanel.setVisible(true);
                    fullText.setText(fullName);

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

                String regexFIO = "(?U)\\w+\\s\\w+\\s\\w+";

                if (!fullText.getText().matches(regexFIO)) {
                    JOptionPane.showMessageDialog(twoPanel,
                            "Ошибка ввода данных! Введите полное Ф.И.О",
                            "ОШИБКА",
                            JOptionPane.ERROR_MESSAGE);

                } else {
                  String[] splitName = fullText.getText().split("\\s");

                  twoPanel.setVisible(false);
                  onePanel.setVisible(true);

                  nameText.setText(splitName[0]);
                  lastNameText.setText(splitName[1]);
                  middleNameText.setText(splitName[2]);
                }
            }
        });

    }

    public JPanel getMainPanel() {
        onePanel.setVisible(true);
        twoPanel.setVisible(false);

        return mainPanel;
    }

}
