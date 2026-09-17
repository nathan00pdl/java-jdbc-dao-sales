# Sales DAO — Java with JDBC

[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/17/)
[![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![License](https://img.shields.io/github/license/nathan00pdl/java-jdbc-dao-sales)](LICENSE)

A sellers and departments system built with **plain Java and JDBC**, no framework. Database access goes through the **DAO pattern**, with hand-written SQL and `PreparedStatement`.

Built while following the Udemy course *"COMPLETE Java 2023 Object-Oriented Programming + Projects"*, by Nélio Alves. This was my first in-depth contact with JDBC, the API that connects a Java program to a relational database — the layer that frameworks like Hibernate hide behind an ORM.

> **Note:** this is a study project from before I started using frameworks. It is a console application: there is no Spring, no Maven, no REST API.

## Tech stack

- **Java 17**
- **JDBC** (`java.sql`) — connection handling, `PreparedStatement`, `ResultSet` and generated keys
- **MySQL**
- **Eclipse** project (`.classpath` / `.project`), no build tool

## Project structure

| Package | Responsibility |
|---|---|
| `entities` | `Seller` and `Department`, the domain objects |
| `dao` | `SellerDAO` and `DepartmentDAO` interfaces, plus `daoFactory`, which hides the concrete implementation from the caller |
| `impl` | `SellerDAOjdbc` and `DepartmentDAOjdbc`, the JDBC implementations with the SQL |
| `db` | `DB`, which opens and closes connections and reads `db.properties`, and the `dbException` / `dbIntegrityException` types |
| `application` | `Program` and `Program2`, the two runnable classes |

The DAO pattern is what makes this work: the programs depend on the interfaces in `dao` and never on the JDBC code in `impl`, so replacing the database or the access strategy would not touch the calling code.

## What each program does

- **`Program`** — sellers: `findById`, `findByDepartment`, `findAll`, `insert`, `update` and `deleteById`. `findAll` and `findByDepartment` join `seller` and `department` and reuse each `Department` instance through a `Map`, so sellers of the same department point to the same object.
- **`Program2`** — departments: the same operations, including a `deleteById` that asks for the id in the terminal and fails with `dbIntegrityException` when sellers still reference that department.

## Running it

**Requirements:** Java 17, MySQL running, and the MySQL JDBC driver (`mysql-connector-j`) added to the project classpath. The driver is not included in this repository.

1. Create the database and the `department` and `seller` tables:

| Table | Columns |
|---|---|
| `department` | `Id`, `Name` |
| `seller` | `Id`, `Name`, `Email`, `BirthDate`, `BaseSalary`, `DepartmentId` → `department(Id)` |

2. Create your credentials file from the template and fill it in:

```bash
cp db.properties.example db.properties
```

`db.properties` is ignored by git and must never be committed. `DB.loadProperties()` reads it from the directory the program is started in.

3. Run `application.Program` or `application.Program2` from Eclipse, or from the terminal with the driver on the classpath:

```bash
javac -d bin $(find src -name "*.java")
java -cp bin:mysql-connector-j.jar application.Program
```

## License

Licensed under the [MIT License](LICENSE).

## Contact

Nathan Paiva de Lacerda — [LinkedIn](https://www.linkedin.com/in/nathan-paiva-636336236)
