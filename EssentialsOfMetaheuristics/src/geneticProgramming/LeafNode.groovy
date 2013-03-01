package geneticProgramming

class LeafNode extends Node{
   
    def value = random.nextInt(100)
    
    def Arity = 0
    
    def children = null
 
    def eval() {
        return value
    }
       
    def depth = 0
    
    String toString() {
        "${value}"
    }
    
}