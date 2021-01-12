package com.king.mobile.lib.algorithm;

import com.king.mobile.lib.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class BinaryTree {

    static class TreeNode {
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


    public static void preTraverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        preTraverse(root.left, list);
        preTraverse(root.right, list);
    }

    public static void inTraverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inTraverse(root.left, list);
        list.add(root.val);
        inTraverse(root.right, list);
    }

    public static void lastTraverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        lastTraverse(root.left, list);
        lastTraverse(root.right, list);
        list.add(root.val);
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重
     * 复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
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

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        return isBSTSequence(sequence, 0, sequence.length - 1);
    }

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
        ArrayList<Integer> arrayList = new ArrayList();
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
    public TreeNode Convert(TreeNode pRootOfTree){
        if(pRootOfTree == null)return null;
        Convert(pRootOfTree.right);
        if(tem!=null){
            pRootOfTree.right = tem;
            tem.left = pRootOfTree;
        }
        tem = pRootOfTree;
        Convert(pRootOfTree.left);
        return tem;
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
        } else if (root.right == null && root.right != null) {
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


}

