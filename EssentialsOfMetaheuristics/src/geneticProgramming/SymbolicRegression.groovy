package geneticProgramming

import java.util.Random;

class SymbolicRegression{

    Random random = new Random()

    def evalCount = 0
    def functionNodesAllowed
    def functionToMatch
    def inputs
    def funcToString
    def maxIterations

    def quality(tree){
        evalCount++

        def diffSum = 0
        for(int i = 0; i < inputs.size(); i++){
//            println("${functionToMatch(inputs[i])} ${tree.eval(inputs[i])}")
//            if ("${tree.eval(inputs[i])}" == 'NaN') println("${tree}")
            diffSum += Math.abs(functionToMatch(inputs[i]) - tree.eval(inputs[i]))
        }
//        println("Finished Quality ${tree}")
        return -diffSum //made negative because our searchers assume maximization, and we want minimization
    }

    String toString() {
        return "SymReg_${funcToString}"
    }

    def SymbolicRegression(
    functionToMatch = {args -> Math.sin(args[0]**2)},
    funcToString = "Sin(x**2)",
    inputs = [[0],[0.5],[1],[1.5],[2],[2.5],[3],[3.5],[4],[4.5],[5],[5.5],[6],[6.5],[7],[7.5],[8],[8.5],[9],[9.5],[10]],
    functionNodesAllowed =
    [new ValueArityPair({x,y -> x+y}, '+', 2),
        new ValueArityPair({x,y -> x-y}, '-', 2),
        new ValueArityPair({x,y -> x*y}, '*', 2),
        new ValueArityPair({x,y -> x/y}, '/', 2),
        new ValueArityPair({x-> Math.sin(x)}, 'Sin', 1),
        new ValueArityPair({x-> Math.cos(x)}, 'Cos', 1),
        new ValueArityPair({x-> Math.log(x)}, 'Log', 1)],
    maxIterations = 1000
    ){
        this.functionNodesAllowed = functionNodesAllowed
        this.functionToMatch = functionToMatch
        this.inputs = inputs
        this.funcToString = funcToString
        this.maxIterations = maxIterations

    }

    def random(){
        def randTree = new Tree(this, functionNodesAllowed, inputs[0].size)
        return randTree
    }

    def create(){
        def tempTree = new Tree(this, functionNodesAllowed, inputs[0].size, false)
        tempTree.head = new LeafNode(tempTree, null)
        tempTree.head.value = Integer.MIN_VALUE
        return tempTree
    }

    def terminate = { a, q = quality(a) ->
        evalCount >= maxIterations
    }

    def tweak(origTree) {
        def tree = origTree.clone()
        def nodeToTweak = tree.search(random.nextInt(tree.size()))

        if (nodeToTweak.isHead()) {
            tree = new Tree(this, nodeToTweak.tree.functions, inputs[0].size())
        } else if (nodeToTweak.isSecondChild()){
            nodeToTweak.parent.children[1] = new FunctionNode(tree, null, 0)
        } else {
            nodeToTweak.parent.children[0] = new FunctionNode(tree, null, 0)
        }
        tree.updateIndexes()
        return tree
    }

}
