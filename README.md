# Yournime

**Yournime** is an Android application that allows users to search, explore, and save their favorite anime. Built with a modern **MVVM architecture**, it integrates the [Jikan API](https://jikan.moe/) to fetch anime data from MyAnimeList.

## ✨ Features

- 🔍 Search for anime by title  
- 📄 View detailed anime information  
- ❤️ Add or remove favorites  
- 🗂 Manage your favorite anime list  
- ⚡ Fast and responsive UI  

## 🛠 Tech Stack

- **Kotlin** - Primary programming language  
- **XML** - Used for building user interfaces  
- **MVVM Architecture** - Clean and scalable codebase  
- **Retrofit** - For API and network communication  
- **Room** - For local data persistence 
- **Koin** - Dependency injection frameworks  
- **Coroutines & Flow** - Asynchronous and reactive programming  
- **Material 3** - Modern and consistent UI components  
- **Modularization** - Separation of concerns into multiple modules  

## 📁 Project Structure

```bash
yournime/
├── app/                   # Main Android application module
├── core/                  # Core utilities like network, database, API service, repositories.
├── domain/                # Domain module: use cases, domain models
├── feature_favorite/      # Module for favorite anime feature
