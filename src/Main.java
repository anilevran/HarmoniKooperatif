import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        GirisEkrani ge = new GirisEkrani();
        
        
        
        
        /*String sql = "INSERT INTO Stok(Isim,Adet,Maliyet_Fiyati,Satis_Fiyati) VALUES(?,?,?,?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1,"anil");
            pstmt.setInt(2,10);
            pstmt.setInt(3,20);
            pstmt.setInt(4,40);
            
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        
         
    }
}
