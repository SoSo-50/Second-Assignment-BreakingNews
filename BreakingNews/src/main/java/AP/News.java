package AP;

public class News {
    private final String title;
    private final String description;
    private final String sourceName;
    private final String author;
    private final String url;
    private final String publishedAt;

    // Constructor
    public News(String title, String description, String sourceName, String author, String url, String publishedAt) {
        this.title = title;
        this.description = description;
        this.sourceName = sourceName;
        this.author = author;
        this.url = url;
        this.publishedAt = publishedAt;
    }

    // Displays news information
    public void displayNews() {
        System.out.println("Title: " + title);
        System.out.println("Source: " + sourceName);
        System.out.println("Author: " + author);
        System.out.println("Published At: " + publishedAt);
        System.out.println("Description: " + description);
        System.out.println("URL: " + url);
        System.out.println("----------------------------------------");
    }

    // Getters for saving to file
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getSourceName() { return sourceName; }
    public String getAuthor() { return author; }
    public String getUrl() { return url; }
    public String getPublishedAt() { return publishedAt; }
}
