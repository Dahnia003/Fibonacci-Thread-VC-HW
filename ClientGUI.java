package cop2805;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClientGUI extends JFrame{
	private final JLabel responseLabel;
    private final JTextField numberField;
    private final JButton sendButton;

 Socket socket;
 PrintWriter pr;
 BufferedReader br;

    public ClientGUI() {
    	
      
        setTitle("Final Project Fibonacci Sequence");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));


        numberField = new JTextField(40);
        numberField.setHorizontalAlignment(JTextField.CENTER);
        responseLabel = new JLabel("Answer", JLabel.CENTER);
        sendButton = new JButton("Calculate");
        sendButton.addActionListener(new ActionListener() {//adding button to send number & connect to server
            @Override
            public void actionPerformed(ActionEvent e) {
                sendNumberToServer();
                
            }
        });
       add(sendButton);
       add(responseLabel);
       add(numberField);
      pack();
      setLocationRelativeTo(null);
        setVisible(true);  
        connectToServer();
        }
    
      private void connectToServer() {  
        try {//socket, br and pr already defined in GUI
             socket = new Socket("localhost", 1234);
             pr = new PrintWriter(socket.getOutputStream(), true);              
           br = new BufferedReader ( 
             		new InputStreamReader(socket.getInputStream()));       
         } //try stop
               catch (IOException e) { e.printStackTrace(); }
    }//method stop

   

    private void sendNumberToServer() { //method to send number to server
        String stringNum = numberField.getText().trim();

        try {
        	int number= Integer.parseInt(stringNum);
        	if (number>0) {
        		pr.println(stringNum);
        	String response=br.readLine();
          responseLabel.setText("Server response: " + response);}
          else {
              JOptionPane.showMessageDialog(this, "Please enter a valid positive number.");
          }
      } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(this, "Please enter a valid positive number.");
      } catch (IOException e) {
          e.printStackTrace();
      }}
        
    
 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { new ClientGUI();} });
}
}
