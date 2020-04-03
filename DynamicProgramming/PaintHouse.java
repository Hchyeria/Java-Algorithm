package DynamicProgramming;

public class PaintHouse {
    public int minCostII(int[][] costs) {
        // write your code here
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int min1 = 0, min2 = 0;
        int id = -1;
        int n = costs.length;
        int k = costs[0].length;

        for (int i = 0; i < n; ++i) {
            int curMin1 = Integer.MAX_VALUE, curMin2 = Integer.MAX_VALUE;
            int curId = -1;
            for (int j = 0; j < k; ++j) {
                int cur = 0;
                if (j != id) {
                    cur = min1 + costs[i][j];
                } else {
                    cur = min2 + costs[i][j];
                }

                if (cur < curMin1) {
                    curMin2 = curMin1;
                    curMin1 = cur;
                    curId = j;
                } else if (cur < curMin2) {
                    curMin2 = cur;
                }
            }
            min1 = curMin1;
            min2 = curMin2;
            id = curId;
        }

        return min1;

    }

    public static void main(String[] args) {
        PaintHouse paintHouse = new PaintHouse();
        int[][] a = {
                {1,5,6},
        {14,15,5},
            {4,3,3},
                {15,15,9},
                    {9,2,7},
                        {6,5,7},
                            {19,4,4},
                                {6,13,3},
                                    {8,16,20},
                                        {18,7,9}
        };
        System.out.println(paintHouse.minCostII(a));
    }
}
