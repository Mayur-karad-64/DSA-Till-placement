import java.util.*;

class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i=0;i<n;i++) parent[i] = i;
        }

        int find(int x) {
            if(parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if(px == py) return false;

            if(rank[px] < rank[py])
                parent[px] = py;
            else if(rank[px] > rank[py])
                parent[py] = px;
            else {
                parent[py] = px;
                rank[px]++;
            }

            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {

        int left = 1;
        int right = 200000;
        int ans = -1;

        while(left <= right) {

            int mid = left + (right-left)/2;

            if(canBuild(n, edges, k, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean canBuild(int n, int[][] edges, int k, int target) {

        DSU dsu = new DSU(n);
        int used = 0;
        int upgrades = 0;

        List<int[]> optional = new ArrayList<>();

        for(int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            if(must == 1) {

                if(s < target) return false;

                if(!dsu.union(u,v)) return false;

                used++;

            } else {
                optional.add(e);
            }
        }

        List<int[]> good = new ArrayList<>();
        List<int[]> upgrade = new ArrayList<>();

        for(int[] e : optional) {

            int u = e[0], v = e[1], s = e[2];

            if(s >= target)
                good.add(e);
            else if(s*2 >= target)
                upgrade.add(e);
        }

        for(int[] e : good) {
            if(dsu.union(e[0], e[1]))
                used++;
        }

        for(int[] e : upgrade) {

            if(used == n-1) break;

            if(dsu.union(e[0], e[1])) {
                upgrades++;
                used++;
            }
        }

        return used == n-1 && upgrades <= k;
    }
}