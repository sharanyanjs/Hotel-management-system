🏨 Hotel Management System - Java
A complete hotel reservation system built with Java OOP, design patterns, and professional architecture

📋 Table of Contents
✨ Features

🏗️ Architecture

🚀 Quick Start

📁 Project Structure

🎮 How to Use

🔧 Code Examples

📊 UML Diagrams

🤝 Contributing

📄 License

✨ Features
🎯 Core Functionality
✅ Customer Management - Create accounts with email validation

✅ Room Booking - Reserve rooms with date conflict prevention

✅ Admin Panel - Manage rooms, customers, and view all reservations

✅ Search System - Find available rooms by date range

✅ Reservation History - View past and current bookings

⚡ Technical Highlights
🏗️ Layered Architecture - Model, Service, API, UI layers

🔄 Polymorphism - IRoom interface with Room/FreeRoom implementations

🏭 Design Patterns - Singleton, Facade, Factory patterns

🛡️ Error Handling - Comprehensive validation and graceful error recovery

📦 Collections - Efficient data storage with HashMap, HashSet, ArrayList

🌟 Unique Enhancements
🌊 Room Features - Sea view, balcony, floor tracking

⭐ Room Ratings - Automatic 1-5 star rating based on amenities

🧹 Cleaning System - Room status and cleaning priority

🎁 Free Rooms - Special complimentary room category

📱 User-Friendly UI - Intuitive console menus with Unicode symbols

🏗️ Architecture
text
┌─────────────────────────────────────────┐
│            PRESENTATION LAYER           │
│  ┌────────────┐     ┌────────────┐    │
│  │  MainMenu  │     │ AdminMenu  │    │
│  │ (Customer) │     │  (Admin)   │    │
│  └────────────┘     └────────────┘    │
└─────────────────────────────────────────┘
                    │
┌─────────────────────────────────────────┐
│             API LAYER (Facade)          │
│  ┌────────────┐     ┌────────────┐    │
│  │HotelResource│    │AdminResource│    │
│  └────────────┘     └────────────┘    │
└─────────────────────────────────────────┘
                    │
┌─────────────────────────────────────────┐
│            SERVICE LAYER                │
│  ┌────────────┐     ┌────────────┐    │
│  │ Customer   │     │Reservation │    │
│  │  Service   │     │  Service   │    │
│  │ (Singleton)│     │ (Singleton)│    │
│  └────────────┘     └────────────┘    │
└─────────────────────────────────────────┘
                    │
┌─────────────────────────────────────────┐
│            MODEL LAYER                  │
│  ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐     │
│  │Customer││ IRoom ││ Room ││Reservation│
│  └─────┘ └─────┘ └─────┘ └─────┘     │
└─────────────────────────────────────────┘
🚀 Quick Start
Prerequisites
☕ Java JDK 17+ Download here

💻 IntelliJ IDEA (Recommended) or any Java IDE

Installation
bash
# 1. Clone the repository
git clone https://github.com/yourusername/hotel-management-system.git

# 2. Navigate to project
cd hotel-management-system

# 3. Open in IntelliJ IDEA
#    File → Open → Select project folder
Running the Application
java
// Run the main application
Run → HotelApplication.java

// Or run tests
Run → Driver.java
📁 Project Structure
text
hotel-management-system/
├── 📂 src/
│   ├── 📂 com/hotel/model/          # 🏗️ Data Models
│   │   ├── Customer.java           # 👤 Customer entity
│   │   ├── IRoom.java              # 🏢 Room interface
│   │   ├── Room.java               # 🛌 Regular room
│   │   ├── FreeRoom.java           # 🎁 Complimentary room
│   │   ├── Reservation.java         # 📅 Booking entity
│   │   └── RoomType.java           # 🏷️ Room type enum
│   │
│   ├── 📂 com/hotel/service/       # ⚙️ Business Logic
│   │   ├── CustomerService.java    # 👥 Customer management
│   │   └── ReservationService.java # 🏨 Reservation logic
│   │
│   ├── 📂 com/hotel/api/           # 🔗 API Layer
│   │   ├── HotelResource.java      # 🎯 Customer APIs
│   │   └── AdminResource.java      # 🛡️ Admin APIs
│   │
│   ├── 📂 com/hotel/ui/            # 🖥️ User Interface
│   │   ├── MainMenu.java           # 👤 Customer menu
│   │   └── AdminMenu.java          # 🛡️ Admin menu
│   │
│   ├── Driver.java                 # 🧪 Test suite
│   └── HotelApplication.java       # 🚀 Main entry point
│
├── 📂 screenshots/                 # 📸 Application screenshots
├── README.md                       # 📖 This file
└── LICENSE                         # ⚖️ MIT License
🎮 How to Use
👤 Customer Flow
text
1. 📝 Create Account
   → Enter name and email
   
2. 🔍 Find Rooms
   → Select check-in/out dates
   → View available rooms
   
3. 🏨 Book Room
   → Choose room number
   → Confirm booking
   
4. 📋 View Reservations
   → See all your bookings
🛡️ Admin Flow
text
1. 👥 View All Customers
   → See registered users
   
2. 🏢 View All Rooms
   → Manage room inventory
   
3. 📊 View All Reservations
   → Monitor hotel bookings
   
4. ➕ Add New Room
   → Expand hotel capacity
