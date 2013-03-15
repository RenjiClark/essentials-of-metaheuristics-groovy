package geneticProgramming

class VarNode extends Node{

    def indexInVarArray = -1

    def Arity = 0

    def tree
    def parent
    def index

    def VarNode(tree, parent){
        this.tree = tree
        this.parent = parent
        this.index = index

        if (tree.varArraySize != 0) {
            indexInVarArray = random.nextInt(tree.varArraySize)
        }

    }

    def eval() {
//        if (indexInVarArray == -1) return 0
        return tree.varArray[indexInVarArray]
    }

    def depth = 0

    def updateIndexes(index){

        this.index = index

    }

    String toString() {
        return "var${indexInVarArray}"
    }
    
    def clone(treeC = this.tree, parentC = this.parent){
        def clone = new VarNode(treeC, parentC)
        clone.indexInVarArray = indexInVarArray
        return clone
    }

}