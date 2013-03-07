package geneticProgramming

class LeafNode extends Node{
   
    def value = random.nextInt(100)
    
    def Arity = 0
 
    def index
    
    def LeafNode(index){
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
    
}