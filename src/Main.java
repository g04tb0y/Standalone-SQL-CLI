import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public void showOptions() {

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
                System.out.println("\n"
                        + "|###########################################");
                //System.out.println("|Object class: "+this.obj.getClass());
                System.out.println("|[+]Type a SQL query or type q to quit:\n"
                        + "|Methods - show alla methods;");
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



    /*
     * Load a JDBC driver jar into the class path. Remember that adding untrusted JAR
     * could lead the program to execute malicious code.
     */
    public static synchronized void loadJDBC(File fjar) {

        if (fjar.length() != 0){

            try {
                URLClassLoader loader = (URLClassLoader)ClassLoader.getSystemClassLoader();
                URL url = fjar.toURI().toURL();

                for (java.net.URL it : Arrays.asList(loader.getURLs())){ //check if it's already loaded
                    if (it.equals(url)){
                        return;
                    }
                }
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
