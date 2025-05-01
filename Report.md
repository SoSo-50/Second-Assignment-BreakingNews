// File: Report.md
# Breaking News Assignment Report

## Introduction
This project is a simple news aggregator application that fetches real-time news from **NewsAPI** and displays it to the user. The goal of this project is to reinforce object-oriented programming skills, API integration, JSON parsing, and version control with Git.

## Completed Tasks
1. **Project Setup and Git**:
   - Forked and cloned the repository.
   - Created a `development` branch for all development work.

2. **Implemented Infrastructure Class**:
   - Implemented the `fetchNews` method using `HttpClient` to send requests to NewsAPI.
   - Parsed JSON responses using the **Gson** library and converted them into `News` objects.
   - Added error handling for non-200 status codes and unsuccessful API responses.

3. **Designed News Class**:
   - Designed the `News` class with attributes `title`, `description`, `sourceName`, `author`, `url`, and `publishedAt`.
   - Implemented the `displayNews` method to show complete news details.
   - Added getters for accessing attributes for file storage.

4. **Implemented Menu in Main Class**:
   - Created an interactive menu displaying news titles.
   - Allowed users to select an article to view details.
   - Added an option to save articles to favorites.

5. **Error Handling**:
   - Handled errors for failed API requests and invalid user inputs.
   - Replaced null JSON values with default values.

6. **Bonus Task: Save Favorite Articles**:
   - Implemented functionality to save favorite articles to `favorites.txt` and load them on startup.
   - Users can view their saved articles.

## Challenges
- **Working with APIs**: Learning to use `HttpClient` and handle JSON responses was challenging. This was resolved by studying documentation and resources.
- **JSON Parsing**: Handling null values in JSON responses required careful checks. Using `isJsonNull` resolved this issue.
- **File Storage**: Ensuring proper saving and loading of data in the file required careful format design.

## Conclusion
This project provided deep insights into object-oriented programming, API integration, and Git-based project management. Implementing the bonus task enhanced my file handling skills. The code is readable, structured, and well-commented, meeting all project requirements.

## Resources Used
- [Gson Documentation](https://github.com/google/gson)
- [Tutorial on Fetching JSON from APIs](https://www.baeldung.com/java-http-client-json)
- [Guide to Writing READMEs](https://www.makeareadme.com/)
- Official NewsAPI Documentation