package BitOperation;

// Determine if the characters of a given string are all unique.

// Assumptions:
// We are using ASCII char set, the value of valid characters are from 0 to 255
// The given string is not null

public class AllUniqueCharacters2 {
    public boolean allUnique(String word) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        // ASCII 0~255
        // 256 / 32 = 8
        int[] bitVector = new int[8];
        for (int i = 0; i < word.length(); ++i) {
            int k = word.charAt(i);
            int row = k / 32;
            int col = k % 32;
            int bitFlag = 1 << col;
            if ((bitVector[row] & bitFlag) != 0) {
                return false;
            }
            bitVector[row] |= bitFlag;
        }
        return true;
    }

    public static void main(String[] args) {
        AllUniqueCharacters2 allUniqueCharacters2 = new AllUniqueCharacters2();
        System.out.println(allUniqueCharacters2.allUnique("abcdefg*()$#"));
        System.out.println(allUniqueCharacters2.allUnique("abcdefg&*()$#*"));
    }
}
