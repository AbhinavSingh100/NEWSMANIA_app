# NEWSMANIA - NEWS READING APP
## Overview
The News Reading App is an Android application built using Java and XML that allows users to read the latest news from various sources. The app aggregates news articles using a news API and displays them in an intuitive and user-friendly interface.

## Features
- Browse the latest news articles
- Filter news by categories (e.g., Technology, Sports, Business)
- Responsive and clean UI
- Fast loading with Android Volley for network requests

## Tech Stack
- **Programming Language:** Java
- **UI Design:** XML
- **Networking:** Android Volley
- **Data Source:** News API

## Architecture
The app follows the **Model-View-Controller (MVC)** architectural pattern:

- **Model:** The `NewsArticle` class represents the model, holding the data for each news article. It contains properties such as title, content, image URL, and other relevant attributes.

- **View:** The view is implemented through the `MainActivity` and `NewsPage` classes. These components are responsible for displaying the news articles and providing the user interface, allowing users to interact with the app.

- **Controller:** The `NewsAdapter` class serves as the controller, mediating between the model and the view. It takes the data from the `NewsArticle` model and binds it to the view, typically through a `RecyclerView` in the `MainActivity`. This separation of concerns facilitates easier maintenance and testing of the application.


## Getting Started
To get a local copy of the project, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/AbhinavSingh100/NEWSMANIA_app.git

2. Open the project in Android Studio.
3. Ensure you have the necessary dependencies in your `build.gradle` file for Android Volley and any other libraries used.
4. Replace the API key in the source code to access the news API.
5. Run the app on an Android device or emulator.

## Challenges Faced
- Optimizing network requests to handle slow connections.
- Implementing data caching for improved performance.
- Designing a user-friendly interface that presents information clearly.


## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgements
- News API for providing news data. https://github.com/KwabenBerko/News-API-Java 
- Android Volley for efficient network handling.

