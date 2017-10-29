package main;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        try
        {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        }
        catch(SQLException ex)
        {
            System.out.println("Ошибка регистрации драйвера");
            return;
        }
        
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
                String id = tf_id_student.getText();
                int id2 = Integer.parseInt(id);
                String fio = tf_fio.getText();
                String kurs = tf_kurs.getText();
                int kurs2 = Integer.parseInt(kurs);
                String description = tf_description.getText();
                String omissions = tf_omissions.getText();
                int omissions2 = Integer.parseInt(omissions);
                String avatage_score = tf_avatage_score.getText();
                double avatage_score2 = Double.parseDouble(avatage_score);
                
                try(Connection connection =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement())
                {
                    statement.execute("INSERT INTO bdstudents.students"
                            + "(id_students, fio, kurs, description, omissions,"
                            + " avatage_score) values (" + id2 +", " + "\"" + fio 
                            + "\"" + ", " + kurs2 + ", " + "\"" + description 
                            + "\"" + ", " + omissions2 + ", " + avatage_score2 + ")");
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
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
    }
}
