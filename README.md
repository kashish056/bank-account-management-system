# Object-Oriented Banking System Project

This repository contains the project for ** Object-Oriented Engineering Analysis and Design**, completed at Toronto Metropolitan University. The project implements a banking system using object-oriented programming principles in Java.

---


---

## 📘 Project Overview

This Java-based banking system simulates basic financial operations using object-oriented design. The system includes role-based features for **Managers** and **Customers**, and supports deposit, withdrawal, and online purchases with behavior customized according to account type (**Gold** or **Platinum**).

The system uses the **State Design Pattern** to dynamically switch behaviors depending on the account's current balance tier.

---

## 🔄 Flow of Events

1. **Manager Authentication**  
   - Can add/delete customers  
   - Can view customer details  
   - Logout option  

2. **Customer Authentication**  
   - Make deposits and withdrawals  
   - Perform online purchases  
   - Logout after completing transactions  

3. **Transaction Verification**  
   - Withdrawals are blocked if the balance is insufficient  
   - Online purchase fees depend on account type (Gold or Platinum)  

4. **Dynamic Account State**  
   - Balance changes can trigger account upgrades/downgrades between tiers  

---

## 🧱 Class Structure

| Class | Purpose |
|-------|---------|
| `BankAccount` | Stores balance and manages account state |
| `AccountState` (interface) | Declares behavior for different account types |
| `Gold`, `Platinum` | Implement `AccountState` with tier-specific rules and charges |
| `Customer` | Holds a bank account and triggers account operations |
| `Manager` | Adds/deletes customer accounts (if implemented) |
| `BankingApplication`, `AlertBox` (GUI) | Optional classes for managing UI and alerts |

---

## 📚 Subjects & Concepts Learned

To successfully design and implement this system, the following subjects were studied and applied:

### 🧠 Object-Oriented Programming
- Classes & Objects  
- Inheritance & Interfaces  
- Polymorphism & Encapsulation  
- Static vs Instance methods

### 🧩 Software Engineering & Design
- Software Development Life Cycle (SDLC)  
- Modular software architecture  
- Clean code and separation of concerns  
- Requirement specification and analysis

### 🖼 UML Modeling
- Use Case Diagrams  
- Class Diagrams  
- Behavioral modeling of system interaction

### 🎯 Design Patterns
- **State Pattern**: Used for switching behavior between Gold and Platinum accounts based on balance thresholds

### 💻 Java Programming
- Java syntax and data types  
- GUI programming using JavaFX or Swing (if applicable)  
- Exception handling and logic control  
- File structure and multi-class project organization

### 🧪 Testing and Debugging
- Manual input/output testing  
- Code-based validation and bug tracking

### 🔁 Git & Version Control
- Version tracking via GitHub  
- Project structure sharing and documentation

---

## 🧪 UML Snapshot (Optional)

> *(Add this if you have a diagram in your project folder)*

![UML Class Diagram](diagrams/UML_class_diagram.png)

---

## 🚀 How to Run

1. Compile all `.java` files inside the `src/` folder.
2. If you are using an IDE (like IntelliJ, Eclipse), create a new project and import the source files.
3. Run the `BankingApplication` class or any main class provided.
4. Interact with the system via command-line or GUI interface (depending on your implementation).

---

## 📎 References

- TMU Project Manual for COE 528  
- _Introduction to Java Programming_ (Comprehensive Version, 10th Edition) by Y. Daniel Liang  
- Lecture Notes: Modeling with UML (Toronto Metropolitan University)  

---

## 📌 License

This project is for academic purposes only and is part of coursework at Toronto Metropolitan University.  
Do not copy or redistribute without permission.

---

## 🌐 Repository Info

- 📁 Repo Name: `coe528-banking-system-java`  
- 📝 Description: `Java-based object-oriented banking system project for COE528 at TMU`

