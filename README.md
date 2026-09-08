# Billing Management System

A Java Swing billing application created as a NetBeans Ant project. It manages buyers and products, calculates invoices, and exports bills as PDF files.

## Project layout

- `Billing Managment System/` - Java and NetBeans project files.
- `jar_files/` - libraries required by the existing Ant project.
- `Bms Icon Jframe/` - user-interface image assets.

The existing application directory contains the final interface, icons, NetBeans forms, and welcome sound. There is one application in this repository.

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

Database settings can also be supplied as Java system properties (`-DBMS_DB_URL=...`, for example). System properties take precedence over environment variables. Keep real passwords out of shared commands and files. No database dump or customer data is included.

## Build and run

Open `Billing Managment System/` in NetBeans, resolve the Absolute Layout library if requested, and choose **Clean and Build**, then **Run Project**. The main class is `Login`.

For a command-line build, supply the library locations from your NetBeans installation. This PowerShell example uses a placeholder installation directory:

```powershell
$netbeansDir = 'C:\path\to\netbeans'
& "$netbeansDir\extide\ant\bin\ant.bat" `
  "-Dlibs.absolutelayout.classpath=$netbeansDir\java\modules\ext\AbsoluteLayout.jar" `
  "-Dlibs.CopyLibs.classpath=$netbeansDir\java\ant\extra\org-netbeans-modules-java-j2seproject-copylibstask.jar" `
  -f 'Billing Managment System\build.xml' jar

java -jar 'Billing Managment System\dist\Billing_Managment_System.jar'
```

Keep the generated `dist/lib/` directory beside the JAR when copying a local build. Build output is excluded from Git. The welcome sound loads from `/Sound/welcom.wav` inside the JAR; an unavailable audio device does not prevent the home screen from opening.

The existing demonstration login is `bms` / `admin`. It is separate from the MySQL credentials and is not a production authentication system. Database operations require your existing compatible `bms` database; the application does not create its schema automatically.

## Publication exclusions

`.gitignore` excludes database notes (`DB.txt`), environment files, SQL dumps, private NetBeans settings, generated PDFs, and compiled output. The three existing dependency JARs remain in `jar_files/` with relative project paths. Source files and UI resources retain the final version's designs.
