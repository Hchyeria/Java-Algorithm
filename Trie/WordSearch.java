package Trie;

import java.util.ArrayList;
import java.util.List;

class Trie {
    public static class TrieNode {
        TrieNode[] children;
        boolean isWord;
        TrieNode() {
            children = new TrieNode[26];
        }
    }

    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            int index = word.charAt(i) - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            int index = word.charAt(i) - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        int n = prefix.length();
        for (int i = 0; i < n; ++i) {
            int index = prefix.charAt(i) - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }
        return true;
    }

}

public class WordSearch {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || words.length == 0) {
            return res;
        }
        Trie trie = buildTrie(words);
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(res, board, i, j, trie, new StringBuilder());
            }
        }
        return res;
    }

    private void dfs(List<String> res, char[][] board, int i, int j, Trie trie, StringBuilder sb) {
        if (!isArea(i, j) || board[i][j] == '#') {
            return;
        }
        char c = board[i][j];

        sb.append(c);
        String s = sb.toString();
        if (!trie.startsWith(s)) {
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        if (trie.search(s)) {
            res.add(s);
        }
        board[i][j] = '#';
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            dfs(res, board, x, y, trie, sb);
        }
        sb.deleteCharAt(sb.length() - 1);
        board[i][j] = c;

    }

    private boolean isArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    private Trie buildTrie(String[] words) {
        Trie trie = new Trie();
        for (String s : words) {
            trie.insert(s);
        }
        return trie;
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
        char[][] b = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] w = {"oath","pea","eat","rain"};
        System.out.println(wordSearch.findWords(b, w));
    }
}
