package BitOperation;

// Generate the hexadecimal representation for a given non-negative integer number as a string.
// The hex representation should start with "0x".
// There should not be extra zeros on the left side.

public class HexadecimalRepresentation {
    // Solution 1: num is positive number
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int remainder = num % 16;
            num /= 16;
            // remainder = num & 0xF;
            // num >>>= 4
            sb.append(convert(remainder));
        }
        return sb.reverse().toString();
    }

    private Character convert(int n) {
        if (n < 10) {
            return (char)('0' + n);
        } else {
            return (char)('a' + n - 10);
        }
    }

    // Solution 2: num maybe negative number
    public String toHex2(int num) {
        if (num == 0) {
            return "0";
        }
        boolean zeroFlag = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 28; i >= 0; i -= 4) {
            int hexNum = (num >>> i) & 0xF;
            if (hexNum != 0 || zeroFlag) {
                sb.append(convert(hexNum));
                zeroFlag = true;
            }
        }
        return sb.toString();
    }
}
