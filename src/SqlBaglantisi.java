import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlBaglantisi {
    Connection conn;

    public SqlBaglantisi() {
        try {
            // db parameters
            String url = "jdbc:sqlite:C://Users/ITopya/Documents/NetBeansProjects/HarmoniManagement/.gitignore/harmonidatabase.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Baglanti Sağlandı");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    
}
