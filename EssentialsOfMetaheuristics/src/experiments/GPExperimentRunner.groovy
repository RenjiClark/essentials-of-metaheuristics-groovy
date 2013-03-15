package experiments

import geneticProgramming.SymbolicRegression
import populationMethods.GPGeneticAlgorithm

class GPExperimentRunner {

    static runExperiment(searchers, problems, numRuns = 50) {
        for (p in problems) {
            for (s in searchers) {
                for (i in 0..<numRuns) {
                    p.evalCount = 0
                    def result = s.maximize(p)
                    println "${s.toString()}\t${p.toString()}\t${p.quality(result)}\t${result}"
                }
            }
        }
    }

    static main(args) {
        def searchers = [
            new GPGeneticAlgorithm()
        ]
        def problems = [
            new SymbolicRegression({argz -> (argz[0]**2+argz[1]**2)**(1/2)},
            "(x**2+y**2)**(1/2)",
            [[0,0],[0,1],[0,2],[1,0],[1,1],[1,2],[2,0],[2,1],[2,2],
                [0,0],[0,1],[0,2],[1,0],[1,1],[1,2],[2,0],[2,1],[2,2]]
            )
            //            new SymbolicRegression(), //this is the default Sin(x**2)
            //            new SymbolicRegression({argz -> (argz[0]**2+argz[1]**2+argz[2]**2)**(1/2)},
            //            "(x**2+y**2+z**2)**(1/2)",
            //            [[0,0,0],[0,1,0],[0,2,0],[1,0,0],[1,1,0],[1,2,0],[2,0,0],[2,1,0],[2,2,0],
            //                [0,0,1],[0,1,1],[0,2,1],[1,0,1],[1,1,1],[1,2,1],[2,0,1],[2,1,1],[2,2,1],
            //                [0,0,2],[0,1,2],[0,2,2],[1,0,2],[1,1,2],[1,2,2],[2,0,2],[2,1,2],[2,2,2]]
            //            )
            //            new SymbolicRegression({argz -> 5}, "5", [[0],[0.5],[1],[1.5],[2],[2.5],[3],[3.5],[4],[4.5],[5],[5.5],[6],[6.5],[7],[7.5],[8],[8.5],[9],[9.5],[10]])
        ]
        // It would be nice to collect the total time here and include it in the
        // output.
        runExperiment(searchers, problems)
    }

}
