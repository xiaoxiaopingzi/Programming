package binarySearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二分搜索树——以右为大的二叉树
 * @author Administrator
 */
// 类上的泛型，可以在类中的任何位置使用，并且Key需要具有可比较性，所以需要extends Comparable<Key>
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

	// 树中的节点为私有的类, 外界不需要了解二分搜索树节点的具体实现
	private class Node{
		private Key key;
		private Value value;
		private Node left, right;
		
		public Node(Key key, Value value){
			this.key = key;
			this.value = value;
			this.left = this.right = null;
		}
		
		public Node(Node node){
			this.key = node.key;
			this.value = node.value;
			this.left = node.left;
			this.right = node.right;
		}
	}
	
	private Node root;  // 根节点
	private int count; // 树中节点的个数
	
	// 构造函数, 默认构造一棵空二分搜索树
	public BinarySearchTree(){
		this.root = null;
		this.count = 0;
	}
	
	// 返回二分搜索树的节点个数
	public int size() {
		return this.count;
	}
	
	// 返回二分搜索树是否为空
	public boolean isEmpty() {
		return this.count == 0;
	}
	
	// 向二分搜索树中插入一个新的(key, value)数据对
	public void insert(Key key, Value value){
		root = insert(root, key ,value);
	}
	
	// 查看二分搜索树中是否存在键key
	public boolean contain(Key key) {
		return contain(root, key);
	}
	
	// 在二分搜索树中搜索键key所对应的值。如果这个值不存在, 则返回null
	public Value search(Key key){
		return search(root, key).value;
	}
	
	// 前序遍历——深度优先遍历
	public void preOrder(){
		preVoid(root);
	}
	 
	// 中序遍历——深度优先遍历
	public void inOrder(){
		inOrder(root);
	}
	
	// 后序遍历——深度优先遍历
	public void postOrder(){
		postOrder(root);
	}
	
	// 层序遍历——广度优先遍历
	public void levelOrder(){
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			System.out.println(node.key);// 打印从队列移出的队首元素
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}
	
	// 寻找最小的键值，如果二叉搜索树的节点数为0，则返回null
	public Key minimum(){
		if (count > 0) {
			Node node = minimum(root);
			return node.key;
		}else {
			return null;
		}
	}
	
	// 寻找最大的键值，如果二叉搜索树的节点数为0，则返回null
	public Key maximum(){
		if (count > 0) {
			Node node = maximum(root);
			return node.key;
		}else {
			return null;
		}
	}
	
	// 从二叉树中删除最小值所在的节点
	public void removeMin(){
		if (root != null) {
			root = removeMin(root);
		}
	}
	
	// 从二叉树中删除最大值所在的节点
	public void removeMax(){
		if (root != null) {
			root = removeMax(root);
		}
	}
	
	// 从二叉树中删除键值为key的节点
	public void remove(Key key){
		if (contain(key)) {
			root = remove(root, key);
		}
	}
	
	// 寻找key的floor值, 递归算法
    // 如果不存在key的floor值(key比BST中的最小值还小), 返回NULL
	public Key floor(Key key){
		if (count == 0 || key.compareTo(minimum()) < 0) {
			return null;
		}else {
			Node floorNode = floor(root, key);
			return floorNode.key;
		}
	}
	
	// 寻找key的ceil值, 递归算法
    // 如果不存在key的ceil值(key比BST中的最大值还大), 返回NULL
	public Key ceil(Key key) {
		if (count == 0 || key.compareTo(maximum()) > 0) {
			return null;
		}else {
			Node ceilNode = ceil(root, key);
			return ceilNode.key;
		}
	}
	
	
	// 查找key的前驱
    // 如果不存在key的前驱(key不存在, 或者key是整棵二叉树中的最小值), 则返回NULL
	public Key predecessor(Key key){
		if (!contain(key) || key.compareTo(minimum()) <= 0) {
			return null;
		}else {
			// 寻找键值为key的节点
			Node node = search(root, key);
			if (node.left != null) {
				// 如果key所在的节点左子树不为空,则其左子树的最大值为key的前驱
				return maximum(node.left).key;
			}else {
				// 否则, key的前驱在从根节点到key的路径上, 在这个路径上寻找到比key小的最大值, 即为key的前驱
				Node preNode = predecessorFromAncestor(root, key);
				return preNode == null ? null : preNode.key;
			}
		}
	} 
	
	// 查找key的后继, 递归算法
    // 如果不存在key的后继(key不存在, 或者key是整棵二叉树中的最大值), 则返回NULL
    public Key successor(Key key){
    	if (!contain(key) || key.compareTo(maximum()) >= 0) {
			return null;
		}else {
			Node node = search(root, key);
			if (node.right != null) {
				// 如果key所在的节点右子树不为空,则其右子树的最小值为key的后继
				return minimum(node.right).key;
			}else {
				// 否则, key的后继在从根节点到key的路径上, 在这个路径上寻找到比key大的最小值, 即为key的后继
		        Node sucNode = successorFromAncestor(root, key);
		        return sucNode == null ? null : sucNode.key;
			}
		}
    }
	
	
	
//------------------------------------------------------------------------
// 二叉搜索树的帮助类
//------------------------------------------------------------------------
	
	// 向以node为根的二分搜索树中, 插入节点(key, value), 使用递归算法
    // 返回插入新节点后的二分搜索树的根
	private Node insert(Node node, Key key, Value value) {
		if (node == null) { 
			// 递归到底的情况——没有节点即表示递归到底了
			count++;
			return new Node(key, value);
		}
		
		if (node.key.compareTo(key) == 0) {
			// node.key和key相等就更新
			node.value = value;
		}else if (node.key.compareTo(key) < 0) {
			// node.key小于key,就取出node的右孩子进行比较
			node.right = insert(node.right, key, value);
		}else {
			node.left = insert(node.left, key, value);
		}
		return node;
	}
	
	// 查看以node为根的二分搜索树中是否包含键值为key的节点, 使用递归算法
	private boolean contain(Node node, Key key) {
		if (node == null) {
			return false;
		}
		if (node.key.compareTo(key) == 0) {
			return true;
		}else if (node.key.compareTo(key) < 0) {
			return contain(node.right, key); // 不断返回
		}else {
			return contain(node.left, key);
		}
	}
	
	// 在以node为根的二分搜索树中查找key所对应的node, 递归算法
    // 若value不存在, 则返回NULL
	private Node search(Node node, Key key){
		if (node == null) {
			// 递归到底发现node为null，说明二叉树中没有指定key的元素
			return null;
		}
		if (node.key.compareTo(key) == 0) {
			return node;
		}else if (node.key.compareTo(key) < 0) {
			// 将search(node.right, key)得到的结果return回去
			return search(node.right, key);
		}else {  // node.key > key
			return search(node.left, key);
		}
	}
	
	// 对二叉搜索树进行前序遍历
	// 在第一次访问节点时就打印出节点的key
	private void preVoid(Node node){
		if (node != null) {
			System.out.println(node.key);
			preVoid(node.left);
			preVoid(node.right);
		}
		
	}
	
	// 对二叉搜索树进行中序遍历
	private void inOrder(Node node){
		if (node != null) {
			inOrder(node.left);
			System.out.println(node.key);
			inOrder(node.right);
		}
	}
	
	// 对二叉搜索树进行后序遍历
	private void postOrder(Node node){
		if (node != null) {
			inOrder(node.left);
			inOrder(node.right);
			System.out.println(node.key);
		}
	}
	
	// 在以node为根的二叉搜索树中，返回最小键值的节点
	private Node minimum(Node node){
		if (node.left == null) {
			// 当node.left为null时，说明当前的节点就是最小的节点
			return node;
		}
		return minimum(node.left);
	}
	
	// 在以node为根的二叉搜索树中，返回最小键值的节点
	// 非递归写法，迭代写法
	public Node getMinNode(){
		Node currentNode = root;
		while (currentNode.left != null) {
			currentNode = currentNode.left;
		}
		return currentNode;
	}
	
	// 在以node为根的二叉搜索树中，返回最大键值的节点
	private Node maximum(Node node){
		if (node.right == null) {
			return node;
		}
		return maximum(node.right);
	}
	
	// 删除以node为根的二分搜索树中的最小节点
	// 返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node){
		if (node.left == null) { 
			//node.left为null表示node为最小的节点
			Node rightNode = node.right;
			node.right = null;
			count--;
			return rightNode; //rightNode可能为null
		}
		node.left = removeMin(node.left);
		return node;
	}
	
	// 删除以node为根的二分搜索树中的最大节点
	// 返回删除节点后新的二分搜索树的根
	private Node removeMax(Node node){
		if (node.right == null) {
			Node leftNode = node.left;	
// 不能直接将node置为null，因为这样会导致上一级递归的node.right=null
// 会导致出现 null = leftNode的情况出现，这就出错了
//			node = null; 
			node.left = null;
			count --;
			return leftNode;
		}
		node.right = removeMax(node.right);
		return node;
	}
	
	// 删除掉以node为根的二分搜索树中键值为key的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
	private Node remove(Node node, Key key){
		if (node == null) {
			return null;
		}
		if (node.key.compareTo(key) < 0) {
			node.right = remove(node.right, key);
			return node;
		}else if (node.key.compareTo(key) > 0) {
			node.left = remove(node.left, key);
			return node;
		}else {    // key == node.key
			
			// 待删除节点左子树为空的情况
			if (node.left == null) {
				Node rightNode = node.right;
				node = null;
				count --;
				return rightNode;
			}
			
			// 待删除节点右子树为空的情况
			if (node.right == null) {
				Node leftNode = node.left;
				node = null;
				count--;
				return leftNode;
			}
			
			//  node.left != null && node.right != null
			// 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
			Node successor = new Node(minimum(node.right));
			count ++;
			
			successor.right = removeMin(node.right);
			successor.left = node.left;
			
			node.left = node.right = null;
			count--;
			
			return successor;

			
// 找到比待删除节点小的最大节点, 即待删除节点左子树的最大节点，用这个节点顶替待删除节点的位置        			
//			Node predecessor = new Node(maximum(node.left));
//			count ++;
//			predecessor.right = node.right;
//			predecessor.left = removeMax(node.left);
//			node.left = node.right = null;
//			count--;
//			return predecessor;
		}
	}
	
	// 在以node为根的二叉搜索树中, 寻找key的floor值所处的节点, 递归算法
	private Node floor(Node node, Key key){
		
		if (node == null) {
			return null;
		}
		
		if (node.key.compareTo(key) == 0) {
			// 如果node的key值和要寻找的key值相等
	        // 则node本身就是key的floor节点
			return node;
		}else if (node.key.compareTo(key) < 0) {
			// 如果node->key < key
	        // 则node有可能是key的floor节点, 也有可能不是(存在比node->key大但是小于key的其余节点)
	        // 需要尝试向node的右子树寻找一下
			Node tempNode = floor(node.right, key);
			if (tempNode != null) {
				return tempNode;
			}else {
				return node;
			}
		}else { 
			// node.key > key
			// 如果node的key值比要寻找的key值大
	        // 则要寻找的key的floor节点一定在node的左子树中
			return floor(node.left, key);
		}
	}
	
	// 在以node为根的二叉搜索树中, 寻找key的ceil值所处的节点, 递归算法
	private Node ceil(Node node, Key key){
		if (node == null) {
			return null;
		}
		
		if (node.key.compareTo(key) == 0) {
			// 如果node的key值和要寻找的key值相等
	        // 则node本身就是key的ceil节点
			return node;
		}else if (node.key.compareTo(key) < 0) {
			// 如果node的key值比要寻找的key值小
	        // 则要寻找的key的ceil节点一定在node的右子树中
			return ceil(node.right, key);
		}else {
			// 如果node.key > key
	        // 则node有可能是key的ceil节点, 也有可能不是(存在比node.key小但是大于key的其余节点)
	        // 需要尝试向node的左子树寻找一下
			Node tempNode = ceil(node.left, key);
			if (tempNode != null) {
				return tempNode;
			}else {
				return node;
			}
		}
	}
	
	// 在以node为根的二叉搜索树中, 寻找key的祖先中,比key小的最大值所在节点, 递归算法
    // 算法调用前已保证key存在在以node为根的二叉树中
    private Node predecessorFromAncestor(Node node, Key key){
    	// 递归到了key所在的节点就直接返回null
    	if (node.key.compareTo(key) == 0) {
			return null;
		}else if (node.key.compareTo(key) < 0) {
			// 如果当前节点小于key, 则当前节点有可能是比key小的最大值
            // 向下搜索结果存储到maxNode中
			Node maxNode = predecessorFromAncestor(node.right, key);
			if (maxNode == null) {
				// 如果maxNode为空, 则当前节点即为结果
                return node;
			}else {
				// maxNode和当前节点node取最大值返回
              return maxNode.key.compareTo(node.key) > 0 ? maxNode : node;
			}
		}else {
			// 如果当前节点大于key, 则当前节点不可能是比key小的最大值
            // 向下搜索到的结果直接返回
			return predecessorFromAncestor(node.left, key);
		}
    }
    
    
    // 在以node为根的二叉搜索树中, 寻找key的祖先中,比key大的最小值所在节点, 递归算法
    // 算法调用前已保证key存在在以node为根的二叉树中
    private Node successorFromAncestor(Node node, Key key){
    	if (node.key.compareTo(key) == 0) {
			return null;
		}else if (node.key.compareTo(key) < 0) {
			// 如果当前节点小于key, 则当前节点不可能是比key大的最小值
            // 向下搜索到的结果直接返回
			return successorFromAncestor(node.right, key);
		}else {
			// node.key > key
			// 如果当前节点大于key, 则当前节点有可能是比key大的最小值
            // 向下搜索结果存储到minNode中
			Node minNode = successorFromAncestor(node.left, key);
			if (minNode != null) {
				// minNode和当前节点node取最小值返回
				return minNode;
//              return minNode.key.compareTo(node.key) < 0 ? minNode : node;
			}else {
				return node;
			}
		}
    }
}
