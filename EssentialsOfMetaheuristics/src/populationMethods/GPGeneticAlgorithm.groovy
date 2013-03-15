package populationMethods

import java.util.Random
import operators.Crossovers
import operators.TournamentSelection

import geneticProgramming.*

class GPGeneticAlgorithm {
    // Algorithm 20

    // We need popsize to be global so that we can use it in the toString method, also added a default value
    def popsize = 100

    // Our Algorithm takes a Genetic Algorithm Problem, a desired population size
    def maximize(problem, populationSize=popsize, selector=new TournamentSelection(), crossover=new Crossovers().treeCrossover) {
        popsize = populationSize

        def startingPopulation = [] //as Set

        //        popsize.times {
        //            def toAdd = problem.random()
        //            startingPopulation.add(toAdd) // Add a new random individual
        //        }

        for(int i = 0; i < popsize; i++){
            def toAdd = problem.random()
            startingPopulation.add(toAdd) // Add a new random individual
        }

        def best = problem.create()
        def qualityOfBest = problem.quality(best)
        while(!problem.terminate(best, qualityOfBest)) {
//          for(def individual: startingPopulation) {
            for(int i = 0; i < popsize; i++){
                //                println("${individual}")
//                def newQuality = problem.quality(individual)
//                println("Processing ${startingPopulation}")
//                println("${i} ${i.class == Integer}")
                def newQuality = problem.quality(startingPopulation[i])
                if(newQuality > qualityOfBest) {
                    best = startingPopulation[i]
//                    best = individual
                    qualityOfBest = newQuality
                }

            }

            def endingPopulation = [] //as Set

//            for(i in 0..(popsize/2)) {
            for(int i = 0; i < popsize/2; i++) {
                def parentA = selector.select(problem, startingPopulation)
                def parentB = selector.select(problem, startingPopulation)
                def children = crossover(parentA, parentB)
                endingPopulation.add(problem.tweak(children[0]))
                endingPopulation.add(problem.tweak(children[1]))
                
            }
            startingPopulation = endingPopulation
        }
        return best
    }

    String toString() {
        "GA_" + popsize
    }

}
