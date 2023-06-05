class Node {
    public int element;
    public Node left;
    public Node right;
    public boolean type;

    public Node() {
        this(0, null, null, false);
    }
    public Node(int x, Node left, Node right, boolean type) {
        this.type = type;
        this.left = left;
    }
    
    //getters
    public int getElement()             { return element; }    
    public Node getLeft()               { return left; }
    public Node getRight()              { return right; }
    public boolean isType()             { return type; }
    //setters
    public void setElement(int element) { this.element = element; }
    public void setLeft(Node left)      { this.left = left; }
    public void setRight(Node right)    { this.right = right; }
    public void setType(boolean type)   { this.type = type; }
    
}