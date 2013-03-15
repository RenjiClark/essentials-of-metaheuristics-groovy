package geneticProgramming

import spock.lang.Specification
import javax.script.ScriptEngineManager
import javax.script.ScriptEngine

class TreeSpec extends Specification{

    def 'testing toString'() {
        when:
        //        ScriptEngineManager mgr = new ScriptEngineManager()
        //        ScriptEngine engine = mgr.getEngineByName("JavaScript")
        //        def someTree = new Tree()
        //        def someTree2 = new Tree()
        //        def someTree3 = new Tree()
        //        def someTree4 = new Tree()
        //        def someTree5 = new Tree()
        //        def someTree6 = new Tree()
        //        def someTree7 = new Tree()
        //        def someTree8 = new Tree()
        //        def someTree9 = new Tree()
        //        def someTree10 = new Tree()
        //        def someProb = new SymbolicRegression()
        //        def someTree = someProb.create()

        //        def someClone = someTree.clone()
        //        someClone.head = new FunctionNode(someClone, null, false)
        //        someClone.head.funcIndex = 4
        //        someClone.head.func = {x-> Math.sin(x)}
        //        someClone.head.key = 'Sin'
        //        someClone.head.Arity = 1
        //        someClone.head.children = []
        //        someClone.head.children.add(new LeafNode(someClone, someClone.head))
        //        someClone.head.children[0].value = 0
        //        someClone.updateIndexes()

        def bigDec = new BigDecimal(3)

        then:


        Math.cos(Math.PI) == -1
        
        
        //println(someTree)
        //       ((Double)bigDec).class == Double
        bigDec == 3
        //        bigDec <= 4
        //        bigDec.compareTo(4) < 0
        //        1==1

        //        someTree.search(0).value == Integer.MIN_VALUE
        //        someClone.search(1).value == 0
        //someTree.eval() == Eval.me(someTree.toString())
    }

}