package geneticProgramming

class ValueArityPair implements Serializable{
    
    def value
    def key
    def arity
    
    def ValueArityPair(value, key, arity) {
        this.value = value
        this.arity = arity
        this.key = key
    }
    
}