# Video Upload and Viewing App

## Overview
This project is a Java-based desktop application for uploading and viewing videos, developed as part of the **Software Development Technology** course in the **Computer Engineering** program at the **University of Murcia**.

This project was developed by **Joselu2099** and **MengChen. Huang**.

## Features
- User authentication and session management
- Video upload with metadata storage
- Video playback functionality
- Video categorization and search
- Commenting and interaction features
- Graphical User Interface (GUI) built with Swing
- PDF generation for playlists

## Technologies Used
- **Java** (Core application development)
- **Java Swing** (For GUI development)
- **H2 Database** (Using `ServidorPersistenciaH2.jar` for persistence)
- **JDBC** (For database interaction)
- **Apache Commons Codec** (For password encryption)
- **iTextPDF** (For generating PDF reports)
- **FlatLaf** (For modern UI styling)
- **JTattoo** (For additional UI themes)
- **Jakarta XML Binding (JAXB)** (For handling XML files)

## Installation & Setup
### Prerequisites
- Java 17+
- H2 Database (`ServidorPersistenciaH2.jar` included in the project)
- FFmpeg installed and configured

### Steps to Run the Project
1. Clone the repository:
   ```sh
   git clone https://github.com/Joselu2099/TDS-AppVideo.git
   cd TDS-AppVideo
   ```
2. Ensure `ServidorPersistenciaH2.jar` is available in the `lib` directory.
3. Compile and run the application:
   ```sh
   mvn clean install
   mvn exec:java -Dexec.mainClass="controller.AppVideo"
   ```

## Usage
1. Launch the application.
2. Log in or create a new user account.
3. Upload videos from your local system.
4. Browse, search, and play uploaded videos.
5. Manage playlists and generate PDFs with their contents.
6. Switch themes using FlatLaf and JTattoo.

## Contribution
Feel free to contribute by submitting pull requests or reporting issues.

## License
This project is licensed under the MIT License.

## Authors
Developed by **Joselu2099** and **MengChen. Huang** as part of the **Software Development Technology** course at the **University of Murcia**.

