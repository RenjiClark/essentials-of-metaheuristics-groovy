package geneticProgramming

class LeafNode extends Node{
   
    def value = random.nextInt(100)
    
    def Arity = 0
 
    def index
    def tree
    
    def LeafNode(tree, index){
        this.tree = tree
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