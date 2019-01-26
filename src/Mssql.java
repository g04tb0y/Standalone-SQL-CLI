import java.util.Arrays;
import java.sql.*;


public class Mssql {

    public static void main(String[] args) {

        if (args.length == 5) {
            System.out.println("Echo params: ");
            Arrays.stream(args).forEach(System.out::println);

            try {
                String connectionString =
                        "jdbc:sqlserver://" + args[0] + ":" + args[1]
                                + "user=" + args[2]
                                + "password=" + args[3]
                                + "trustServerCertificate=true;"
                                + "loginTimeout=30;";

                Connection connection = DriverManager.getConnection(connectionString);
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

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else {
            showUsage();
            System.exit(1);
        }
        System.exit(0);
    }

    private static void showUsage(){
        System.out.println("Positional command-line argument(for sake of code simplicity):");
        System.out.println("java -cp .:/full/path/jdbc.jar Mysql <host> <port> <username> <password> <query>");
    }

}

