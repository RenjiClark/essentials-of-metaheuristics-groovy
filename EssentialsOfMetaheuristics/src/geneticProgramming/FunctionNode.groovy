package geneticProgramming

class FunctionNode extends Node{

    def tree
    def children = []
    def funcIndex = random.nextInt(tree.functions.size())
    def func = tree.functions[funcIndex].value
    def key = tree.functions[funcIndex].key
    def Arity = tree.functions[funcIndex].arity
    def index
    def parent

    def eval() {
        def child0Eval = children[0].eval()
        if (key == 'Log' && child0Eval.compareTo(0) <= 0) return 1
        if (Arity == 1) return func(child0Eval)
        
        def child1Eval = children[1].eval()
        if (key == '/' && Math.abs(child1Eval).compareTo(0.00001) < 0) return 1
        return func(child0Eval, child1Eval)
    }

    def FunctionNode(tree, parent, createChildren = true) {
        this.tree = tree
        this.parent = parent
        this.index = index

        Arity.times {
            def pickChild = random.nextInt(4)
            if(pickChild == 0) {
                children += new LeafNode(tree, this)
            } else if(pickChild == 1){
                if (tree.varArraySize != 0) {
                    children += new VarNode(tree, this)
                } else {
                    children += new LeafNode(tree, this)
                }
            } else {
                children += new FunctionNode(tree, this)
            }
        }
    }

    def updateIndexes(index){
        this.index = index
        
        children[0].updateIndexes(1 + index)
        if (Arity == 2) children[1].updateIndexes(1 + children[0].size() + index)
        
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
    
    def clone(treeC = this.tree, parentC = this.parent){
        def clone = new FunctionNode(treeC, parentC, false)
        clone.funcIndex = funcIndex
        clone.func = func
        clone.key = key
        clone.Arity = Arity
        clone.children = []
        clone.children.add(children[0].clone(treeC, clone))
        if (Arity == 2) clone.children.add(children[1].clone(treeC, clone))
        return clone
    }

}