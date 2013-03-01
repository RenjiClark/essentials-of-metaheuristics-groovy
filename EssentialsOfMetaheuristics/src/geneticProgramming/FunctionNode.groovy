package geneticProgramming

class FunctionNode extends Node{

    def functions = [new ValueArityPair({x,y -> x+y}, '+', 2),
        new ValueArityPair({x,y -> x-y}, '-', 2),
        new ValueArityPair({x,y -> x*y}, '*', 2),
        new ValueArityPair({x,y -> x/y}, '/', 2),
        new ValueArityPair({x-> Math.sin(x)}, 'Sin', 1),
        new ValueArityPair({x-> Math.cos(x)}, 'Cos', 1),
        new ValueArityPair({x-> Math.log(x)}, 'Log', 1)]

    def children = []

    def eval() {
        def child0Eval = children[0].eval()
        if (key == '/' && child0Eval == 0) return 1
        if (key == 'Log' && child0Eval < 0) return 1
        
        if (Arity == 1) return func(child0Eval)
        return func(child0Eval, children[1].eval())
    }
    def funcIndex = random.nextInt(functions.size())
    def func = functions[funcIndex].value
    def key = functions[funcIndex].key
    def Arity = functions[funcIndex].arity
    
    def FunctionNode() {
        Arity.times {
            def childIsLeaf = random.nextBoolean()
            if(childIsLeaf) {
                children += new LeafNode()
            } else {
                children += new FunctionNode()
            }
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