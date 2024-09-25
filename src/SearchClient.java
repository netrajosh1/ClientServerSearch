import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

/**
 * Displays titles sent in a drop-down menu
 *
 * <p>Purdue University -- CS18000 -- Fall 2023</p>
 *
 * @author Netra Joshi Purdue CS
 * @version November 10, 2023
 */

public class SearchClient {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome!");
        try {
            String hostName = JOptionPane.showInputDialog("Enter the server hostname (e.g., localhost):");
            int port = Integer.parseInt(JOptionPane.showInputDialog("Enter the server port number:"));

            if (!hostName.equals("localhost")) {
                throw new IOException();
            }
            Socket socket = new Socket(hostName, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            JOptionPane.showMessageDialog(null, "Connection established with the server.");
            int option = 9;
            do {
                try {
                    String searchText = JOptionPane.showInputDialog(null,
                            "Enter your search text: ");
                    out.println(searchText);
                    BufferedReader reader = new BufferedReader(new FileReader("searchDatabase.txt"));
                    String line;
                    ArrayList<String> choices = new ArrayList<String>();
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(";");
                        if (parts.length >= 3) {
                            int num = Integer.parseInt(parts[0].trim());
                            String title = parts[1].trim();
                            if (line.contains(searchText)) {
                                choices.add(title);
                            }
                        }
                    }

                    int[] choicesInt = new int[choices.size()];
                    for (int i = 0; i < choices.size(); i++) {
                        choicesInt[i] = i;
                    }
                    int options;
                    if (choices.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error: no text matching the" +
                                "search text has been processed.");
                    } else {
                        JOptionPane.showMessageDialog(null, choices);

                    }
                    JOptionPane.showMessageDialog(null, "Data has been processed");
                    option = JOptionPane.showConfirmDialog(null,
                            "Would you like to search again?", "Search Text",
                            JOptionPane.YES_NO_OPTION);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }

            } while (option == 0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: Connection not established.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid port number.");
            e.printStackTrace();
        }
    }
}
