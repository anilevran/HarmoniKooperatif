import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
<<<<<<< HEAD
=======
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
>>>>>>> anil

public class Stok extends JFrame {
    JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8;
    JButton button1,button2,button3,button4,button5,button6,button7;
    JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9,label10,label11,label12,label13,label14,label15,label16,label17,label18,label19,label20,label21,label22,label23,label24;
    JComboBox combobox,combobox2,combobox3,combobox4;
    JTextField textfield1,textfield2,textfield3,textfield4,textfield5,textfield6,textfield7,textfield8,textfield9,textfield10,textfield11;
    /*String[] urun_comboboxItems = {"Bashful", "Doc", "Dopey",
      "Grumpy", "Happy", "Sleepy",
      "Sneezy"};*/
    String[] urun_comboboxItems,malzeme_comboboxItems;
    String[] birimItems = {"Metre", "Santimetre" , "Top" , "Kilogram" , "Gram" , "Adet"};
    JTable table;
    String[] basliklar = {"Ürün Kodu","İsim","Adet","Maliyet Fiyatı","Satış Fiyatı"};
    SqlBaglantisi baglanti = new SqlBaglantisi();
    String[][] data = new String[veriSayisi()][basliklar.length];
    JScrollPane tablepane;
    DefaultTableModel tm,tm2;
    Font font1,font2;
    
    
    public Stok() {
        initObjects();
    }
    public int idGenerate(){
        int id = 0;
        try{
            String sql = "SELECT Urun_kodu FROM Stok";
            Statement stmt = null;
            stmt = baglanti.conn.createStatement();    
            ResultSet rs = stmt.executeQuery(sql);
            ResultSet rs2 = rs;
            while(rs.next()){
                id  = rs.getInt("Urun_kodu");
            }
           
        }catch(SQLException ex){
                System.out.println("idgenerate fonk çalışmadı");
        }
        
        
        return id + 1;
    }
    private int veriSayisi(){
        int veriSayisi = 0;
        try{
            String sql = "SELECT * FROM Stok";
            Statement stmt = null;
            stmt = baglanti.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                veriSayisi++;
            }
        }catch(SQLException ex){
            System.out.println("verisayısı fonk catch");
        }
        
        return veriSayisi;
    }
    private boolean StoktabloDoldur(){
        try{
            String sql = "SELECT * FROM Stok";
            Statement stmt = null;
            stmt = baglanti.conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(sql);
            int colcount = rs.getMetaData().getColumnCount()-1;//Veritabanındaki tabloda kaç tane sütun var?
            tm = new DefaultTableModel(); //Model oluşturuyoruz
            for(int i = 1;i<=colcount;i++){
                tm.addColumn(rs.getMetaData().getColumnName(i));
                //System.out.println(rs.getMetaData().getColumnName(i));//Tabloya sütun ekliyoruz veritabanımızdaki sütun ismiyle aynı olacak şekilde
            }
            colcount++;
            while(rs.next()){
                Object[] row = new Object[colcount];
                boolean condition = false;
                for(int i=1;i<=colcount;i++){
                    row[i-1] = rs.getObject(i);
                    if(i == 6 && rs.getObject(i).equals(combobox.getSelectedItem().toString())){
                        condition = true;
                        //rs.getObject(i) == combobox.getSelectedItem().toString();
                    }
                       
                }
                if(condition)
                    tm.addRow(row);

                }
            
            table.getTableHeader().setFont(font2);
            table.setFont(font2);
            table.setModel(tm);
            System.out.println("tablo dolduruldu");
            
        }catch(SQLException ex){
            System.out.println("sıkıntılı");
            return false;
        }
        return true;
    }
    private boolean Stok2tabloDoldur(){
        try{
            String sql = "SELECT * FROM Stok2";
            Statement stmt = null;
            stmt = baglanti.conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(sql);
            int colcount = rs.getMetaData().getColumnCount()-1;//Veritabanındaki tabloda kaç tane sütun var?
            tm2 = new DefaultTableModel(); //Model oluşturuyoruz
            for(int i = 1;i<=colcount;i++){
                tm2.addColumn(rs.getMetaData().getColumnName(i));
                //System.out.println(rs.getMetaData().getColumnName(i));//Tabloya sütun ekliyoruz veritabanımızdaki sütun ismiyle aynı olacak şekilde
            }
            while(rs.next()){
                Object[] row = new Object[colcount];
                boolean condition = false;
                for(int i=1;i<=colcount;i++){
                    row[i-1] = rs.getObject(i);
                    if(i == 1){
                        if(rs.getObject(i).equals(combobox2.getSelectedItem()))
                            condition = true;
                    }
                }
                if(condition)
                    tm2.addRow(row);
                }
            System.out.println("tablo2 dolduruldu");
            table.setModel(tm2);
            
        }catch(SQLException ex){
            System.out.println("sıkıntılı");
            return false;
        }
        return true;
    }
    private String[] urunCombobox(){
        String[] array = null;
        try{
            String sql = "SELECT Kategori FROM Stok";
            Statement stmt = null;
            stmt = baglanti.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            array = new String[resultSetCount(rs)];
            ResultSet rs2 = stmt.executeQuery(sql);
            int i = 0;
            int j = 1;
            while(rs2.next()){
                array[i] = rs2.getString("Kategori");
                j++;
                i++;
            }
            int size = array.length;
            array = removeDuplicateElements(array);
            return array;
            
        }catch(SQLException ex){
            System.out.println("combobox doldurulamadı");
            return null;
        }
        
        
    }
    private String[] malzemeCombobox(){
        String[] array = null;
        try{
            String sql = "SELECT Malzeme_adi FROM Stok2";
            Statement stmt = null;
            stmt = baglanti.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            array = new String[resultSetCount(rs)];
            ResultSet rs2 = stmt.executeQuery(sql);
            int i = 0;
            int j = 1;
            while(rs2.next()){
                array[i] = rs2.getString("Malzeme_adi");
                j++;
                i++;
            }
            int size = array.length;
            //array = removeDuplicateElements(array);
            return array;
            
        }catch(SQLException ex){
            System.out.println("combobox doldurulamadı");
            return null;
        }
        
        
    }
    private int resultSetCount(ResultSet resultSet) throws SQLException{
    try{
        int i = 0;
        while (resultSet.next()) {
            i++;
        }
        return i;
    } catch (Exception e){
       System.out.println("Error getting row count");
       e.printStackTrace();
    }
    return 0;
}
    private String[] removeDuplicateElements(String[] array){
        LinkedHashSet<String> lhSetColors =  
                new LinkedHashSet<String>(Arrays.asList(array));
        
        //create array from the LinkedHashSet
        String[] newArray = lhSetColors.toArray(new String[ lhSetColors.size() ]);
        return newArray;
    }  
    private Font createFont(String fileName,Float size){
        Font tempFont = null;
        try {
            //create the font to use. Specify the size!
            tempFont = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(tempFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        return tempFont;
    }
    private void initObjects(){
        font1 = createFont("AlfaSlabOne-Regular.ttf",20f);
        font2 = createFont("AlfaSlabOne-Regular.ttf",14f);
        urun_comboboxItems = urunCombobox();
        malzeme_comboboxItems = malzemeCombobox();
        //setExtendedState(MAXIMIZED_BOTH); 
        //setUndecorated(true);
        setTitle("Stok Ekranı");
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(1,2));
        
        
        //panel initilazing
        panel1 = new JPanel(new GridLayout(2,2));
        panel2 = new JPanel(new GridLayout(2,1));
        
        panel3 = new JPanel(null);
        panel4 = new JPanel(null);
        panel5 = new JPanel(null);
        panel6 = new JPanel(null);
        
        panel7 = new JPanel(new GridLayout(1,1));
        panel8 = new JPanel(null);
        
        
        //panel borderları
        panel3.setBorder(BorderFactory.createLineBorder(Color.black));
        panel4.setBorder(BorderFactory.createLineBorder(Color.black));
        panel5.setBorder(BorderFactory.createLineBorder(Color.black));
        panel6.setBorder(BorderFactory.createLineBorder(Color.black));
        panel7.setBorder(BorderFactory.createLineBorder(Color.black));
        panel8.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //panel1 içindeki paneller
        panel1.add(panel3);
        panel1.add(panel4);
        panel1.add(panel5);
        panel1.add(panel6);
        
        //panel2 içindeki paneller
        panel2.add(panel7);
        panel2.add(panel8);
        
        //panel3 içindeki objeler
        label1 = new JLabel("Ürün Stok Durumu");
        label1.setFont(font1);
        
        label2 = new JLabel("Ürün Kategorisi: ");
        label2.setFont(font2);
        
        combobox = new JComboBox(urun_comboboxItems);
        combobox.setFont(font2);
        
        button1 = new JButton("Ürün Stoğu Göster");
        button1.setFont(font2);
        
        //panel3 - buton1 action
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StoktabloDoldur();
                urun_comboboxItems = urunCombobox();
            }
        });
        
        panel3.add(label1);
        panel3.add(label2);
        panel3.add(combobox);
        panel3.add(button1);
        
        label1.setBounds(150, 0,250,50);
        label2.setBounds(15, 75, 150, 50);
        combobox.setBounds(200, 82, 150, 35);
        button1.setBounds(300, 450, 150, 50);
        
        
        //panel4 içindeki objeler
        label3 = new JLabel("Ürün Stok Ekleme");
        label3.setFont(font1);
        
        label4 = new JLabel("Ürün Adı: ");
        label4.setFont(font2);
        
        label5 = new JLabel("Ürün Adedi: ");
        label5.setFont(font2);
        
        label6 = new JLabel("Ürün Maliyet Fiyatı: ");
        label6.setFont(font2);
        
        label7 = new JLabel("Ürün Satış Fiyatı: ");
        label7.setFont(font2);
        
        label23 = new JLabel("Ürün Kategorisi: ");
        label23.setFont(font2);
        
        label24 = new JLabel("Yeni Kategori Girişi");
        label24.setFont(font2);
        
        textfield1 = new JTextField("");
        textfield1.setFont(font2);
        textfield2 = new JTextField("");
        textfield2.setFont(font2);
        textfield3 = new JTextField("");
        textfield3.setFont(font2);
        textfield4 = new JTextField("");
        textfield4.setFont(font2);
        textfield11 = new JTextField("");
        textfield11.setFont(font2);
        
        combobox4 = new JComboBox(urun_comboboxItems);
        combobox4.setFont(font2);
        
        //panel4 - buton1 action
        button5 = new JButton("Yeni Kategori Ile Ekle");
        button5.setFont(font2);
        button5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "INSERT INTO Stok(Urun_kodu,Isim,Adet,Maliyet_Fiyati,Satis_Fiyati,Kategori) VALUES(?,?,?,?,?,?)";
            try{
                PreparedStatement pstmt = baglanti.conn.prepareStatement(sql);
                pstmt.setInt(1, idGenerate());
                pstmt.setString(2,textfield1.getText());
                pstmt.setInt(3,Integer.parseInt(textfield2.getText()));
                pstmt.setInt(4,Integer.parseInt(textfield3.getText()));
                pstmt.setInt(5,Integer.parseInt(textfield4.getText()));
                pstmt.setString(6, textfield11.getText());
                pstmt.executeUpdate();
                combobox.addItem(textfield11.getText());
                combobox4.addItem(textfield11.getText());
                
                
                
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            /*finally{
                    try {
                        baglanti.conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Stok.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }*/
                }
        });
        
        //panel4 - buton2 action
        button2 = new JButton("Ürün Stoğa Ekle");
        button2.setFont(font2);
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "INSERT INTO Stok(Urun_kodu,Isim,Adet,Maliyet_Fiyati,Satis_Fiyati,Kategori) VALUES(?,?,?,?,?,?)";
            try{
                PreparedStatement pstmt = baglanti.conn.prepareStatement(sql);
                pstmt.setInt(1, idGenerate());
                pstmt.setString(2,textfield1.getText());
                pstmt.setInt(3,Integer.parseInt(textfield2.getText()));
                pstmt.setInt(4,Integer.parseInt(textfield3.getText()));
                pstmt.setInt(5,Integer.parseInt(textfield4.getText()));
                pstmt.setString(6, combobox4.getSelectedItem().toString());
                pstmt.executeUpdate();
                
                
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            /*finally{
                    try {
                        baglanti.conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Stok.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }*/
                }
            });
        
        
        panel4.add(label3);
        panel4.add(label4);
        panel4.add(label5);
        panel4.add(label6);
        panel4.add(label7);
        panel4.add(label23);
        panel4.add(label24);
        panel4.add(button2);
        panel4.add(button5);
        panel4.add(textfield1);
        panel4.add(textfield2);
        panel4.add(textfield3);
        panel4.add(textfield4);
        panel4.add(textfield11);
        panel4.add(combobox4);
        
        
        label3.setBounds(150, 0,200,50);
        label4.setBounds(15, 75,200,50);
        label5.setBounds(15, 125,200,50);
        label6.setBounds(15, 175,200,50);
        label7.setBounds(15, 225,200,50);
        label23.setBounds(15, 275,200,50);
        label24.setBounds(15, 375, 200, 50);
        button2.setBounds(300, 450, 150, 50);
        button5.setBounds(50, 450, 200, 50);
        textfield1.setBounds(180, 85,200,35);
        textfield2.setBounds(180, 135,200,35);
        textfield3.setBounds(180, 185,200,35);
        textfield4.setBounds(180, 235,200,35);
        textfield11.setBounds(180, 385, 200, 35);
        combobox4.setBounds(180, 285,200,35);
        
        
        //panel5 içindeki objeler
        label8 = new JLabel("Malzeme Stok Durumu");
        label8.setFont(font1);
        
        label9 = new JLabel("Malzeme Adı: ");
        label9.setFont(font2);
        
        combobox2 = new JComboBox(malzeme_comboboxItems);
        combobox2.setFont(font2);
        
        button3 = new JButton("Malzeme Stoğu Göster");
        button3.setFont(font2);
        
        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Stok2tabloDoldur();
            }
        });
        
        panel5.add(label8);
        panel5.add(label9);
        panel5.add(combobox2);
        panel5.add(button3);
        
        label8.setBounds(150, 0,250,50);
        label9.setBounds(15, 75, 150, 50);
        combobox2.setBounds(150, 82, 150, 35);
        button3.setBounds(275, 450, 175, 50);
        
        
        //panel6 içindeki objeler
        label10 = new JLabel("Malzeme Stok Ekleme");
        label10.setFont(font1);
        
        label11 = new JLabel("Malzeme Adı: ");
        label11.setFont(font2);
        
        label12 = new JLabel("Malzeme Birimi: ");
        label12.setFont(font2);
        
        label13 = new JLabel("Malzeme Adedi: ");
        label13.setFont(font2);
        
        textfield5 = new JTextField("");
        textfield5.setFont(font2);
        textfield6 = new JTextField("");
        textfield6.setFont(font2);
        
        button4 = new JButton("Ürün Stoğa Ekle");
        button4.setFont(font2);
        
        //panel6 - buton 4 action
        button4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String sql2 = "INSERT INTO Stok2(Malzeme_adi,Malzeme_birimi,Malzeme_adedi) VALUES(?,?,?)";
            try{
                PreparedStatement pstmt2 = baglanti.conn.prepareStatement(sql2);
                pstmt2.setString(1,textfield5.getText());
                pstmt2.setString(2,combobox3.getSelectedItem().toString());
                pstmt2.setInt(3,Integer.parseInt(textfield6.getText()));
                pstmt2.executeUpdate();
                
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            }
        });
        
        combobox3 = new JComboBox(birimItems);
        combobox3.setFont(font2);
        
        panel6.add(label10);
        panel6.add(label11);
        panel6.add(label12);
        panel6.add(label13);
        panel6.add(textfield5);
        panel6.add(combobox3);
        panel6.add(textfield6);
        panel6.add(button4);
        
        label10.setBounds(150, 0,220,50);
        label11.setBounds(15, 75,200,50);
        label12.setBounds(15, 125,200,50);
        label13.setBounds(15, 175,200,50);
        button4.setBounds(300, 450, 150, 50);
        textfield5.setBounds(180, 85,200,35);
        combobox3.setBounds(180, 135,200,35);
        textfield6.setBounds(180, 185,200,35);
        
        
        
        //panel 7 içindeki objeler
        table = new JTable();
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablepane = new JScrollPane(table);
        tablepane.setBackground(Color.red);
        table.setBorder(BorderFactory.createLineBorder(Color.black));
        panel7.add(tablepane);
        
        //panel 8 içindeki objeler
        label19 = new JLabel("İSİM");
        label19.setFont(font2);
        label20 = new JLabel("ADET");
        label20.setFont(font2);
        label21 = new JLabel("MALİYET FİYATI");
        label21.setFont(font2);
        label22 = new JLabel("SATIŞ FİYATI");
        label22.setFont(font2);
        label19.setBounds(100, 50, 100, 100);
        label20.setBounds(100, 150, 100, 100);
        label21.setBounds(100, 250, 100, 100);
        label22.setBounds(100, 350, 100, 100);
        button6 = new JButton("Veriyi Güncelle");
        button6.setFont(font2);
        button6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    String sql = "UPDATE Stok SET Isim = ?, Adet = ?, Maliyet_Fiyati = ?, Satis_Fiyati = ?, WHERE Isim = ?";
                try{
                    PreparedStatement update = baglanti.conn.prepareStatement("UPDATE Stok SET Isim = ?, Adet = ?, Maliyet_Fiyati = ?, Satis_Fiyati = ? WHERE Isim = ?");
                    
                    update.setString(1, textfield7.getText());
                    update.setInt(2,Integer.parseInt(textfield8.getText()));
                    update.setInt(3,Integer.parseInt(textfield9.getText()));
                    update.setInt(4,Integer.parseInt(textfield10.getText()));
                    System.out.println(table.getValueAt(table.getSelectedRow(), 1).toString());
                    update.setString(5, table.getValueAt(table.getSelectedRow(), 1).toString());
                    update.executeUpdate();
                    System.out.println("tamamdır");
                
                
                }
                catch (SQLException ex) {
                    System.out.println("button6action");
                }
            }
        });
        button7 = new JButton("Veriyi Sil");
        button7.setFont(font2);
        button7.addActionListener(new ActionListener() {

            //yapım aşamasında
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
<<<<<<< HEAD
                    System.out.println("1");
                    PreparedStatement pstmt = baglanti.conn.prepareStatement("DELETE FROM Stok WHERE Isim = ?;");
                    System.out.println("2");
                    pstmt.setString(1, textfield7.getText());
                }catch(SQLException ex){
                    
=======
                    String asd = textfield7.getText();
                    PreparedStatement pstmt = baglanti.conn.prepareStatement("DELETE FROM Stok WHERE Isim = ?");
                    pstmt.setString(1, textfield7.getText());
                    pstmt.executeUpdate();
                }catch(SQLException ex){
                    System.out.println("buton7 action catch");
>>>>>>> anil
                }
            }
        });
        button6.setBounds(550,100,150,75);
        button7.setBounds(550,300,150,75);
        textfield7 = new JTextField(20);
        textfield7.setFont(font2);
        textfield8 = new JTextField(20);
        textfield8.setFont(font2);
        textfield9 = new JTextField(20);
        textfield9.setFont(font2);
        textfield10 = new JTextField(20);
        textfield10.setFont(font2);
        textfield7.setBounds(250, 83, 150, 32);
        textfield8.setBounds(250, 183, 150, 32);
        textfield9.setBounds(250, 283, 150, 32);
        textfield10.setBounds(250, 383, 150, 32);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                for (int i = 1; i < 5; i++) {
                        if(i == 1)
                            textfield7.setText(table.getValueAt(table.getSelectedRow(), i).toString());
                        else if(i == 2)
                            textfield8.setText(table.getValueAt(table.getSelectedRow(), i).toString());
                        else if(i == 3)
                            textfield9.setText(table.getValueAt(table.getSelectedRow(), i).toString());
                        else
                            textfield10.setText(table.getValueAt(table.getSelectedRow(), i).toString());
                }
            }
        });
        
        panel8.add(label19);
        panel8.add(label20);
        panel8.add(label21);
        panel8.add(label22);
        panel8.add(textfield7);
        panel8.add(textfield8);
        panel8.add(textfield9);
        panel8.add(textfield10);
        panel8.add(button6);
        panel8.add(button7);
        
        
        
        
        
        //ekrana eklenen paneller
        add(panel1);
        add(panel2);
        
        setVisible(true);
    }
}
