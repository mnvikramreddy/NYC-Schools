# 🏫 NYC Schools MVVM App

An Android application that displays a list of NYC public schools and their SAT results using a clean MVVM architecture with repository pattern, offline caching, search functionality, and proper dependency injection using Dagger 2.

---

## ✨ Features

- 📚 Fetch list of NYC public schools from a remote API
- 📊 View SAT results of selected school
- 🔍 Search schools by name
- 📦 Offline caching using Room database
- 🔁 Repository pattern as a **Single Source of Truth**
- 🧠 Clean MVVM architecture
- 📐 Jetpack Navigation with Safe Args
- 🔗 Custom BindingAdapters for XML
- ✅ Unit-tested ViewModels and Repository

---

## 🧱 Architecture

```plaintext
Clean MVVM Architecture
+ Repository Pattern (single source of truth)
+ Dagger 2 for Dependency Injection
+ ViewModel + LiveData
+ Room DB for local cache
+ Retrofit for networking
````

---

## 🧩 Tech Stack

| Layer         | Technologies                                     |
| ------------- | ------------------------------------------------ |
| View          | XML Layouts + DataBinding + Navigation Component |
| ViewModel     | AndroidX ViewModel, LiveData                     |
| Model         | POJOs + Room Entities                            |
| DI            | Dagger 2                                         |
| Local Storage | Room Database                                    |
| Remote Data   | Retrofit with Coroutine support                  |
| UI Updates    | Custom BindingAdapters                           |
| Navigation    | Jetpack Navigation Component with Safe Args      |
| Testing       | JUnit, Mockito (ViewModel & Repository testing)  |

---

## 🏗️ Project Structure

```
📁 data/
    ├── local/ (Room DB, DAO)
    ├── model/ (Entities and DTOs)
    ├── remote/ (Retrofit API interface)
    └── repository/ (MainRepository)

📁 di/
    └── AppComponent.kt
    └── Modules.kt

📁 ui/
    ├── schoollist/ (SchoolListFragment, ViewModel)
    └── schooldetails/ (DetailsFragment, ViewModel)

📁 utils/
    └── BindingAdapters.kt
    └── NetworkUtils.kt

📁 test/
    └── unit/ (ViewModel + Repository Unit Tests)
```

---

## 🔍 Search Functionality

Search is implemented using `SearchView` with filtering logic in the ViewModel.
Filtering updates a LiveData-backed school list, enabling real-time search on the RecyclerView.

---

## ✅ Unit Testing

Tests included for:

* ViewModel logic (e.g., loading and filtering schools)
* Repository behavior (e.g., choosing local vs remote source)

```kotlin
@Test
fun `repository returns cached data if available`() = runTest {
    whenever(localDataSource.getSchools()).thenReturn(cachedSchools)
    val result = repository.getSchools()
    assertEquals(cachedSchools, result)
}
```

---

## 🚀 How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/nyc-schools-app.git
   ```

2. Open in Android Studio

3. Build and run on an emulator or device with internet access

---

