package geneticProgramming

//import groovy.util.Node;

class Node{

    Random random = new Random()

    def count = 0
    def tree

    def size() {
        sizeHelper(this, this.Arity)
        count
    }

    def sizeHelper(currentNode, currentArity) {
        count++

        if (this.class == FunctionNode) {
            if (currentArity == 1){
                sizeHelper(currentNode.children[0], currentNode.children[0].Arity)
            } else if (currentArity == 2){
                sizeHelper(currentNode.children[0], currentNode.children[0].Arity)
                sizeHelper(currentNode.children[1], currentNode.children[1].Arity)
            }
        }

        //        for(i in currentNode.children){
        //            sizeHelper(currentNode.getChildren(i))
        //        }
    }

    def isHead() {
        parent == null
    }

    def isSecondChild() {
        parent.Arity == 2 && parent.children[1] == this
    }

    def myClone() {
        def clone = this.clone()
        if (clone.Arity == 1){
            clone.children[0] = clone.children[0].clone()
        } else if (clone.Arity == 2){
            clone.children[0] = clone.children[0].clone()
            clone.children[1] = clone.children[1].clone()
        }
        return this.clone()
    }

}
