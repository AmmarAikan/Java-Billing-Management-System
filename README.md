# Billing Management System

A Java Swing billing application created as a NetBeans Ant project. It manages buyers and products, calculates invoices, and exports bills as PDF files.

## Project layout

- `Billing Managment System/` - Java and NetBeans project files.
- `jar_files/` - libraries required by the existing Ant project.
- `Bms Icon Jframe/` - user-interface image assets.

## Requirements

- Java 8 or newer
- NetBeans with the Absolute Layout library
- MySQL

## Database configuration

The application reads its database settings from environment variables so credentials are not committed to Git:

```text
BMS_DB_URL=jdbc:mysql://localhost:6666/bms
BMS_DB_USER=root
BMS_DB_PASSWORD=your_password
```

`BMS_DB_URL` and `BMS_DB_USER` have the defaults shown above. If `BMS_DB_PASSWORD` is not set, the desktop application asks for the MySQL password when it first connects. The entered password stays in memory only for the current application session.

Generated PDF bills are saved under `BMS Sales` in the current user's home folder. Set `BMS_SALES_DIR` to choose another folder.
