package geneticProgramming

class LeafNode extends Node{
   
    def value = random.nextInt(100)
    
    def Arity = 0
    
    def tree
    def parent
    def index
    
    def LeafNode(tree, parent, index){
        this.tree = tree
        this.parent = parent
        this.index = index
        
        if(random.nextBoolean()) {
            value = random.nextInt(100)
        } else {
            value = tree.varArray[random.nextInt(tree.varArray.size)]
        }
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
    
}