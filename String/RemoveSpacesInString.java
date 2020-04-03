package String;

// Given a string, remove all leading/trailing/duplicated empty spaces.

// Assumption: The given string is not null.

public class RemoveSpacesInString {
    public String removeSpace(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] in = input.toCharArray();
        int fast = 0, slow = 0;
        for (; fast < input.length(); ++fast) {
            if (in[fast] != ' ' || (slow > 0 && in[slow - 1] != ' ')) {
                in[slow++] = in[fast];
            }
        }
        while (slow >= 1 && in[slow - 1] == ' ') {
            slow--;
        }
        return new String(in, 0, slow);
    }

    // Time complexity is O(n)
    // Space complexity is O(n)
    public static void main(String args[]) {
        RemoveSpacesInString removeSpacesInString = new RemoveSpacesInString();
        System.out.println(removeSpacesInString.removeSpace("   s tud e nt fs  gsg  "));
    }
}
