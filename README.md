# E-Commerce Android Application

## Project Overview
This is a basic E-commerce Android application developed using Kotlin.
The application allows users to register, login, upload products, view product listings, view detailed product information, and manage a favorites list.

The project is built following modern Android development practices and integrates Firebase services for authentication and data storage.

---

## Features Implemented

### Authentication
- User Registration using Email & Password (Firebase Authentication)
- User Login using Email & Password
- Basic error handling for authentication failures

### Home Screen
- Displays a list of all uploaded products
- Each product shows:
  - Product title
  - Short description
  - Price
- Click on a product opens the Product Details screen

### Product Details
- Full product description
- Product image (via image URL)
- Product price
- Uploader email information
- Add product to Favorites option

### Upload Product
- Users can upload products with:
  - Title
  - Description
  - Price
  - Product image URL
- Product data is stored in Firebase Firestore

### Favorites
- Users can add products to a favorites list
- Favorites are stored locally using Room Database
- Favorites list accessible from the home screen

---

## Architecture & Design
- Language: Kotlin
- Architecture: MVVM (basic implementation)
- UI: XML layouts
- Local Database: Room
- Authentication: Firebase Authentication
- Remote Database: Firebase Firestore

---

## Project Structure
The main application source code is available under:

app/src/

This includes:
- Kotlin source files
- XML layouts
- AndroidManifest.xml

---

## How to Run the Project
1. Clone the repository
2. Open the project in Android Studio
3. Add your own google-services.json file
4. Sync Gradle
5. Run the app on an emulator or physical device

---

## Notes
- Firebase Storage is not used due to free tier limitations.
- Product images are handled using image URLs stored in Firestore.
- Optional features such as search, filters, and recommendations were not implemented as they were optional.

---

## Author
Shivam
