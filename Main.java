import java.util.Map;
import java.util.Scanner;
import java.security.SecureRandom;

public class Main {
    // دالة لتوليد كلمات مرور عشوائية وقوية جداً
    public static String generateSecurePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("   Welcome to Secure Password Manager   ");
        System.out.println("========================================");
        
        System.out.print("Enter your Master Password to unlock: ");
        String masterPassword = scanner.nextLine();

        // محاولة تحميل البيانات للتأكد من صحة كلمة المرور الرئيسية
        Map<String, String> passwords = PasswordStorage.loadPasswords(masterPassword);
        
        if (passwords == null) {
            System.out.println("[-] Invalid Master Password! Access Denied.");
            return;
        }

        System.out.println("[+] Access Granted Successfully.");

        while (true) {
            System.out.println("\n--- Options ---");
            System.out.println("1. View Saved Passwords");
            System.out.println("2. Add New Password manually");
            System.out.println("3. Generate & Save a Secure Password");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    if (passwords.isEmpty()) {
                        System.out.println("[*] No passwords saved yet.");
                    } else {
                        System.out.println("\nYour Saved Accounts:");
                        passwords.forEach((site, pass) -> System.out.println("-> " + site + " : " + pass));
                    }
                    break;
                case "2":
                    System.out.print("Enter Website/App Name: ");
                    String site = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String pass = scanner.nextLine();
                    passwords.put(site, pass);
                    PasswordStorage.savePasswords(passwords, masterPassword);
                    System.out.println("[+] Password saved securely!");
                    break;
                case "3":
                    System.out.print("Enter Website/App Name: ");
                    String autoSite = scanner.nextLine();
                    String autoPass = generateSecurePassword(14); // توليد كلمة مرور بطول 14 خانة
                    System.out.println("[+] Generated Password: " + autoPass);
                    passwords.put(autoSite, autoPass);
                    PasswordStorage.savePasswords(passwords, masterPassword);
                    System.out.println("[+] Password saved securely!");
                    break;
                case "4":
                    System.out.println("Exiting... Stay secure!");
                    return;
                default:
                    System.out.println("[-] Invalid choice. Try again.");
            }
        }
    }
}