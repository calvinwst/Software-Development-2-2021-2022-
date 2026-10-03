import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TempConvert extends JFrame implements ActionListener, WindowListener {


    private Container contentPane = this.getContentPane();

    private JLabel cLabel = new JLabel("C:"), fLabel = new JLabel("F:");
    private JLabel statusLabel = new JLabel(" ");

    private JTextField cArea = new JTextField(10), fArea = new JTextField(10);

    private double cValue, fValue;

    public TempConvert()
    {
        this.setSize(400,120);
        this.setResizable(false);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addWindowListener(this);

        this.setTitle("Temp Convert");
    }

    public void init()
    {

        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(0,1,0,1);

        gbc.gridx = 1;
        gbc.gridy = 1;

        contentPane.add(cLabel, gbc);

        gbc.gridx = 2;

        contentPane.add(fLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 1;
        gbc.gridy = 2;

        contentPane.add(cArea, gbc);

        gbc.gridx = 2;

        contentPane.add(fArea, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        statusLabel.setForeground(new Color(170, 0, 0));
        contentPane.add(statusLabel, gbc);

        cArea.addActionListener(this);

        fArea.addActionListener(this);

        this.setVisible(true);
    }

    public double convertTemp(double d, char c)
    {
        if (c == 'C')
        {
            return Math.round(((d*1.8)+32)*100)/100.0;
        }
        else
        {
            return Math.round(((d-32)/1.8)*100)/100.0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField inputField = e.getSource() == cArea ? cArea : fArea;
        try {
            double input = Double.parseDouble(inputField.getText().trim());
            if (!Double.isFinite(input)) {
                throw new NumberFormatException();
            }

            if (inputField == cArea) {
                cValue = input;
                fValue = convertTemp(cValue, 'C');
            } else {
                fValue = input;
                cValue = convertTemp(fValue, 'F');
            }

            statusLabel.setText(" ");
            cArea.setText("" + cValue);
            fArea.setText("" + fValue);
        } catch (NumberFormatException ex) {
            statusLabel.setText("Enter a valid finite number.");
            inputField.selectAll();
            inputField.requestFocusInWindow();
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Window starts");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // we can do something here


        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public static void main(String[] args) {
        new TempConvert().init();
    }
}
