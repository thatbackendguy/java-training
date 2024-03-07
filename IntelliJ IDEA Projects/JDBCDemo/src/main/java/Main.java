import java.sql.*;

public class Main
{
    public static void main(String[] args)
    {
        var url = "jdbc:mysql://localhost:3306/demoDB";

        var username = "root";

        var password = "Root@1010";

        try
        {
            Connection conn = DriverManager.getConnection(url, username, password);

            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * from products");

            while(rs.next())
            {
                int prodID = rs.getInt("product_id");

                String prodName = rs.getString("product_name");

                double prodPrice = rs.getFloat("price");

                int prodQuantity = rs.getInt("quantity");

                System.out.println(prodID + "\t" + prodName + "\t" + prodPrice + "\t" + prodQuantity);

            }


        } catch(SQLException e)
        {
            System.out.println("Connection Error");
            throw new RuntimeException(e);
        }


    }
}
