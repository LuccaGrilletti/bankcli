# BankCLI

A console-based banking simulator written in Java. BankCLI lets you register
clients, open accounts, and move money between them through a simple
menu-driven interface backed by a layered architecture (model / repository /
service / cli) and a custom domain exception hierarchy.

## Features

**Implemented**
- Client management: create, search by CPF, list, update, delete
- Account management: create, search by ID, list, delete
- Deposits and withdrawals
- CPF validation (official check-digit algorithm, modulo 11)
- Business rules enforced at the service layer (e.g. no duplicate CPFs, no
  deleting a client/account that still holds a balance)
- A custom, unchecked exception hierarchy (`BankCliException` and its
  subtypes) for consistent, user-friendly error handling in the CLI

**Planned / not yet implemented**
- Login / authentication
- Transfers between accounts
- Transaction history and daily transaction limits

These show up as "coming soon" options in the menu — the corresponding
exception types (`InvalidCredentialsException`, `SameAccountTransferException`,
`DailyLimitExceededException`, `TransactionNotFoundException`) already exist
in the codebase, reserved for when these features are built.

## Tech stack

- Java 21
- No external dependencies
- No build tool (Maven/Gradle) — plain `javac`/`java`, or open directly in
  an IDE such as IntelliJ IDEA

## Prerequisites

- JDK 21 or newer installed and available on your `PATH`

## Getting started

### Clone the repository

```bash
git clone https://github.com/<your-username>/BankCLI.git
cd BankCLI
```

### Option A — Run from an IDE (recommended)

Open the project folder in IntelliJ IDEA (or any IDE with Java support),
let it index the project, then run `src/main/java/com/lucca/bankcli/Main.java`.

### Option B — Run from the command line

Compile every source file into an output directory, then run the compiled `Main` class:

```bash
# from the repository root
javac -d out $(find src/main/java -name "*.java")   # Linux/macOS/Git Bash
java -cp out com.lucca.bankcli.Main
```

On Windows PowerShell, compile with:

```powershell
Get-ChildItem -Recurse -Filter *.java src\main\java | ForEach-Object { $_.FullName } | Out-File sources.txt
javac -d out "@sources.txt"
java -cp out com.lucca.bankcli.Main
```

> On Windows, if the console output looks garbled, run with
> `java -Dstdout.encoding=UTF-8 -cp out com.lucca.bankcli.Main` or switch the
> console code page to UTF-8 (`chcp 65001`) — the default `cp850` code page
> can mangle some characters printed by the JVM.

## Project structure

```
src/main/java/com/lucca/bankcli/
├── Main.java          # composition root: wires repositories, services and the menu together
├── cli/                # console presentation layer (Menu, and a reserved CommandParser stub)
├── model/               # domain entities: Client, Account (Transaction is a reserved stub)
├── repository/          # persistence contracts and in-memory implementations
├── service/              # use-case orchestration and cross-entity business rules
├── util/                 # CPF validation (and a reserved Validator stub)
└── exception/            # BankCliException and its subtypes
```

## Usage walkthrough

```
===== BankCLI =====
--- Clients ---
1. Create client
2. Search client (by CPF)
3. List clients
4. Update client
5. Delete client
--- Accounts ---
6. Create account
7. Search account (by ID)
8. List accounts
9. Delete account
--- Transactions ---
10. Deposit
11. Withdraw
--- Other ---
12. Login (coming soon)
13. Transfer between accounts (coming soon)
14. Quit
Choose an option:
```

A typical session: create a client (option 1), create an account for that
client's CPF (option 6), then deposit/withdraw (options 10/11) using the
account ID printed at creation time.

## Testing

There are no automated tests yet (`src/test` exists but is currently empty).
All flows described above have been exercised manually end-to-end.
