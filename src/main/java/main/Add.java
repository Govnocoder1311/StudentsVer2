package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Add
{
    private static final String USERNAME = "root"; //имя пользователя
    private static final String PASSWORD = "1234"; //пароль
    private static final String URL = "jdbc:mysql://localhost:3306/bdstudents?useSSL=";//свой url
    
    void showWin(boolean visible) throws SQLException
    {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        
        JFrame frame = new JFrame("Добавить запись");
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        
        JLabel id_student = new JLabel("ID студента");
        JTextField tf_id_student = new JTextField();
        JLabel fio = new JLabel("ФИО студента");
        JTextField tf_fio = new JTextField();
        JLabel kurs = new JLabel("Курс");
        JTextField tf_kurs = new JTextField();
        JLabel description = new JLabel("Описание студента");
        JTextField tf_description = new JTextField();
        JLabel omissions = new JLabel("Пропуски");
        JTextField tf_omissions = new JTextField();
        JLabel avatage_score = new JLabel("Средний балл");
        JTextField tf_avatage_score = new JTextField();
        
        JButton add = new JButton("Добавить");
        JButton back = new JButton("Назад");
        
        //Добавить
        add.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String id1 = tf_id_student.getText();
                int id2 = Integer.parseInt(id1);
                String fio1 = tf_fio.getText();
                String kurs1 = tf_kurs.getText();
                int kurs2 = Integer.parseInt(kurs1);
                String description1 = tf_description.getText();
                String omissions1 = tf_omissions.getText();
                int omissions2 = Integer.parseInt(omissions1);
                String avatage_score1 = tf_avatage_score.getText();
                double avatage_score2 = Double.parseDouble(avatage_score1);
                try 
                {
                    insert(conn, id2, fio1, kurs2, description1, omissions2, avatage_score2);
                } 
                catch (SQLException ex)
                {
                    System.out.printf("Ошибка вставки");
                }
                tf_id_student.setText("");
                tf_fio.setText("");
                tf_kurs.setText("");
                tf_description.setText("");
                tf_omissions.setText("");
                tf_avatage_score.setText("");
            }
        });
        
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
        
        panel.add(id_student);
        panel.add(tf_id_student);
        panel.add(fio);
        panel.add(tf_fio);
        panel.add(kurs);
        panel.add(tf_kurs);
        panel.add(description);
        panel.add(tf_description);
        panel.add(omissions);
        panel.add(tf_omissions);
        panel.add(avatage_score);
        panel.add(tf_avatage_score);
        panel.add(add);
        panel.add(back);
        
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);
        frame.setVisible(true);
        
        conn.close();
    }
    
    public void insert(Connection con, int id, String fio, int kurs, 
            String description, int omissions, 
            double avatage_score) throws SQLException 
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("INSERT INTO students "
                    + "(id_student, fio, kurs, description, omissions, avatage_score) "
                    + "VALUES (?, ?, ?, ?, ?)");
            String id1 = Integer.toString(id);
            String kurs1 = Integer.toString(kurs);
            String omissions1 = Integer.toString(omissions);
            String avatage_score1 = Double.toString(avatage_score);
            stmt.setString(1, id1);
            stmt.setString(2, fio);
            stmt.setString(3, kurs1);
            stmt.setString(4, description);
            stmt.setString(5, omissions1);
            stmt.setString(6, avatage_score1);
            stmt.executeUpdate();
        }
        finally 
        {
            if (stmt != null) 
            {
                stmt.close();
            }
        }
    }
}
