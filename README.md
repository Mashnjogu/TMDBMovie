# TMDBMovie
This is an android app that fetches and displays movies and tv shows from TMDB API. The app also provides information regarding the movie or show, the cast and movies similar to the chosen film.

Minsdk: 24

# Design Pattern
The app is built with the Model-View-ViewModel (MVVM) is its structural design pattern that separates objects into three distinct groups:
- View: The view is responsible for defining the structure, layout, and appearance of what the user sees on screen.
- Model: Model classes are non-visual classes that encapsulate the app's data.
- ViewModel: The view model implements properties and commands to which the view can data bind to, and notifies the view of any state changes through change notification events.

# Libraries
- Retrofit: Retrofit is a REST client for Java/ Kotlin and Android by Square inc under Apache 2.0 license. Its a simple network library that is used for network transactions. By using this library we can seamlessly capture JSON response from web service/web API
- Gson: JSON Parser,used to parse requests on the data layer for Entities and understands Kotlin non-nullable and default parameters.
- Dagger Hilt:  A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
- Okhttp: OkHttp is an HTTP client from Square for Java and Android applications. It’s designed to load resources faster and save bandwidth. OkHttp is widely used in open-source projects and is the backbone of libraries like Retrofit, Picasso, and many others
- kens burns effect: the addition of an animated transition to a digital photograph in order to create the illusion of movement.
- Coil: an image loading library backed by Kotlin Coroutines
- Room database: he Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

# Screenshots
Solarized dark             |  Solarized Ocean
:-------------------------:|:-------------------------:
![HomeScreen](https://github.com/Mashnjogu/TMDBMovie/assets/45306598/68d85a57-a040-47fb-bb92-eb7ac25c9713)  |  ![TvShowDetails](https://github.com/Mashnjogu/TMDBMovie/assets/45306598/1775b7c3-6199-42d7-9324-d40e049228ea)

