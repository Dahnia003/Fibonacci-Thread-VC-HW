package cop2805;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class FibonacciGUI extends JFrame{
	private JLabel labelName;
	private  JLabel responseLabel;
    private JTextField txtField;
    private  JButton sendButton;
    
//declaring socket, input and output because they will be used in different methods
 Socket socket;
 PrintWriter pr;
 BufferedReader br;

    public FibonacciGUI() {
    	
      
        setTitle("Final Project Fibonacci Sequence");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        labelName=new JLabel("Enter Fibonacci Number");
        txtField = new JTextField(40);
        responseLabel = new JLabel("Answer");
        JPanel BP =new JPanel(new GridLayout(5,5));
        sendButton = new JButton("Calculate");
        sendButton.addActionListener(new ActionListener() {//adding button to send number & connect to server
            @Override
            public void actionPerformed(ActionEvent e) {
                sendNumberToServer();
                
            }
        });
       add(BP, BorderLayout.EAST);
       add(labelName, BorderLayout.NORTH);
       add(sendButton, BorderLayout.EAST);
       add(responseLabel, BorderLayout.CENTER);
       add(txtField, BorderLayout.CENTER);
      pack();
      setLocationRelativeTo(null);
        setVisible(true);  
        connectToServer();
        }
    
      private void connectToServer() {  
        try {//socket, br and pr already defined in GUI
             socket = new Socket("localhost", 1234);
             pr = new PrintWriter(
            		 new OutputStreamWriter (socket.getOutputStream()));              
           br = new BufferedReader ( 
             		new InputStreamReader(socket.getInputStream()));       
         } //try stop
               catch (IOException e) { e.printStackTrace(); }
    }//method stop

              

    private void sendNumberToServer() { //method to send number to server
        String stringNum = txtField.getText().trim();

        try {
        	int number= Integer.parseInt(stringNum);
        	if (number > 0) {
        		pr.println(stringNum);
        	String response=br.readLine();
          responseLabel.setText("Server response: " + response);}
          else {// preventing program to crash
              JOptionPane.showMessageDialog(this, "Enter a positive number.");
          }
      } catch (NumberFormatException e) {// putting a try catch around integer parse int as instructed
          JOptionPane.showMessageDialog(this, "Enter a positive number.");
      } catch (IOException e) {
          e.printStackTrace();
      }}
        
    
 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { new FibonacciGUI();} });
}
}
