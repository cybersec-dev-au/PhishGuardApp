# 🛡️ PhishGuard App

<div align="center">

![PhishGuard Logo](https://img.shields.io/badge/PhishGuard-AI%20Powered-00ff88?style=for-the-badge&logo=android&logoColor=white)
[![Java](https://img.shields.io/badge/Java-100%25-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://www.android.com/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=for-the-badge)](LICENSE)

### 🔍 Real-Time Phishing Detection at Your Fingertips

*An intelligent Android application that leverages NLP and Machine Learning to detect phishing attempts in SMS messages and protect users from cyber threats.*

[Features](#-features) • [Demo](#-demo) • [Installation](#-installation) • [How It Works](#-how-it-works) • [Tech Stack](#-tech-stack) • [Contributing](#-contributing)

</div>

---

## 📱 Overview

**PhishGuard** is a cutting-edge Android security application designed to protect users from phishing attacks in real-time. By analyzing SMS messages using advanced Natural Language Processing (NLP) and Machine Learning algorithms, PhishGuard identifies malicious content and alerts users before they fall victim to scams.

### 🎯 Key Highlights

- ✅ **Real-time SMS scanning** with instant threat detection
- 🤖 **AI-powered analysis** using NLP and TensorFlow/Keras models
- 📊 **Confidence scoring** for risk assessment
- 🔒 **Privacy-focused** - all processing happens locally
- 📱 **Native Android UI** with Material Design
- 🚀 **Flask REST API** backend for ML model serving

---

## ✨ Features

### 🔐 Security Features
- **Intelligent Message Analysis**: Uses TF-IDF vectorization and neural networks to detect phishing patterns
- **Real-time Scanning**: Automatic scanning of incoming SMS messages
- **Risk Assessment**: Provides confidence scores for detected threats
- **Phishing Database**: Maintains history of scanned messages with threat levels

### 📱 User Experience
- **Clean Interface**: Intuitive Material Design UI
- **Instant Alerts**: Immediate notifications for suspicious messages
- **Message History**: View scan history and threat analysis
- **User Feedback**: Option to report false positives/negatives

### 🧠 ML Capabilities
- **NLP Processing**: Advanced text feature extraction
- **Pattern Recognition**: Identifies common phishing tactics
- **Continuous Learning**: Model improvements through user feedback
- **High Accuracy**: Trained on extensive phishing datasets

---

## 🎬 Demo

<div align="center">

### App Screenshots

| Splash Screen | Message Scanner | Threat Detection |
|:---:|:---:|:---:|
| ![Splash](screenshots/splash.png) | ![Scanner](screenshots/scanner.png) | ![Detection](screenshots/detection.png) |

</div>

---

## 🚀 Installation

### Prerequisites

- **Android Studio** Arctic Fox (2020.3.1) or higher
- **JDK** 11 or higher
- **Android SDK** API Level 21+
- **Python 3.8+** (for backend)
- **Flask** and ML dependencies (see backend repo)

### Clone the Repository

```bash
git clone https://github.com/ares-coding/PhishGuardApp.git
cd PhishGuardApp
```

### Build and Run

1. **Open in Android Studio**
   ```bash
   # Open the project folder in Android Studio
   ```

2. **Sync Gradle Dependencies**
   ```
   File → Sync Project with Gradle Files
   ```

3. **Configure Backend Connection**
   - Update the API endpoint in `app/src/main/java/config/ApiConfig.java`
   ```java
   public static final String BASE_URL = "http://your-backend-url:5000";
   ```

4. **Run the App**
   - Connect your Android device or start an emulator
   - Click **Run** (▶️) or press `Shift + F10`

---

## 🔧 How It Works

### Architecture Overview

```
┌─────────────────────────────────────────────────────────┐
│                    ANDROID CLIENT                        │
│  ┌────────────┐  ┌──────────────┐  ┌────────────────┐  │
│  │  SMS       │  │  UI Layer    │  │  Retrofit      │  │
│  │  Receiver  │→ │  (Activity)  │→ │  API Client    │  │
│  └────────────┘  └──────────────┘  └────────────────┘  │
└─────────────────────────────────────────────────────────┘
                           ↓ HTTP Request
┌─────────────────────────────────────────────────────────┐
│                   FLASK REST API                         │
│  ┌────────────┐  ┌──────────────┐  ┌────────────────┐  │
│  │  API       │  │  Preprocessor│  │  ML Model      │  │
│  │  Endpoint  │→ │  (TF-IDF)    │→ │  (Keras/TF)    │  │
│  └────────────┘  └──────────────┘  └────────────────┘  │
└─────────────────────────────────────────────────────────┘
                           ↓ JSON Response
                    {
                      "prediction": "phishing",
                      "confidence": 0.94,
                      "risk_level": "high"
                    }
```

### Detection Process

1. **Message Reception**: App intercepts incoming SMS
2. **Feature Extraction**: Text is preprocessed and vectorized using TF-IDF
3. **API Request**: Processed features sent to Flask backend
4. **ML Inference**: Neural network analyzes patterns
5. **Risk Assessment**: Confidence score calculated
6. **User Notification**: Alert displayed if threat detected
7. **History Logging**: Result stored in local database

---

## 🛠️ Tech Stack

### Frontend (Android)

| Technology | Purpose |
|-----------|---------|
| ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white) | Primary development language |
| ![Android Studio](https://img.shields.io/badge/Android_Studio-3DDC84?style=flat&logo=android-studio&logoColor=white) | IDE and development environment |
| ![Retrofit](https://img.shields.io/badge/Retrofit-009688?style=flat&logo=square&logoColor=white) | REST API client |
| ![Material Design](https://img.shields.io/badge/Material_Design-757575?style=flat&logo=material-design&logoColor=white) | UI/UX components |

### Backend (ML API)

| Technology | Purpose |
|-----------|---------|
| ![Python](https://img.shields.io/badge/Python-3776AB?style=flat&logo=python&logoColor=white) | Backend programming |
| ![Flask](https://img.shields.io/badge/Flask-000000?style=flat&logo=flask&logoColor=white) | REST API framework |
| ![TensorFlow](https://img.shields.io/badge/TensorFlow-FF6F00?style=flat&logo=tensorflow&logoColor=white) | Deep learning framework |
| ![Keras](https://img.shields.io/badge/Keras-D00000?style=flat&logo=keras&logoColor=white) | Neural network API |
| ![Scikit-learn](https://img.shields.io/badge/Scikit--learn-F7931E?style=flat&logo=scikit-learn&logoColor=white) | ML utilities & TF-IDF |

---

## 📂 Project Structure

```
PhishGuardApp/
├── .idea/                    # Android Studio configuration
├── app/                      # Main application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/         # Java source files
│   │   │   │   └── com.phishguard/
│   │   │   │       ├── activities/    # UI Activities
│   │   │   │       ├── adapters/      # RecyclerView Adapters
│   │   │   │       ├── api/           # Retrofit API interfaces
│   │   │   │       ├── models/        # Data models
│   │   │   │       ├── receivers/     # SMS Broadcast Receiver
│   │   │   │       └── utils/         # Helper classes
│   │   │   ├── res/          # Resources
│   │   │   │   ├── drawable/ # Images & icons
│   │   │   │   ├── layout/   # XML layouts
│   │   │   │   ├── values/   # Colors, strings, themes
│   │   │   │   └── ...
│   │   │   └── AndroidManifest.xml
│   │   └── test/             # Unit tests
│   └── build.gradle          # App-level Gradle config
├── gradle/                   # Gradle wrapper
├── .gitignore
├── build.gradle              # Project-level Gradle config
├── gradle.properties
├── gradlew                   # Gradle wrapper script (Unix)
├── gradlew.bat               # Gradle wrapper script (Windows)
├── settings.gradle.kts
└── README.md                 # This file
```

---

## 🔐 Permissions

The app requires the following permissions:

```xml
<!-- Required for SMS scanning -->
<uses-permission android:name="android.permission.RECEIVE_SMS" />
<uses-permission android:name="android.permission.READ_SMS" />

<!-- Required for API communication -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

---

## 🧪 Testing

### Run Unit Tests

```bash
./gradlew test
```

### Run Instrumentation Tests

```bash
./gradlew connectedAndroidTest
```

---

## 🔗 Related Repositories

- **Backend API**: [PhishGuard ML API with Database](https://github.com/ares-coding/phishguard-ml-api-with-database)
- **ML Model Training**: [Phishing Detection Model](https://github.com/ares-coding/phishing-detection-ml)

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

### How to Contribute

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

### Development Guidelines

- Follow Java coding conventions
- Write unit tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR

---

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Au Amores**

- GitHub:  https://github.com/cybersec-dev-au
- Portfolio:
https://au-dev-cs.vercel.app/

---

## 🙏 Acknowledgments

- TensorFlow and Keras teams for the ML framework
- Android development community
- Open-source phishing datasets contributors
- Flask framework developers

---

## 📞 Support

If you encounter any issues or have questions:

1. Check the [Issues](https://github.com/ares-coding/PhishGuardApp/issues) page
2. Create a new issue with detailed description
3. Contact via email: [your-email@example.com]

---

## 🌟 Star History

If you find this project useful, please consider giving it a ⭐!

---

<div align="center">

### 🛡️ Stay Safe from Phishing! 🛡️

Made with Passion and a lot of Coffee by [Ares Coding](https://github.com/ares-coding)

Add Apache 2.0 license

- Add LICENSE file with Apache License 2.0
- Project is now open source under Apache 2.0 terms
- Allows commercial use, modification, and distribution

![Visitors](https://visitor-badge.laobi.icu/badge?page_id=ares-coding.PhishGuardApp)
![Last Commit](https://img.shields.io/github/last-commit/ares-coding/PhishGuardApp?style=flat-square)

</div>
