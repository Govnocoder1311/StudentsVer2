package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class BDStudents 
{
    void showWin(boolean visible, String username)
    {
        JFrame frame = new JFrame("Работа с БД");
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        
        JButton find = new JButton("Поиск");
        JButton view = new JButton("Посмотреть всех студентов");
        JButton add = new JButton("Добавить записи о студенте");
        
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("Файл");
        JMenuItem exit = new JMenuItem("Выход");

        file.add(exit);
        menuBar.add(file);

        exit.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame.setVisible(false);

                LogIn in = new LogIn();
                in.showWin();
            }
        });
        
        //Поиск
        find.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame.setVisible(false);
                Find find = new Find();
                try 
                {
                    find.showWin(true);
                } 
                catch (SQLException ex) 
                {
                    System.out.println("Ошибка подключения!");
                }
            }
        });
        
        //Добавить запись о новом студенте
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame.setVisible(false);
                Add add = new Add();
                add.showWin(true);
            }
        });
        
        //Посмотреть все записи о студентах
        view.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame.setVisible(false);
                View view = new View();
                try 
                {
                    view.showWin(true);
                } 
                catch (SQLException ex) 
                {
                    System.out.println("Ошибка подключения!");
                }
            }
        });
        
        //Выход
        exit.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame.setVisible(false);
                LogIn in = new LogIn();
                in.showWin();
            }
        });
        
        panel.add(view);
        panel.add(add);
        panel.add(find);
        
        frame.setJMenuBar(menuBar);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(visible);
    }
}