package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.*;

public class Find
{
    private static final String USERNAME = "root"; //имя пользователя
    private static final String PASSWORD = "1234"; //пароль
    private static final String URL = "jdbc:mysql://localhost:3306/bdstudents?useSSL=";//свой url
    
    void showWin(boolean visible) throws SQLException
    {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        String query = "select * from bdstudents.students where fio like '%Илья%'";
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
        
        JFrame frame = new JFrame("Найти запись");
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        
        JLabel label_find = new JLabel("Введите ФИО студента: ");
        JTextField tf_find = new JTextField();
        JButton back = new JButton("Назад");
        JButton find = new JButton("Найти");
        
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
        
        //Поиск
        find.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                /*String query = "select * from bdstudents.students where fio like '%Илья%'";
                Statement statement = null;
                try 
                {
                    statement = conn.createStatement();
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
                }
                try 
                {
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
                    table = new JTable (data, columns);
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            }
        });
        
        
        panel.add(label_find);
        panel.add(tf_find);
        panel.add(table);
        panel.add(find);
        panel.add(back);
        
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
