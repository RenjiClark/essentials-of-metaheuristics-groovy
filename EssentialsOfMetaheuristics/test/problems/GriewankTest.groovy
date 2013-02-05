package problems

import spock.lang.Specification

class GriewankTest extends Specification {
    final numValues = 1
    def griewank = new Griewank()

    def "create takes an argument that specifies how many zeros"() {
        expect:
        griewank.create(numValues, [Math.PI*Math.PI/4]) == [Math.PI*Math.PI/4]
    }

    def "Quality Function Works Correctly"() {
        expect:
        //Since floats are prone to roundoff error, here instead of using == 0 we used a range
        griewank.quality([Math.PI*Math.PI/4]) < Math.PI*Math.PI/16000 + 1.00000000001 && griewank.quality([Math.PI*Math.PI]) > Math.PI*Math.PI/16000 - 0.00000000001
    }
}