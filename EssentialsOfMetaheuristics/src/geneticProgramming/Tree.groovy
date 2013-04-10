package geneticProgramming

import java.util.Random;

import com.sun.org.apache.xalan.internal.xsltc.dom.CurrentNodeListFilter;

class Tree{

    def head

    Random random = new Random()

    def functions
    def varArray
    def varArraySize
    def variables

    def Tree(
    functions = [new ValueArityPair({x,y -> x+y}, '+', 2),
        new ValueArityPair({x,y -> x-y}, '-', 2),
        new ValueArityPair({x,y -> x*y}, '*', 2),
        new ValueArityPair({x,y -> x/y}, '/', 2),
        new ValueArityPair({x,y-> x**y}, 'Math.pow', 2),
        new ValueArityPair({x-> Math.sin(x)}, 'Math.sin', 1),
        new ValueArityPair({x-> Math.cos(x)}, 'Math.cos', 1),
        new ValueArityPair({x-> Math.log(x)}, 'Math.log', 1)],
    varArraySize = 5,
    variables = ['getX()', 'getY()','distanceToNextDestination','direction','angle'],
    newTree = true
    ) {
        //include max depth and size here
        this.functions = functions
        this.varArraySize = varArraySize
        this.variables = variables
        if (newTree){
            head = new FunctionNode(this, null)
            updateIndexes()
        }
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

    def eval(varArray) {
        this.varArray = varArray
        return head.eval()
    }

    def search(index = random.nextInt(size())) {
        return searchHelper(head, index)
    }

    def searchHelper(currentNode, index){
//        print("${currentNode.index} ${index}")
//        if (currentNode.Arity == 2){
//            println(" ${currentNode.children[1].index}")
//        } else {
//            println()
//        }
//        println(this)
//        if (currentNode.Arity == 0 && currentNode.index != index) println("fail")
        if (currentNode.index == index) return currentNode
        if (currentNode.Arity == 2 && currentNode.children[1].index <= index) return searchHelper(currentNode.children[1], index)

//        if (currentNode.Arity == 0) return currentNode

        return searchHelper(currentNode.children[0], index)
    }

    String toString() {
        "${head.toString()}"
    }

    def clone(){
        
        def clone = new Tree(functions, varArraySize, variables, false)
        clone.head = head.clone(clone, null)
        clone.updateIndexes()
        return clone
    }
    
    def test(num){
        
    }
    

}