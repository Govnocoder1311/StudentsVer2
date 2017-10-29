package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;

public class View 
{
    private static final String USERNAME = "root"; //имя пользователя
    private static final String PASSWORD = "1234"; //пароль
    private static final String URL = "jdbc:mysql://localhost:3306/bdstudents?useSSL=";//свой url
    
    private ArrayList<String []> dataArrayList;
    
    void showWin(boolean visible) throws SQLException
    {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        String query = "select * from bdstudents.students";
        Statement statement = conn.createStatement();
        ResultSet resSet = statement.executeQuery(query);
        
        ResultSetMetaData md = resSet.getMetaData();
        int columnCount = md.getColumnCount();
        Vector columns = new Vector(columnCount);
        for(int i=1; i<=columnCount; i++)
        columns.add(md.getColumnName(i));
        Vector data = new Vector();
        Vector row;
        while(resSet.next())
        {
            row = new Vector(columnCount);
            for(int i=1; i<=columnCount; i++)
            {
                row.add(resSet.getString(i));
            }
            data.add(row);
        }
        JTable table = new JTable (data, columns);
        
        JFrame frame = new JFrame("Все записи");
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        
        JButton back = new JButton("Назад");
        
        //Назад
        back.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame.setVisible(false);
                BDStudents bdsudents = new BDStudents();
                bdsudents.showWin(true, "Admin");
            }
        });
        
        panel.add(table);
        panel.add(back);
        
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(visible);
        
        statement.close();
        conn.close();
    }
}