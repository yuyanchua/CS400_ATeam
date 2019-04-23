package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorScreen extends JFrame implements ActionListener{

  // Declaration of object of JButton class.
  JButton b;

  // Constructor of Demo class
  public ErrorScreen() {
    // Setting layout as null of JFrame.
    this.setLayout(null);

    // Initialization of object of "JButton" class.
    b = new JButton("Button 1");

    // Setting Bounds of a JButton.
    b.setBounds(130, 05, 100, 50);

    // "this" keyword in java refers to current object.
    // Adding JButton on JFrame.
    this.add(b);

    // Adding Listener toJButton.
    b.addActionListener(this);
  }

  // Override Method
  public void actionPerformed(ActionEvent evt) {
    if (evt.getSource() == b) {
      // Code To popup an ERROR_MESSAGE Dialog.
      JOptionPane.showMessageDialog(this, "Enter a valid answer.", "ERROR",
          JOptionPane.ERROR_MESSAGE);
    }
  }
  
  public void start(ErrorScreen e) {
	// Setting Bounds of a Frame.
    e.setBounds(200, 200, 400, 300);

    // Setting Resizeable status of frame as false
    e.setResizable(false);

    // Setting Visible status of frame as true.
    e.setVisible(true);
  }

  // Driver code
  public static void main(String args[]) {

    // Creating Object of demo class.
    ErrorScreen e = new ErrorScreen();
    e.start(e);
    
  }

}
