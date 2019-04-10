import java.sql.*;

public class Netzzsql {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        if (args.length == 6) {
            System.out.println("Echo params: ");
            for (String arg : args) {
                System.out.println(arg);
            }

            Class.forName("org.netezza.Driver");
            String connectionString = "jdbc:netezza://" + args[0] + ":" + args[1]+"/"+args[5] +";";

            //Get connection using driver manager
            Connection connection = DriverManager.getConnection(connectionString, args[2], args[3]);
            if (connection != null) {
                System.out.println("Connection OK, we are in!");
            } else {
                System.out.println("Failed to create connection...");
                return;
            }
            Statement stm = connection.createStatement();
            ResultSet res = stm.executeQuery(args[4]);
            ResultSetMetaData rmd = res.getMetaData();

            while(res.next()) {
                System.out.println(res.getRow());
                for (int i = 1; i <= rmd.getColumnCount(); i++) {
                    if (i > 1) System.out.print(",  ");
                    System.out.print(rmd.getColumnName(i) + ": " + res.getString(i));
                }
                System.out.println();
            }

            connection.close();

        }
        else {
            showUsage();
            System.exit(1);
        }
        System.exit(0);
    }

    private static void showUsage(){
        System.out.println("Positional command-line argument(for sake of code simplicity):");
        System.out.println("java -cp .:/full/path/jdbc.jar Netzzsql <host> <port> <username> <password> <query> <dbname>");
    }
}
