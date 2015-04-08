import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class watek implements Runnable {

    JTextArea tPole = new JTextArea();

    public watek(JTextArea tPole) {
        this.tPole = tPole;
    }

    @Override
    public void run() {

        String s = tPole.getText();
        String[] words = s.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
        }

        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String word : words) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, count + 1);
            }
        }
        int occurences = Collections.frequency(lst, "Ram");
        System.out.println(occurences);
        System.out.println(counts);
        JOptionPane.showMessageDialog(null, counts, "Zliczacz", JOptionPane.INFORMATION_MESSAGE);

    }

}
