package com.hm707.tree.binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 遍历二叉树
 */
public class TraverseBTree {

	List<Integer> valList = new ArrayList<>();

	/**
	 * 先序遍历 root -> left -> right
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		if (root == null)
			return valList;
		valList.add(root.val);

		if (root.left != null)
			preorderTraversal(root.left);
		if (root.right != null)
			preorderTraversal(root.right);

		return valList;
	}

	/**
	 * 按层级遍历
	 *
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {

		List<List<Integer>> resultList = new ArrayList<>();

		if (root == null)
			return resultList;

		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.offer(root);

		while (!nodeQueue.isEmpty()) {
			List<Integer> nodeList = new ArrayList<>();
			int size = nodeQueue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = nodeQueue.poll();

				nodeList.add(node.val);

				if (node.left != null)
					nodeQueue.offer(node.left);
				if (node.right != null)
					nodeQueue.offer(node.right);

			}
			resultList.add(nodeList);

		}
		return resultList;
	}
}
