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


class CsvExportStrategy implements ExportStrategy {
    @Override
    public void export(ReportData data) {
        System.out.println("Exporting report '" + data.getTitle() + "' to CSV format.");
    
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


        System.out.println("--- User selects CSV export ---");
        reportGenerator.setExportStrategy(new CsvExportStrategy());
        reportGenerator.generateReport(salesData);

        System.out.println("\n----------------------------------\n");

        System.out.println("--- User selects JSON export ---");
        reportGenerator.setExportStrategy(new JsonExportStrategy());
        reportGenerator.generateReport(salesData);
    }

}
