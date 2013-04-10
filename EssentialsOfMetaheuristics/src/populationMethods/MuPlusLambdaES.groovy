package populationMethods
import applications.robocode.RoboCodeProblem
import utility.GenomeFitnessPair;

class MuPlusLambdaES {
    Integer numParents = 2
    Integer numChildren = 6
    Random random = new Random()

    def maximize(problem){
        assert numParents > 0 && numChildren > 0, "Number of parents and children must be positive"
        assert numChildren % numParents == 0, "Number of children must be a multiple of the number of parents"

        def individualArr = []
        
        numChildren.times {
            def individual = problem.random()
            individualArr.add(new GenomeFitnessPair(genome: individual, fitness: problem.quality(individual)))
        }
        
        def best = new GenomeFitnessPair(genome: individualArr[0].genome, fitness: individualArr[0].fitness)

        while(!problem.terminate(best.genome, best.fitness)) {
            individualArr.sort()
            println("${individualArr[0].fitness > best.fitness}")
            if (individualArr[0].fitness > best.fitness) {
                best.genome = individualArr[0].genome
                best.fitness = individualArr[0].fitness
            }
            individualArr = individualArr[0..<numParents]

            for (i in 0..<numParents/2) {
                def crossovers = problem.crossover(individualArr[i*2].genome, individualArr[(i*2)+1].genome)
                individualArr.add(new GenomeFitnessPair(genome: crossovers[0], fitness: problem.quality(crossovers[0])))
                individualArr.add(new GenomeFitnessPair(genome: crossovers[1], fitness: problem.quality(crossovers[1])))
            }
           
            individualArr.sort()
            individualArr = individualArr[0..<numParents]

            for (i in 0..<numParents) {
                for (j in 0..<(numChildren / numParents)) {
                    def individual = problem.tweak(problem.copy(individualArr.get(i).genome))
                    individualArr.add(new GenomeFitnessPair(genome: individual, fitness: problem.quality(individual)))
                }
            }

        }
        return best.genome
    }

    String toString() {
        "MuPlusLambdaES_" + numParents + "_" + numChildren
    }
}