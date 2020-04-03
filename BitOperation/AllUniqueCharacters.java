package BitOperation;

// Determine if the characters of a given string are all unique.

// Assumptions:
// The only set of possible characters used in the string are 'a' - 'z', the 26 lower case letters.
// The given string is not null.

public class AllUniqueCharacters {
    public boolean allUnique(String word) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        int occurChars = 0;
        for (int i = 0; i < word.length(); ++i) {
            int bitFlag = 1 << (word.charAt(i) - 'a');
            if ((occurChars & bitFlag) != 0) {
                return false;
            }
            occurChars |= bitFlag;

        }
        return true;
    }

    public static void main(String[] args) {
        AllUniqueCharacters allUniqueCharacters = new AllUniqueCharacters();
        System.out.println(allUniqueCharacters.allUnique("abcdefg"));
        System.out.println(allUniqueCharacters.allUnique("abcdefga"));
    }
}
