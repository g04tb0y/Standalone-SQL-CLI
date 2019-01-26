import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Properties;
import java.sql.*;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {
	String connStr = "";
	String ip = "";
	int port = 1521;
	String sid = "";
	String usr = "";
	String pwd = "";


	if (args.length == 6) {
	    //Debug only
        Arrays.stream(args).forEach(System.out::println);
        port = Integer.parseInt(args[3]);
        File file = new File(args[4]);
    }
    else {
        showUsage();
        System.exit(1);
    }

    }


    private static void showUsage(){
        System.out.println("Positional command-line argument(for sake of code simplicity):");
        System.out.println("username password ip port jdbcdriver query");
    }

    private static void interactivePrompt() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String opt = null;

        System.out.println("\n"
                + "|###########################################");
        //System.out.println("|Object class: "+this.obj.getClass());
        System.out.println("|[+]Type a SQL query or type q to quit:\n"
                + "|Methods - show alla methods;");
        System.out.print(">");

        try {
            while ((opt = br.readLine()) != null && !opt.equalsIgnoreCase("q"))
            {
                System.out.println("|[+]Type a SQL query or type q to quit:");
                System.out.print(">");

                // Connection to DB
                System.out.print("Connecting to DB: ");
                // If ok run the query
                System.out.print("Executing query: ");

                }


            } catch (IOException e) {

            System.out.println("Error while reading from stdin buffer");
            e.printStackTrace();

        }


        System.out.println("Bye!");
    }


    public void connect(String jdbcpath) {

        try {
            URL u = new URL("jar:file://" + jdbcpath+ "!/";
            URLClassLoader ucl = new URLClassLoader(new URL[] { u });
            String classname = ucl.getClass().getName();
            System.out.println("Driver class name: ");//debug
            Driver d = null;
            d = (Driver)Class.forName(classname, true, ucl).newInstance();
            DriverManager.registerDriver(new DriverShim(d));
            DriverManager.getConnection("jdbc:postgresql:" + database, user, password);
        } catch (InstantiationException |
                SQLException |
                IllegalAccessException |
                ClassNotFoundException |
                MalformedURLException e) {
            e.printStackTrace();
        }

        // Success!
    }



    class DriverShim implements Driver {
        private Driver driver;
        DriverShim(Driver d) {
            this.driver = d;
        }
        public boolean acceptsURL(String u) throws SQLException {
            return this.driver.acceptsURL(u);
        }
        public Connection connect(String u, Properties p) throws SQLException {
            return this.driver.connect(u, p);
        }
        public int getMajorVersion() {
            return this.driver.getMajorVersion();
        }
        public int getMinorVersion() {
            return this.driver.getMinorVersion();
        }
        public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) throws SQLException {
            return this.driver.getPropertyInfo(u, p);
        }
        public boolean jdbcCompliant() {
            return this.driver.jdbcCompliant();
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }
    }

    /*
     * Load a JDBC driver jar into the class path. Remember that adding untrusted JAR
     * could lead the program to execute malicious code.
     */
    public static synchronized void connect(Url u) {

        if (fjar.length() != 0){

            try {

                URLClassLoader ucl = new URLClassLoader(new URL[] { u });


                Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{java.net.URL.class});
                method.setAccessible(true);
                method.invoke(loader, new Object[]{url});

                System.out.println("[OK] JDBC driver loaded!");
            } catch (final java.lang.NoSuchMethodException |
                    java.lang.IllegalAccessException |
                    java.net.MalformedURLException |
                    java.lang.reflect.InvocationTargetException e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("[ERROR] Invalid JAR file!");
        }
    }

}
