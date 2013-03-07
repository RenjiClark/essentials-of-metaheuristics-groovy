package geneticProgramming

import spock.lang.Specification
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

class TreeSpec extends Specification{

    def 'testing toString'() {
        when:
//        ScriptEngineManager mgr = new ScriptEngineManager();
//        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        def someTree = new Tree()
        def someTree2 = new Tree()
        def someTree3 = new Tree()
        def someTree4 = new Tree()
        def someTree5 = new Tree()
        def someTree6 = new Tree()
        def someTree7 = new Tree()
        def someTree8 = new Tree()
        def someTree9 = new Tree()
        def someTree10 = new Tree()

        then:
        println(someTree)
        1==1
        //someTree.eval() == Eval.me(someTree.toString())
    }

}