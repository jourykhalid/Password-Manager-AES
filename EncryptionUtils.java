import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class EncryptionUtils {
    private static final String ALGORITHM = "AES";

    // توليد مفتاح تشفير 256-بت ثابت بناءً على كلمة المرور الرئيسية
    private static SecretKeySpec generateKey(String masterPassword) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = sha.digest(masterPassword.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(key, ALGORITHM);
    }

    // دالة التشفير
    public static String encrypt(String data, String masterPassword) throws Exception {
        SecretKeySpec secretKey = generateKey(masterPassword);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // دالة فك التشفير
    public static String decrypt(String encryptedData, String masterPassword) throws Exception {
        SecretKeySpec secretKey = generateKey(masterPassword);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}