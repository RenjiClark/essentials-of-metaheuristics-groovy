package geneticProgramming

class LeafNode extends Node{
   
    def value = random.nextInt(100)
    
    def Arity = 0
    
    def tree
    def parent
    def index
    
    def LeafNode(tree, parent){
        this.tree = tree
        this.parent = parent
        this.index = index
    }
    
    def eval() {
        return value
    }
    
    def depth = 0
    
    def updateIndexes(index){
        
        this.index = index
        
    }
    
    String toString() {
        "${value}"
    }
    
    def clone(treeC = this.tree, parentC = this.parent){
        def clone = new LeafNode(treeC, parentC)
        clone.value = value
        return clone
    }
    
}