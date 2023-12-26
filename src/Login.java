import java.util.Random;

public class Login {
    private String username = "admin";
    private String password = "Semangat123";
    private String currentCaptcha;
    private int maxLoginAttempts = 3; // Jumlah maksimal percobaan login
    private int loginAttemptsRemaining = maxLoginAttempts;

    public Login() {
        generateCaptcha();
    }

    public String getCurrentCaptcha() {
        return currentCaptcha;
    }

    private void generateCaptcha() {
        currentCaptcha = generateRandomCaptcha();
    }

    private String generateRandomCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captcha.toString();
    }

    public boolean authenticate(String enteredUsername, String enteredPassword, String enteredCaptcha) {
        if (loginAttemptsRemaining <= 0) {
            System.out.println("Anda sudah 3x salah, coba lagi nanti");
            return false;
        }

        if (enteredUsername.equals(username) && enteredPassword.equals(password)
                && enteredCaptcha.equals(currentCaptcha)) {
            resetLoginAttempts();
            generateCaptcha();
            return true;
        } else {

            loginAttemptsRemaining--;
            generateCaptcha();
            return false;
        }
    }

    private void resetLoginAttempts() {
        loginAttemptsRemaining = maxLoginAttempts;
    }
}
