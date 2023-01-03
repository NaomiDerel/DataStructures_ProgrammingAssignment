public interface TwoThreeTree {
    public int compare(Node x, Node y);
    public Node Successor(Node x);
    public Node Predecessor(Node x);
    public void Update_Key(Node x);
    public void Set_Children(Node x,Node left, Node middle, Node right);
    public Node Insert_And_Split(Node x,Node z);
    public void Insert (Node z);
    public Node borrowOrMerge(Node y);
    public void DeleteWithNode(Node x);
}
