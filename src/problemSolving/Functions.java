package problemSolving;

public class Functions {

    public static void main(String[] args) {

    }

    // 문자열이 숫자인지 확인-1
    static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }

        return true;
    }

    // 문자열이 숫자인지 확인-2 (자바 8)
    static boolean isNumeric_2(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.chars().allMatch(Character::isDigit);
    }
}
