import java.io.*;

/**
 * Created by Damian on 2015-01-18.
 */
public class User implements Serializable {
    public String nick;
    public String haslo;

    public User(String nick, String haslo) {
        this.nick = nick;
        this.haslo = haslo;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream("users.txt"));
    }

}
