package geneticProgramming

import java.util.Random;

class Tree{

    def head

    Random random = new Random()

    def functions
    def varArray

    def Tree(functions = [new ValueArityPair({x,y -> x+y}, '+', 2),
        new ValueArityPair({x,y -> x-y}, '-', 2),
        new ValueArityPair({x,y -> x*y}, '*', 2),
        new ValueArityPair({x,y -> x/y}, '/', 2),
        new ValueArityPair({x-> Math.sin(x)}, 'Sin', 1),
        new ValueArityPair({x-> Math.cos(x)}, 'Cos', 1),
        new ValueArityPair({x-> Math.log(x)}, 'Log', 1)], variableArray = [101, 102], newTree = true) {
        //include max depth and size here
        this.functions = functions
        varArray = variableArray
        if (newTree) head = new FunctionNode(this, null, 0)
        //       varArray = new Object[problem.numVars]
    }

    def updateIndexes() {
        head.updateIndexes(0)
    }

    def size() {
        return head.size()
    }

    def depth() {
        return head.depth()
    }

    def eval() {
        return head.eval()
    }

    def search(index = random.nextInt(size())) {
        return searchHelper(head, index)
    }

    def searchHelper(currentNode, index){
        if (currentNode.getIndex == index) return currentNode
        if (currentNode.Arity == 2 && currentNode.children[1].getIndex <= index) return searchHelper(currentNode.children[1], index)
        return searchHelper(currentNode.children[0], index)
    }

    String toString() {
        "${head.eval()}\n${head.toString()}"
    }

    def clone() {
        def clone = new Tree(functions, varArray, false)
        println("${head}")
        clone.head = head.clone()
        println("${clone}")
        
    }

}