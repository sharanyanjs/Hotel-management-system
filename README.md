# 🏨 Hotel Management System (Java)

A complete **Hotel Reservation & Management System** built with **Java (17+)**, demonstrating **OOP principles**, **design patterns**, and **clean layered architecture**.

---

## 📋 Table of Contents

* [✨ Features](#-features)
* [🏗️ Architecture](#-architecture)
* [🚀 Quick Start](#-quick-start)
* [📁 Project Structure](#-project-structure)
* [🎮 How to Use](#-how-to-use)
* [🔧 Code Examples](#-code-examples)
* [📊 UML Diagrams](#-uml-diagrams)
* [🤝 Contributing](#-contributing)
* [📄 License](#-license)

---

## ✨ Features

### 🎯 Core Functionality

* ✅ **Customer Management** – Create customer accounts with email validation
* ✅ **Room Booking** – Reserve rooms with date conflict prevention
* ✅ **Admin Panel** – Manage rooms, customers, and view all reservations
* ✅ **Search System** – Find available rooms by date range
* ✅ **Reservation History** – View past and current bookings

### ⚡ Technical Highlights

* 🏗️ **Layered Architecture** – Model, Service, API, and UI layers
* 🔄 **Polymorphism** – `IRoom` interface with `Room` / `FreeRoom` implementations
* 🏭 **Design Patterns** – Singleton, Facade, Factory
* 🛡️ **Robust Error Handling** – Validation with graceful recovery
* 📦 **Java Collections** – `HashMap`, `HashSet`, `ArrayList`

### 🌟 Unique Enhancements

* 🌊 **Room Features** – Sea view, balcony, floor tracking
* ⭐ **Room Ratings** – Automatic 1–5 star rating based on amenities
* 🧹 **Cleaning System** – Room status and cleaning priority
* 🎁 **Free Rooms** – Complimentary room category
* 📱 **User-Friendly UI** – Console-based menus with Unicode symbols

---

## 🏗️ Architecture

```
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
│  ┌─────────┐ ┌──────┐ ┌──────┐ ┌────────────┐
│  │Customer │ │IRoom │ │ Room │ │Reservation │
│  └─────────┘ └──────┘ └──────┘ └────────────┘
└─────────────────────────────────────────┘
```

---

## 🚀 Quick Start

### Prerequisites

* ☕ **Java JDK 17+**
* 💻 **IntelliJ IDEA** (recommended) or any Java IDE

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/yourusername/hotel-management-system.git

# 2. Navigate to the project directory
cd hotel-management-system

# 3. Open in IntelliJ IDEA
# File → Open → Select project folder
```

### Running the Application

```java
// Run the main application
HotelApplication.java

// Run test driver
Driver.java
```

---

## 📁 Project Structure

```
hotel-management-system/
├── src/
│   ├── com/hotel/model/          # 🏗️ Data Models
│   │   ├── Customer.java        # 👤 Customer entity
│   │   ├── IRoom.java           # 🏢 Room interface
│   │   ├── Room.java            # 🛌 Regular room
│   │   ├── FreeRoom.java        # 🎁 Complimentary room
│   │   ├── Reservation.java    # 📅 Booking entity
│   │   └── RoomType.java        # 🏷️ Room type enum
│   │
│   ├── com/hotel/service/       # ⚙️ Business Logic
│   │   ├── CustomerService.java
│   │   └── ReservationService.java
│   │
│   ├── com/hotel/api/           # 🔗 API / Facade Layer
│   │   ├── HotelResource.java
│   │   └── AdminResource.java
│   │
│   ├── com/hotel/ui/            # 🖥️ Console UI
│   │   ├── MainMenu.java
│   │   └── AdminMenu.java
│   │
│   ├── Driver.java              # 🧪 Test driver
│   └── HotelApplication.java    # 🚀 Entry point
│
├── screenshots/                 # 📸 Application screenshots
├── README.md                    # 📖 Documentation
└── LICENSE                      # ⚖️ MIT License
```

---

## 🎮 How to Use

### 👤 Customer Flow

```
1. 📝 Create Account
   → Enter name and email

2. 🔍 Find Rooms
   → Select check-in & check-out dates
   → View available rooms

3. 🏨 Book Room
   → Choose room number
   → Confirm reservation

4. 📋 View Reservations
   → Display all current & past bookings
```

### 🛡️ Admin Flow

```
1. 👥 View All Customers
   → List registered users

2. 🏢 View All Rooms
   → Manage room inventory

3. 📊 View All Reservations
   → Monitor hotel bookings

4. ➕ Add New Room
   → Expand hotel capacity
```

---

## 🔧 Code Examples

```java
IRoom room = new Room("101", 120.0, RoomType.DOUBLE);
ReservationService.getInstance().addRoom(room);
```

---

## 📊 UML Diagrams

> UML class and sequence diagrams can be found in the `/screenshots` directory.

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit changes (`git commit -m 'Add new feature'`)
4. Push to branch (`git push origin feature/new-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the **MIT License**.

See the [LICENSE](LICENSE) file for details.

---

⭐ If you find this project helpful, consider giving it a star on GitHub!
