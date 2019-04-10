
This project is a collection of simple command line program written in java,
that should help pentester to access to database when sql client, such as sqlplus etc, are not available on the machine. 
The general idea is to keep the code as simple as possible so it can be deployed easily on the target machine, even through a webshell.

## Usage
There is a stand alone .java file for each of the common DBMS such Oracle, MySQL and so on.
Compile with javac:
```bash
javac *.java
```

The execute preloading the JDBC driver into the Class Path:
```bash
java -cp :/full/path/DBDrivers/ojdbc6.jar Orcl 192.168.138.132 1521 system oracle "select * from dba_users"

```
Separate multiple classpath with ```:```
### Oracle oci driver

LD preload when using the oci8
```
LD_LIBRARY_PATH="/usr/lib/oracle/11.2/client64/lib:/usr/lib64"
```