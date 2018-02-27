package app.main;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import app.database.DatabaseManager;

public class MainWindow {
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField addressField;
    private JList dataList;
    private JTextField cityField;
    private JButton addButton;
    private JTextField idField;
    private JButton removeButton;
    private JPanel mainWindow;
    private JTextField personIDField;
    private JButton refreshButton;
    private static DatabaseManager dm;

    public MainWindow() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(personIDField.getText());
                    String lastName = lastNameField.getText();
                    String firstName = firstNameField.getText();
                    String address = addressField.getText();
                    String city = cityField.getText();
                    if(lastName.equals("")||firstName.equals("")||address.equals("")||city.equals(""))
                        JOptionPane.showMessageDialog(mainWindow, "All fields must be filled");
                    else{
                        dm.addPerson(id,lastName, firstName, address, city);
                        refresh();
                    }
                }
                catch(Exception ee){
                    JOptionPane.showMessageDialog(mainWindow, "All fields must be filled");
                }

            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int personID = Integer.parseInt(idField.getText());
                try {
                    dm.removePerson(personID);
                    refresh();
                }
                catch(Exception ee){
                    System.err.println(ee.getMessage());
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refresh();
                }
                catch(Exception ee){
                    System.err.println(ee.getMessage());
                }
            }
        });
    }
    public void refresh(){
        try {
            ResultSet rs = dm.getPersons();
            ArrayList<String> temp = new ArrayList<>();
            while (rs.next()) {
                temp.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
            }
            dataList.setListData(temp.toArray());
        }
        catch(Exception ee){
            System.err.println(ee.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Data Base App");
        frame.setContentPane(new MainWindow().mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        try {
            dm = new DatabaseManager();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

    }

}
