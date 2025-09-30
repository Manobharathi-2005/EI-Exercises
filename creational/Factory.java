package creational;

interface Document {
    void open();
    void close();
}


class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a Word document.");
    }
    @Override
    public void close() {
        System.out.println("Closing the Word document.");
    }
}

class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a PDF document.");
    }
    @Override
    public void close() {
        System.out.println("Closing the PDF document.");
    }
}

// The Factory class that creates document objects.
class DocumentFactory {
    public static Document createDocument(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        switch (type.toLowerCase()) {
            case "word":
                return new WordDocument();
            case "pdf":
                return new PdfDocument();
            default:
                throw new IllegalArgumentException("Unknown document type: " + type);
        }
    }
}


public class Factory{
    public static void main(String[] args) {

        Document myWordDoc = DocumentFactory.createDocument("word");
        myWordDoc.open();
        myWordDoc.close();

        System.out.println("---");


        Document myPdfDoc = DocumentFactory.createDocument("pdf");
        myPdfDoc.open();
        myPdfDoc.close();
    }
}