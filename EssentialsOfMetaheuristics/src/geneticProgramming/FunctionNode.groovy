package geneticProgramming

class FunctionNode extends Node{

    def tree
    def children = []
    def funcIndex = random.nextInt(tree.functions.size())
    def func = tree.functions[funcIndex].value
    def key = tree.functions[funcIndex].key
    def Arity = tree.functions[funcIndex].arity
    def index
    //    def functions = problem.functions
//    def functions = [new ValueArityPair({x,y -> x+y}, '+', 2),
//        new ValueArityPair({x,y -> x-y}, '-', 2),
//        new ValueArityPair({x,y -> x*y}, '*', 2),
//        new ValueArityPair({x,y -> x/y}, '/', 2),
//        new ValueArityPair({x-> Math.sin(x)}, 'Sin', 1),
//        new ValueArityPair({x-> Math.cos(x)}, 'Cos', 1),
//        new ValueArityPair({x-> Math.log(x)}, 'Log', 1)]

    def eval() {
        def child0Eval = children[0].eval()
        if (key == '/' && child0Eval == 0) return 1
        if (key == 'Log' && child0Eval < 0) return 1
        if (Arity == 1) return func(child0Eval)
        return func(child0Eval, children[1].eval())
    }

    def FunctionNode(tree, index) {
        this.tree = tree
        this.index = index
        index++
        
        def currentChild = 0
        Arity.times {
            if (currentChild == 1) {
                index += children[0].size()
            } else {
                currentChild++
            }

            def childIsLeaf = random.nextBoolean()
            if(childIsLeaf) {
                children += new LeafNode(tree, index)
            } else {
                children += new FunctionNode(tree, index)
            }
        }
    }

    def updateIndexes(index){
        this.index = index
        index++

        def currentChild = 0
        for(child in children){
            if (currentChild == 1) {
                index += child.size()
            } else {
                currentChild++
            }
            child.updateIndexes(index)
        }
    }

    @Override
    String toString() {
        if (Arity == 1) return "${key}(${children[0]})"
        return "(${children[0]})${key}(${children[1]})"
    }

    def depth() {
        if (Arity == 1) return (children[0] + 1)
        if (children[0].depth() > children[1].depth()) {
            return (children[0].depth() + 1)
        }
        return (children[1].depth() + 1)
    }

}