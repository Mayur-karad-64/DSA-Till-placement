class Solution {
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    private boolean check(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;

        return a.val == b.val &&
               check(a.left, b.right) &&
               check(a.right, b.left);
    }
}