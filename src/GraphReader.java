import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
import java.io.IOException;
import java.util.regex.*;

/** @class GraphReader
 *
 * @brief Cette classe permet de lire des fichiers d?crivant des graphes.
 */
public class GraphReader {
	
    private Path filePath;

    public GraphReader(String fileName) {
        filePath = FileSystems.getDefault().getPath(fileName);
    }

    public void read(IGraph graph) {
        List<String> lines;

        try {
            lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier :" + e);
            return ;
        }
        
        Iterator<String> it = lines.iterator();
        while(it.hasNext()) {
            String line = it.next();
            Pattern p1 = Pattern.compile("(\\w+)\\s*\\((\\w+)\\)\\s*-->\\s*(\\w.*)");
            Pattern p2 = Pattern.compile("\\s*(\\w+)\\s*(\\((\\d*)\\))?");

            Matcher matcher = p1.matcher(line);
            boolean b = matcher.matches();
            if (b) {
                if (matcher.groupCount() == 3) {
                    String id = matcher.group(1);
                    String name = matcher.group(2);
                    String successors = matcher.group(3);

                    IVertex v = graph.addVertex(id);
                    v.setName(name);

                    StringTokenizer st = new StringTokenizer(successors,",");
                    while (st.hasMoreTokens()) {
                        String successor = st.nextToken();
                        Matcher m2 = p2.matcher(successor);
                        boolean b2 = m2.matches();
                        if (b2) {
                            String s = m2.group(1);
                            IVertex v2 = graph.addVertex(s);

                            int i = 0;
                            if (m2.group(3) != null) {
                                i = Integer.parseInt(m2.group(3));
                            }
                            v.addNeighbour(v2,i);
                        }
                    }
                }
            }
        }
    }
}
