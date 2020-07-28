package Recursion;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() <= 0 || s.length() > 12) {
            return res;
        }

        int n = s.length();
        char[] string = s.toCharArray();
        dfs(res, string, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(List<String> res, char[] string, List<Integer> tmp, int index) {
        int n = string.length;

        if (tmp.size() == 3) {
            res.add(convert(tmp, string));
            return;
        }

        if (index == n - 1) {
            return;
        }

        for (int i = index; i < n - 1; ++i) {
            tmp.add(i);
            if (valid(tmp, string)) {
                dfs(res, string, tmp, i + 1);
            }
            tmp.remove(tmp.size() - 1);
        }
    }

    private boolean valid(List<Integer> list, char[] s) {
        int size = list.size();
        int pre = 0;
        if (size > 1) {
            pre = list.get(size - 2) + 1;
        }
        if (s[pre] == '0' && list.get(size - 1) - pre + 1 > 1) return false;
        int v = -1;
        try {
            v = Integer.parseInt(new String(s, pre, list.get(size - 1) - pre + 1));
        } catch (NumberFormatException e) {
            return false;
        }
        if (v < 0 || v > 255) {
            return false;
        }

        if (size == 3) {
            int n = s.length;
            pre = list.get(size - 1) + 1;
            if (s[pre] == '0' && n - pre > 1) return false;
            try {
                v = Integer.parseInt(new String(s, pre, n - pre));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return v >= 0 && v <= 255;
    }

    private String convert(List<Integer> list, char[] s) {
        int n = s.length;
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        for (int i : list) {
            if (pre != 0) sb.append(".");
            sb.append(new String(s, pre, i - pre + 1));
            pre = i + 1;
        }
        if (pre != 0) sb.append(".");
        sb.append(new String(s, pre, n - pre));
        return sb.toString();
    }

    public static void main(String[] args) {
        RestoreIPAddresses restoreIPAddresses = new RestoreIPAddresses();
        System.out.println(restoreIPAddresses.restoreIpAddresses("0000"));
    }
}
