package geneticProgramming

import spock.lang.Specification

class LeafNodeSpec extends Specification{
    
    def 'testing toString'() {
        when:
        def someNode = new LeafNode(value : 3)
    
        then:
        someNode.toString() == '3'
    }
    
    
}