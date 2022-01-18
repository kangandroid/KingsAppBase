package com.king.mobile.lib.algorithm;

import com.king.mobile.lib.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static TreeNode root;

    static {
        root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
    }

    // 前序遍历
    public static void preTraverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        preTraverse(root.left, list);
        preTraverse(root.right, list);
    }

    // 中叙遍历
    public static void inTraverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inTraverse(root.left, list);
        list.add(root.val);
        inTraverse(root.right, list);
    }

    // 后续遍历
    public static void lastTraverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        lastTraverse(root.left, list);
        lastTraverse(root.right, list);
        list.add(root.val);
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * <p>
     * 例如：输入前序遍历序列{1,2,4,7,3,5,6,8} 和 中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     *
     * @param pre
     * @param in
     */
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || in.length == 0 || pre.length != in.length) return null;
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }

    /**
     * BFS
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temRoot = queue.getFirst();
            result.add(temRoot.val);
            if (temRoot.left != null) {
                queue.addLast(temRoot.left);
            }
            if (temRoot.right != null) {
                queue.addLast(temRoot.right);
            }
        }
        return result;
    }

    /**
     * 深度优先遍历
     *
     * @param root
     */
    public static void dfs(TreeNode root) {
        if (root == null) return;
        int val = root.val;
        dfs(root.left);
        dfs(root.right);
        PrintUtil.print(val);
    }

    /**
     * @param sequence
     * @return
     */
    public boolean verifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        return isBSTSequence(sequence, 0, sequence.length - 1);
    }

    /**
     * @param sequence
     * @param start
     * @param end
     * @return
     */
    private boolean isBSTSequence(int[] sequence, int start, int end) {
        if (start >= end) return true;
        int index = -1;
        int rootV = sequence[end];
        boolean a = false;
        for (int i = start; i < end; i++) {
            if (sequence[i] < rootV) {
                if (a) {
                    return false;
                } else {
                    index = i;
                }
            } else {
                a = true;
            }
        }
        return isBSTSequence(sequence, start, index - 1) && isBSTSequence(sequence, index + 1, end - 1);
    }


    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
//        preTraverse(root, arrayList);
//        inTraverse(root, arrayList);
//        lastTraverse(root, arrayList);
//        PrintUtil.print(Arrays.toString(arrayList.toArray()));
//                0, 1, 3, 4, 2, 5, 6,
//                3, 1, 4, 0, 5, 2, 6,
//                3, 4, 1, 5, 6, 2, 0
        int[] pre = {0, 1, 3, 4, 2, 5, 6};
        int[] in = {3, 1, 4, 0, 5, 2, 6};
        TreeNode treeNode = reConstructBinaryTree(pre, in);
        dfs(treeNode);
        lastTraverse(treeNode, arrayList);
        PrintUtil.print(Arrays.toString(arrayList.toArray()));
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        if (root != null) {
            ArrayList<Integer> e = new ArrayList<>();
            e.add(root.val);
            paths.add(e);
            findPath(root, target, paths);
        }
        return paths;
    }

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     */
    TreeNode tem = null;

    public TreeNode convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        convert(pRootOfTree.right);
        if (tem != null) {
            pRootOfTree.right = tem;
            tem.left = pRootOfTree;
        }
        tem = pRootOfTree;
        convert(pRootOfTree.left);
        return tem;
    }

    /**
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        if (root1.val == root2.val) {
            if (isSame(root1, root2)) {
                return true;
            }
        }
        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    /**
     * 判断两节点以下是否相同
     *
     * @param root1
     * @param root2
     * @return
     */
    boolean isSame(TreeNode root1, TreeNode root2) {
        if (root1.val != root2.val) {
            return false;
        } else {
            return isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
        }
    }

    /**
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;
        while (i < pushA.length) {
            if (pushA[i] != popA[j]) {
                stack.push(pushA[i++]);
            } else {
                i++;
                j++;
                while (!stack.empty() && stack.peek() == popA[j]) {
                    stack.pop();
                    j++;
                }
            }
        }
        return stack.empty();
    }

    public void findPath(TreeNode root, int target, ArrayList<ArrayList<Integer>> paths) {
        ArrayList<Integer> path = paths.get(paths.size() - 1);
        if (root.left == null && root.right == null) {
            int sum = 0;
            for (Integer integer : path) {
                sum += integer;
            }
            if (sum != target) {
                paths.remove(path);
            }
        } else if (root.left != null && root.right == null) {
            path.add(root.left.val);
            findPath(root.left, target, paths);
        } else if (root.left == null) {
            path.add(root.right.val);
            findPath(root.right, target, paths);
        } else {
            ArrayList<Integer> arrayList = (ArrayList<Integer>) path.clone();
            path.add(root.left.val);
            findPath(root.left, target, paths);
            arrayList.add(root.right.val);
            paths.add(arrayList);
            findPath(root.right, target, paths);
        }
    }


    public int[][] threeOrders(TreeNode root) {
        List<Integer> pre = new ArrayList();
        List<Integer> in = new ArrayList();
        List<Integer> last = new ArrayList();

        preTraverse(root, pre);
        inTraverse(root, in);
        lastTraverse(root, last);

        int n = pre.size();
        int[][] result = new int[3][n];
        for (int i = 0; i < n; i++) {
            result[0][i] = pre.get(i);
            result[1][i] = in.get(i);
            result[2][i] = last.get(i);
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(root);
        LinkedList<TreeNode> tem = new LinkedList();
        ArrayList<Integer> arrayList = new ArrayList();
        while (queue.size() > 0) {
            TreeNode node = queue.removeFirst();
            arrayList.add(node.val);
            if (node.left != null) {
                tem.addLast(node.left);
            }
            if (node.right != null) {
                tem.addLast(node.right);
            }
            if (queue.size() == 0) {
                result.add(arrayList);
                arrayList = new ArrayList();
                queue = tem;
                tem = new LinkedList();
            }
        }
        return result;
    }


}

