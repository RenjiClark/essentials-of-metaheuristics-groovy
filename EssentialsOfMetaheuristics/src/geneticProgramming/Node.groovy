package geneticProgramming

//import groovy.util.Node;

class Node implements Cloneable{

    Random random = new Random()

    def count
    def tree

    def size() {
        count = 0
        sizeHelper(this)
        count
    }

    def sizeHelper(currentNode) {
        count++

        if (this.class == FunctionNode) {
            if (currentNode.Arity == 1){
                sizeHelper(currentNode.children[0])
            } else if (currentNode.Arity == 2){
                sizeHelper(currentNode.children[0])
                sizeHelper(currentNode.children[1])
            }
        }
    }

    def isHead() {
        parent == null
    }

    def isSecondChild() {
        parent.Arity == 2 && parent.children[1] == this
    }

}
