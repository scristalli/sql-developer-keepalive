package keepalive;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class PollIntervalDialog extends JDialog {
    @SuppressWarnings("compatibility:-4837128555563361740")
    private static final long serialVersionUID = 931087028121895189L;

    private JTextField textField;
    private JOptionPane optionPane;
    private Integer pollInterval;

    // minimal value for poll interval in seconds
    private final Integer minPollInterval = 60;

    // maximal value for poll interval in seconds
    private final Integer maxPollInterval = 43200; //12h

    private static final String OK_STRING = "OK";
    private static final String CANCEL_STRING = "Cancel";

    public PollIntervalDialog(JFrame parent) {
        super(parent, true);
        setTitle("KeepAlive");
        initDialog();
        pack();
    }

    private void initDialog() {
        textField = new JTextField(20);
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new NumberFilter());

        Object[] controls = { "Specify poll interval (in seconds):", textField };
        Object[] options = { OK_STRING, CANCEL_STRING };

        optionPane = new JOptionPane(controls, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);
        setContentPane(optionPane);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        optionPane.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {

                if (isVisible() && JOptionPane.VALUE_PROPERTY.equals(e.getPropertyName())) {

                    Object value = optionPane.getValue();

                    if (value == JOptionPane.UNINITIALIZED_VALUE) {
                        return;
                    }

                    switch (value.toString()) {
                    case OK_STRING:
                        String v = textField.getText();
                        if (checkValue(v)) {
                            pollInterval = Integer.parseInt(v, 10);
                            setVisible(false);
                        } else {
                            //to let PropertyChangeEvent fire next time the user presses button
                            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
                        }
                        break;

                    case CANCEL_STRING:
                        pollInterval = null;
                        setVisible(false);
                        break;
                    }
                }
            }

        });
    }

    private boolean checkValue(String txt) {
        if (txt != null && txt.length() > 0) {
            //avoid parsing possibly very long string into integer
            if (txt.length() <= maxPollInterval.toString().length()) {
                try {
                    Integer value = Integer.parseInt(txt, 10);
                    if (value >= minPollInterval && value <= maxPollInterval) {
                        return true;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Please specify value in range " + minPollInterval + " - " + maxPollInterval, "Wrong value", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public Integer getPollInterval() {
        return pollInterval;
    }
}

class NumberFilter extends DocumentFilter 
{
    private static final Pattern pattern = Pattern.compile("^[0-9]+$");

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
                        AttributeSet attrs) throws BadLocationException {
        if (isNumber(text))
            super.replace(fb, offset, length, text, attrs);
        else {
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        if (isNumber(text)) {
            super.insertString(fb, offset, text, attr);
        } else {
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
    }

    private static boolean isNumber(String s) {
        Matcher m = pattern.matcher(s);
        return m.matches();
    }
}
