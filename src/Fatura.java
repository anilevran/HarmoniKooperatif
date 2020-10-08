import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fatura extends JFrame {
     JPanel panel1,panel2;
     JButton button1;
     JLabel label1,label2,label3,label4,label5,label6;
     JTextField textfield1,textfield2,textfield3,textfield4,textfield5,textfield6;
    

    public Fatura() {
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setTitle("Fatura Ekranı");
        setResizable(false);      
        setLayout(new GridLayout(1,2));
        
        
        
        
      //panel initilazing
      panel1 = new JPanel(null);
      panel2 = new JPanel(new GridLayout(1,1));
      
      //panel borderları
      panel1.setBorder(BorderFactory.createLineBorder(Color.black));
      panel2.setBorder(BorderFactory.createLineBorder(Color.black));
      
      
      //panel1 içindeki objeler
        label1 = new JLabel("Fatura Tarihi:");
        label1.setFont(new Font(null, Font.BOLD, 17));
        
        label2 = new JLabel("Fatura No: ");
        label2.setFont(new Font(null, Font.BOLD, 17));
        
        label3 = new JLabel("Program Fatura No: ");
        label3.setFont(new Font(null, Font.BOLD, 17));
        
        label4 = new JLabel("Ürün Adı: ");
        label4.setFont(new Font(null, Font.BOLD, 17));
        
        label5 = new JLabel("Ürün Fiyatı: ");
        label5.setFont(new Font(null, Font.BOLD, 17));
        
        label6 = new JLabel("Ürün Adedi: ");
        label6.setFont(new Font(null, Font.BOLD, 17));
        
        panel1.add(label1);
        panel1.add(label2);    
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(label5);
        panel1.add(label6);
        
        
        
        
        label1.setBounds(15, 0,   150, 50);
        label2.setBounds(15, 50,  150, 50);
        label3.setBounds(15, 100, 150, 50);
        label4.setBounds(15, 150, 150, 50);
        label5.setBounds(15, 200, 150, 50);
        label6.setBounds(15, 250, 150, 50);
        
        
      
      //ekrana eklenen paneller
      add(panel1);
      add(panel2);
      
      setVisible(true);
    }
    
}
