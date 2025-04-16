package AP;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FAVORITES_FILE = "favorites.txt";
    private static List<News> favorites = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your API key: ");
        String apiKey = scanner.nextLine();

        Infrastructure infrastructure = new Infrastructure(apiKey);
        List<News> newsList = new ArrayList<>();

        // Load favorite articles from file
        loadFavorites();

        while (true) {
            System.out.println("\nNews Menu:");
            System.out.println("1. Fetch new articles");
            System.out.println("2. View favorite articles");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidInput(scanner);

            if (choice == 1) {
                try {
                    newsList = infrastructure.fetchNews();
                    displayNewsMenu(newsList, scanner);
                } catch (IOException | InterruptedException e) {
                    System.out.println("Error fetching news: " + e.getMessage());
                }
            } else if (choice == 2) {
                displayFavoritesMenu(scanner);
            } else if (choice == 3) {
                System.out.println("Exiting program...");
                break;
            } else {
                System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }

    // Gets valid input from the user
    private static int getValidInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Displays news menu and handles selections
    private static void displayNewsMenu(List<News> newsList, Scanner scanner) {
        while (true) {
            System.out.println("\nNews List:");
            for (int i = 0; i < newsList.size(); i++) {
                System.out.println((i + 1) + ". " + newsList.get(i).getTitle());
            }
            System.out.println("0. Return to main menu");
            System.out.print("Select a news article for details: ");

            int choice = getValidInput(scanner);

            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= newsList.size()) {
                News selectedNews = newsList.get(choice - 1);
                selectedNews.displayNews();
                System.out.print("Would you like to add this article to favorites? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("yes")) {
                    favorites.add(selectedNews);
                    saveFavorites();
                    System.out.println("Article added to favorites!");
                }
            } else {
                System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Displays favorites menu
    private static void displayFavoritesMenu(Scanner scanner) {
        if (favorites.isEmpty()) {
            System.out.println("No favorite articles found.");
            return;
        }

        while (true) {
            System.out.println("\nFavorite Articles:");
            for (int i = 0; i < favorites.size(); i++) {
                System.out.println((i + 1) + ". " + favorites.get(i).getTitle());
            }
            System.out.println("0. Return to main menu");
            System.out.print("Select an article for details: ");

            int choice = getValidInput(scanner);

            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= favorites.size()) {
                favorites.get(choice - 1).displayNews();
            } else {
                System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Saves favorite articles to file
    private static void saveFavorites() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FAVORITES_FILE))) {
            for (News news : favorites) {
                writer.write(String.format("%s|%s|%s|%s|%s|%s%n",
                        news.getTitle(), news.getDescription(), news.getSourceName(),
                        news.getAuthor(), news.getUrl(), news.getPublishedAt()));
            }
        } catch (IOException e) {
            System.out.println("Error saving favorites: " + e.getMessage());
        }
    }

    // Loads favorite articles from file
    private static void loadFavorites() {
        File file = new File(FAVORITES_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);
                if (parts.length == 6) {
                    favorites.add(new News(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading favorites: " + e.getMessage());
        }
    }
}

