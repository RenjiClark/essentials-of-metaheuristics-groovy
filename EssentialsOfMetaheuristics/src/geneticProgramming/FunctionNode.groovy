package geneticProgramming

class FunctionNode extends Node{
    
    def Arity
    
    def children
    
    def FunctionNode(arity) {
        Arity = arity
        children = Node[Arity]
    }
    
}