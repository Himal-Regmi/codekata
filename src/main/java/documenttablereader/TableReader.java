package documenttablereader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TableReader {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Please input a file name");
            System.exit(1);
        }

        printSecretMessageFromDocUrl(args[0]);
    }

    public static void printSecretMessageFromDocUrl(String docUrl){
        try{
            Element table = DocumentUtil.getTableElementFromDocument(docUrl);

            if (table == null){
                System.err.println("Table element not found");
                System.exit(1);
            }

            char[][] secretMessageGrid = getSecretMessageGrid(table);

            if (secretMessageGrid == null){
                System.err.println("Secret Message Grid not found");
                System.exit(1);
            }

            for (char[] row : secretMessageGrid) {
                System.out.println(row);
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }

    public static char[][] getSecretMessageGrid(Element table) {
        Elements tableRows = table.getElementsByTag("tr");
        ArrayList<DataPoint> dataPoints = new ArrayList<>();

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int i = 1; i < tableRows.size(); i++) {
            Element tableRow = tableRows.get(i);
            Elements tableCells = tableRow.getElementsByTag("td");

            try {
                int x  = Integer.parseInt(tableCells.get(0).text().trim());
                int y = Integer.parseInt(tableCells.get(2).text().trim());
                char ch = tableCells.get(1).text().charAt(0);

                if (x < minX) minX = x;
                if (x > maxX) maxX = x;
                if (y < minY) minY = y;
                if (y > maxY) maxY = y;

                dataPoints.add(new DataPoint(x, y,ch));
            }  catch (NumberFormatException ignored) {
            }
        }

        if  (dataPoints.isEmpty()) {
            return null;
        }

        int numRows = maxY - minY + 1;
        int numCols = maxX - minX + 1;

        char[][] secretMessageGrid = new char[numRows][numCols];

        for (char[] row : secretMessageGrid) {
            Arrays.fill(row, ' ');
        }

        for (DataPoint dataPoint : dataPoints) {
            int row = dataPoint.y;
            int col = dataPoint.x;
            secretMessageGrid[maxY - row][col] = dataPoint.ch;
        }

        return secretMessageGrid;
    }
}
