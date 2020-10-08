import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Fatura extends JFrame {
     JPanel panel1,panel2;
     JButton button1;
     JLabel label1,label2,label3,label4,label5,label6;
     JTextField textfield1,textfield2,textfield3,textfield4,textfield5;
     SqlBaglantisi baglantiFatura = new SqlBaglantisi();

        public int idGenerate2(){
        int id = 0;
        try{
            String sql = "SELECT PFatura_No FROM Stok3";
            Statement stmt = null;
            stmt = baglantiFatura.conn.createStatement();    
            ResultSet rs = stmt.executeQuery(sql);          
            while(rs.next()){
                id  = rs.getInt("PFatura_No");
            }

        }catch(SQLException ex){
                System.out.println("idgenerate2 fonk çalışmadı");
        }


        return id + 1;
    } 


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

        label3 = new JLabel("Ürün Adedi: ");
        label3.setFont(new Font(null, Font.BOLD, 17));

        label4 = new JLabel("Ürün Adı: ");
        label4.setFont(new Font(null, Font.BOLD, 17));

        label5 = new JLabel("Ürün Fiyatı: ");
        label5.setFont(new Font(null, Font.BOLD, 17));



        textfield1 = new JTextField("");
        textfield2 = new JTextField("");
        textfield3 = new JTextField("");
        textfield4 = new JTextField("");
        textfield5 = new JTextField("");


        button1 = new JButton("Fatura Ekle");
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 String sql = "INSERT INTO Stok3(Fatura_Tarihi,Fatura_No,PFatura_No,Urun_Adi,Urun_Fiyati,Urun_Adedi) VALUES(?,?,?,?,?,?)";

                 try{
                     PreparedStatement pstmt = baglantiFatura.conn.prepareStatement(sql);                   
                     pstmt.setInt(1,Integer.parseInt( textfield1.getText()));
                     pstmt.setInt(2,Integer.parseInt(textfield2.getText()));
                     pstmt.setInt(3,idGenerate2());
                     pstmt.setString(4,textfield4.getText());
                     pstmt.setInt(5,Integer.parseInt(textfield5.getText()));
                     pstmt.setInt(6,Integer.parseInt(textfield3.getText()));
                     pstmt.executeUpdate();





                 } catch (SQLException ex) {
                     System.out.println("çalışmadı");
                }
            }
        });










        panel1.add(label1);
        panel1.add(label2);    
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(label5);


        panel1.add(textfield1);
        panel1.add(textfield2);
        panel1.add(textfield3);
        panel1.add(textfield4);
        panel1.add(textfield5);


        panel1.add(button1);


        label1.setBounds(15, 50,   150, 100);
        label2.setBounds(15, 100,  150, 150);
        label3.setBounds(15, 150, 150, 200);
        label4.setBounds(15, 200, 150, 250);
        label5.setBounds(15, 250, 150, 300);

        textfield1.setBounds(200, 80,200,35);
        textfield2.setBounds(200, 155,200,35);
        textfield3.setBounds(200, 230,200,35);
        textfield4.setBounds(200, 305,200,35);
        textfield5.setBounds(200, 380,200,35);    
        button1.setBounds(700, 600, 150, 50);


      //ekrana eklenen paneller
      add(panel1);
      add(panel2);

      setVisible(true);
    }
}