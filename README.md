# Welcome to AfDOC! 🚀

> "Empowering Developers with Seamless Collaboration"

AfDOC, short for **Application for Developers Organization Collaboration**, is a cross-platform Windows and Mobile application designed to streamline collaborative projects among developers. It integrates essential features such as real-time chat, file sharing, video conferencing, scheduling, and version control to make teamwork effortless and efficient.

---

## Features Overview ✨

### 1. Real-time Chat 💬
- **Powered by Kafka & Reactive MongoDB:** Enjoy lightning-fast messaging with permanent chat log storage.
- **Flexible Storage Options:**
    - **Online Server Storage:** Automatically save complete chat histories to a central server when specific conditions are met.
    - **Local Storage:** Save chat logs locally on your device—or even on group members' devices—for immediate, real-time access.
    - **Server Integration:** Configure your own server to store chat logs, giving you complete control over your data.

### 2. File Sharing 📁
- **Universal File Support:** Store and manage all file types with customizable permissions using Minio (S3 API).
- **Advanced Options:**
    - **Encrypt/Sign Files:** Choose to encrypt or sign selected files (or all files). Encryption uses local keys for locking/unlocking, ensuring that only authorized team members with the key file can access the content.
    - **Server-Specific Storage:** Direct file storage to a designated server to boost security—ideal for safeguarding files within an internal network.
    - **Edit Suggestions & Version Control:** Allow authorized users to propose modifications. File owners can review and approve these requests, with an undo log available for recovery. This feature is optimized for compact document updates over full-scale Git version control.

### 3. Real-time Video Conferencing & Scheduling 🎥🗓
- **Interactive Video Meetings:** Host live video conferences with team members who have the required permissions. Engage in diverse, interactive sessions that facilitate efficient collaboration.
- **Integrated Scheduling:** Manage and notify team members about upcoming meetings and events. With mobile app integration, notifications can be customized per recipient, ensuring everyone stays informed.

### 4. Document Editor & Bulletin Board 📝
- **Dynamic Bulletin Boards:** Group administrators can create multiple boards to share API documentation, project updates, and more.
- **Markdown Support:** Write and format documents in Markdown, complete with internal links to other boards or documents for easy navigation.

### 5. API Testing 🔧
- **Customizable Testing Environment:**
    - **Network Flexibility:** Choose to execute tests locally or on a server, depending on your needs.
    - **Language-Based Automation:** Automate API tests using your preferred programming language.
    - **GUI Automation:** Set up and run test flows through an intuitive graphical interface.

---

*More detailed documentation and a complete table of contents for each section will be available soon!*

---

## Getting Started 🚀

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/AfDOC.git
