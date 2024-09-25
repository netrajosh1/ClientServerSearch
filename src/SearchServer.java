import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads database
 *
 * <p>Purdue University -- CS18000 -- Fall 2023</p>
 *
 * @author Netra Joshi Purdue CS
 * @version November 2, 2023
 */

public class SearchServer {
    private static int port = 1234;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            List<String> database = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader("searchDatabase.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    database.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from: " + clientSocket.getInetAddress());
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);

                    String searchText = br.readLine();
                    System.out.println("Received search request: " + searchText);

                    List<String> searchResults = new ArrayList<>();
                    for (String entry : database) {
                        String[] parts = entry.split(";");
                        if (parts.length >= 2) {
                            String title = parts[1];
                            String description = parts.length > 2 ? parts[2] : "";
                            if (title.toLowerCase().contains(searchText.toLowerCase()) ||
                                    description.toLowerCase().contains(searchText.toLowerCase())) {
                                searchResults.add(title);
                            }
                        }
                    }
                    for (String result : searchResults) {
                        pw.println(result);
                    }

                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
