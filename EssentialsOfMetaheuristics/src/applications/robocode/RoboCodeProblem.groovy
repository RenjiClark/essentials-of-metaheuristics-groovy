package applications.robocode
import geneticProgramming.*
import operators.Crossovers

/*
 *   id : an id used in the generation of the name of the class.
 *   enemy_energy : the coefficient for the enemy's energy
 *   my_energy : the coefficient for our energy
 *   angle_diff : the coefficient for the different in angles between us and the point and then and the point
 *   distance : the coefficient for the distance between the point and the enemy
 */

import geneticProgramming.Tree

class RoboCodeProblem {
    Integer individualCount = 0
    Integer evalCount = 0
    Random random = new Random()
    public static final STDEV = 10

    def random() { // change to have the random tree creation
        ++individualCount
        [ 'id' : individualCount,
                    'ranEqLine' : new Tree()]
        
        //['id': individualCount, 'tree': tree.random()]
    }

    def copy(individual) {
        def clone = ['id': null, 'ranEqLine':null]
        ++individualCount
        clone['id'] = individualCount
        clone['ranEqLine'] = individual['ranEqLine'].clone()
        return clone
    }

    def tweak(individual) {
        def tree = individual['ranEqLine'].clone()
        def nodeToTweak = tree.search(random.nextInt(tree.size()))

        if (nodeToTweak.isHead()) {
            tree = new Tree(nodeToTweak.tree.functions)
        } else if (nodeToTweak.isSecondChild()){
            nodeToTweak.parent.children[1] = new FunctionNode(tree, null)
        } else {
            nodeToTweak.parent.children[0] = new FunctionNode(tree, null)
        }
        tree.updateIndexes()
        individual['ranEqLine'] = tree
        return individual
    }
    
    def crossover(individualA, individualB) {
        def crossovers = new Crossovers()
        def treePair = crossovers.treeCrossover(individualA['ranEqLine'], individualB['ranEqLine'])
        ++individualCount
        def mapA = ['id' : individualCount, 'ranEqLine' : treePair[0]]
        ++individualCount
        def mapB = ['id' : individualCount, 'ranEqLine' : treePair[1]]
        return [mapA, mapB]
    }

    def quality(individual) { // make it call RobotBuilder then BattleRunner then return the quality we care about
        ++evalCount
        if (individual['ranEqLine'].size() > 20 && random.nextInt(10) < 3) return 0
        def bobTheBuilder = new RobotBuilder("templates/HawkOnFireOS.template")
        bobTheBuilder.buildJarFile(individual)
        def battleRunner = new BattleRunner("templates/battle.template")
        battleRunner.buildBattleFile(individual['id'])

        return battleRunner.runBattle(individual['id'])
    }

    def terminate(bestIndividual, bestQuality) {
        evalCount > 30
    }
}
