package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Find
{
    void showWin(boolean visible)
    {
        JFrame frame = new JFrame("Найти запись");
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
        
        panel.add(back);
        
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
