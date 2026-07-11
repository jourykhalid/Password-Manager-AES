# Secure Password Manager (AES-256)

A secure CLI-based desktop Password Manager application built using **Java**. The application allows users to store, retrieve, and generate strong cryptographic passwords. 

To guarantee top-tier privacy, all data stored in the local file system is strictly encrypted using the **AES (Advanced Encryption Standard)** algorithm and can only be unlocked via a personalized **Master Password**.

## Features
- **AES-256 Encryption:** Every single entry (both website names and passwords) is encrypted before being written to the local disk.
- **Secure Key Derivation:** Uses SHA-256 hashing to generate a unique encryption key from the user's Master Password.
- **Automated Password Generator:** Generates high-entropy, random secure passwords including mixed-case letters, numbers, and symbols.
- **Zero-Knowledge Architecture:** If the incorrect Master Password is provided, the data decryption sequence fails automatically, keeping the files safe from brute-force attempts.

## Project Structure
- `EncryptionUtils.java`: Contains core cryptographic logic (Encryption, Decryption, Key generation).
- `PasswordStorage.java`: Manages safe file I/O operations and local data storage tracking.
- `Main.java`: Drives the interactive command-line interface and logic loop.

## How to Run in VS Code

### Prerequisites
- Ensure you have the **Java Extension Pack** installed in VS Code.
- Make sure Java JDK (version 8 or above) is configured.

### Execution
1. Open the project root folder in VS Code.
2. Navigate to `src/Main.java`.
3. Click on the **Run** button above the `main` method or press `F5`.
4. Define your Master Password on the first prompt to initialize/unlock the storage vault.

## License
This project is open-source and available under the MIT License.
