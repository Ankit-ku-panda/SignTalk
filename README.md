📄 SignTalk – Real-Time Sign Language Detection App
📌 Project Overview

SignTalk is an Android-based mobile application that detects real-time hand gestures representing sign language letters (A–Z) and converts them into readable text using Artificial Intelligence and Computer Vision.

The app uses the device camera and Google MediaPipe Hand Landmarker to recognize hand landmarks and interpret sign language gestures instantly.

This project is developed as a Final Year Engineering Project to assist communication between deaf-mute individuals and normal users.

🎯 Objectives

• Detect real-time hand gestures using mobile camera
• Recognize sign language letters (A–Z)
• Convert gestures into readable text
• Form words and sentences
• Provide real-time translation
• Assist deaf and mute communication

🧠 Technologies Used
Technology	Purpose
Java	Android App Development
Android Studio	Development IDE
MediaPipe	Hand Gesture Detection
CameraX	Camera Integration
Machine Learning	Gesture Recognition
XML	UI Design
📱 Features

✅ Real-time hand detection
✅ A–Z letter recognition
✅ Word formation
✅ Live camera preview
✅ Fast and lightweight
✅ Works offline
✅ User-friendly interface

📷 How It Works

User opens the app

Clicks "Open Camera"

Camera detects hand

MediaPipe identifies hand landmarks

App converts gesture to letter

Letters form words

Text displayed on screen

🗂 Project Structure
SignTalk
│
├── app
│   ├── java/com/example/signtalk
│   │      ├── MainActivity.java
│   │      ├── CameraActivity.java
│   │      └── EmojiInterpreter.java
│   │
│   ├── res
│   │      ├── layout
│   │      └── drawable
│   │
│   ├── assets
│   │      └── hand_landmarker.task
│
└── README.md

⚙ Installation Guide
Step 1: Install APK

Copy:

app-release.apk


to your Android phone and install.

Step 2: Grant Camera Permission

Allow camera access when asked.

Step 3: Use App

Open app
Click:

Open Camera


Show hand gesture.

💻 Development Requirements

• Android Studio Hedgehog or later
• Java JDK 11
• Android SDK 33+
• Android Phone (Recommended)

🤖 Machine Learning Model

Model Used:

MediaPipe Hand Landmarker


File:

hand_landmarker.task


Location:

app/src/main/assets/

📊 Expected Output

Example:

Gesture → Output

✊ → A
✋ → B
👌 → OK

Word Example:

A + B + C → ABC

🎓 Academic Use

This project is submitted as:

Final Year Engineering Project

Branch:

Computer Science / Information Technology

🚀 Future Improvements

• Detect full sign language words
• Add speech output
• Add sentence prediction
• Add multi-language support
• Improve accuracy

👨‍💻 Author

Name: Your Name
College: Your College Name
Year: Final Year
Project Name: SignTalk

📜 License

This project is for educational purposes only.

🙏 Acknowledgment

Special thanks to:

Google MediaPipe
Android Studio
Open Source Community

⭐ Optional (Recommended)

Also create:

PROJECT_REPORT.docx


and

PRESENTATION.pptx
