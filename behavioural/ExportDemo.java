package behavioural;

class ReportData {
    private final String title;
    private final String content;

    public ReportData(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}


interface ExportStrategy {
    void export(ReportData data);
}

/**
 * Concrete Strategies that implement different export formats.
 */
class CsvExportStrategy implements ExportStrategy {
    @Override
    public void export(ReportData data) {
        System.out.println("Exporting report '" + data.getTitle() + "' to CSV format.");
        // In a real application, logic to generate a CSV file would go here.
        System.out.println("CSV content: title,content\n\"" + data.getTitle() + "\",\"" + data.getContent() + "\"");
    }
}

class JsonExportStrategy implements ExportStrategy {
    @Override
    public void export(ReportData data) {
        System.out.println("Exporting report '" + data.getTitle() + "' to JSON format.");
        // In a real application, logic for JSON serialization would go here.
        System.out.println("JSON content: {\n  \"title\": \"" + data.getTitle() + "\",\n  \"content\": \"" + data.getContent() + "\"\n}");
    }
}

/**
 * The Context class that uses an export strategy.
 */
class ReportGenerator {
    private ExportStrategy exportStrategy;

    public void setExportStrategy(ExportStrategy strategy) {
        this.exportStrategy = strategy;
    }

    public void generateReport(ReportData data) {
        if (exportStrategy == null) {
            System.out.println("Error: Please select an export format first.");
            return;
        }
        exportStrategy.export(data);
    }
}


public class ExportDemo {
    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGenerator();
        ReportData salesData = new ReportData("Q3 Sales Report", "Total sales reached $500,000.");

        // First, export the report as a CSV file.
        System.out.println("--- User selects CSV export ---");
        reportGenerator.setExportStrategy(new CsvExportStrategy());
        reportGenerator.generateReport(salesData);

        System.out.println("\n----------------------------------\n");

        // Now, the user decides to export the same report as a JSON file.
        System.out.println("--- User selects JSON export ---");
        reportGenerator.setExportStrategy(new JsonExportStrategy());
        reportGenerator.generateReport(salesData);
    }
}