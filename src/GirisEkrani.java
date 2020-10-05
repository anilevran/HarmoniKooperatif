import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GirisEkrani extends JFrame {
    JPanel panel;
    JButton button1,button2,button3,button4;

    public GirisEkrani(){
        setSize(400,300);
        setLocationRelativeTo(null);
        setTitle("Giriş");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();
        //buton1 özellikleri
        button1 = new JButton("Fatura");
        button1.setSize(100,50);
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Fatura faturaEkrani = new Fatura();
            }
        });
        button2 = new JButton("Stok");
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Stok stokEkrani = new Stok();
                
            }
        });
        button3 = new JButton("Çalışanlar");
        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Calisanlar calisanlarEkrani = new Calisanlar();
            }
        });
        button4 = new JButton("Kasa");
        button4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Kasa kasaEkrani = new Kasa();
            }
        });
        GridLayout layout = new GridLayout(4,1);
        layout.setVgap(20);
        panel.setLayout(layout);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        add(panel);
        setVisible(true);
        
        
        
    }
    
}
