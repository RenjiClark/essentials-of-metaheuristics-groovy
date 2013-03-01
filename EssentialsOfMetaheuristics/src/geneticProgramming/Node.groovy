package geneticProgramming

//import groovy.util.Node;

class Node{
       
    Random random = new Random()
    
    def size() {
        count = 0
        sizeHelper(this)
        count
    }
    
    def sizeHelper(currentNode) {
        count++
        
        if (currentNode.left != currentNode.class == LeafNode) {
            sizeHelper(currentNode.left)
        }
        if (currentNode.right != currentNode.class == LeafNode) {
            sizeHelper(currentNode.right)
        }
    }
    
}
