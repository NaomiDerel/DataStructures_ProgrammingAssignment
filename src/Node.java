public class Node<E>
{
    protected E node_content;
    protected Node<E> parent;
    protected Node<E> left;
    protected Node<E> middle;
    protected Node<E> right;
    protected Node<E> successor;
    protected Node<E> predecessor;


    public Node() {
    }

    public Node(E node_content){
        this.node_content = node_content;
        this.parent = null;
        this.left = null;
        this.middle = null;
        this.right = null;
        this.successor = null;
        this.predecessor = null;
    }

    public Node(E node_content, Node<E> parent) {
        this.node_content = node_content;
        this.parent = parent;
        this.left = null;
        this.middle = null;
        this.right = null;
        this.successor = null;
        this.predecessor = null;
    }

    public E getNode_content() {
        return node_content;
    }

    public Node<E> getParent() {
        return parent;
    }

    public void setSuccessor(Node<E> successor) {
        this.successor = successor;
    }

    public void setPredecessor(Node<E> predecessor) {
        this.predecessor = predecessor;
    }
}
