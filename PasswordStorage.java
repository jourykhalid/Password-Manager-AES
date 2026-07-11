import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PasswordStorage {
    private static final String FILE_NAME = "passwords.dat";

    // حفظ جميع كلمات المرور داخل ملف محلي بعد تشفيرها
    public static void savePasswords(Map<String, String> passwordMap, String masterPassword) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : passwordMap.entrySet()) {
                // تشفير اسم الموقع وتشفير كلمة المرور الخاصة به
                String encryptedSite = EncryptionUtils.encrypt(entry.getKey(), masterPassword);
                String encryptedPass = EncryptionUtils.encrypt(entry.getValue(), masterPassword);
                writer.println(encryptedSite + ":" + encryptedPass);
            }
        } catch (Exception e) {
            System.out.println("[-] Error saving passwords: " + e.getMessage());
        }
    }

    // قراءة الملف المحلي وفك التشفير لعرض البيانات للمنتسب المصرح له
    public static Map<String, String> loadPasswords(String masterPassword) {
        Map<String, String> decryptedMap = new HashMap<>();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) {
            return decryptedMap; // إرجاع خريطة فارغة إذا كان أول تشغيل للبرنامج
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    // فك التشفير أثناء القراءة برمجياً
                    String site = EncryptionUtils.decrypt(parts[0], masterPassword);
                    String pass = EncryptionUtils.decrypt(parts[1], masterPassword);
                    decryptedMap.put(site, pass);
                }
            }
        } catch (Exception e) {
            // إذا كانت كلمة المرور الرئيسية خاطئة، سيفشل فك التشفير هنا تلقائياً
            return null; 
        }
        return decryptedMap;
    }
}