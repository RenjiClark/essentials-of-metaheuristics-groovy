package operators

class Crossovers{
    static random = new Random()

    def onePointCrossover = { father, mother, crossoverPoint = random.nextInt(father.size) ->
        [
            father[0..<crossoverPoint] + mother[crossoverPoint..<mother.size],
            mother[0..<crossoverPoint] + father[crossoverPoint..<father.size]
        ]
    }

    def twoPointCrossover = {father, mother,
        xoMin = random.nextInt(father.size),
        xoMax = random.nextInt(mother.size)->
        if(xoMin > xoMax){
            (xoMin, xoMax) = [xoMax, xoMin]
        }
        [
            father[0..<xoMin] + mother[xoMin..<xoMax] + father[xoMax..<father.size],
            mother[0..<xoMin] + father[xoMin..<xoMax] + mother[xoMax..<father.size]
        ]
    }

    def uniformCrossover = {father, mother, probability = 1/father.size ->
        def f = []
        def m = []
        for(i in 0..father.size-1){
            if(probability >= random.nextFloat()){
                f += mother[i]
                m += father[i]
            } else {
                f += father[i]
                m += mother[i]
            }
        }
        [f,m]
    }

    def treeCrossover = {father, mother ->
        def fatherClone = father.clone()
        def motherClone = mother.clone()
        def fatherXO = random.nextInt(fatherClone.size())
        def motherXO = random.nextInt(motherClone.size())

//        if (father.size() != fatherClone.size()) println("Father Failed ${father} ${fatherClone}")
//        if (mother.size() != motherClone.size()) println("Mother Failed ${mother} ${motherClone}")
        
//        println("Crossover Print ${fatherClone.size()} ${fatherXO} ${motherClone.size()} ${motherXO}")
        def fatherSeed = fatherClone.search(fatherXO)
        def fatherSeedClone = fatherSeed.clone()

        def motherEgg = motherClone.search(motherXO)

        if (fatherSeed.isHead()){
            fatherSeed.tree.head = motherEgg
        } else if (fatherSeed.isSecondChild()) {
            fatherSeed.parent.children[1] = motherEgg
        } else {
            fatherSeed.parent.children[0] = motherEgg
        }

        if (motherEgg.isHead()){
            motherEgg.tree.head = fatherSeedClone
        } else if (motherEgg.isSecondChild()) {
            motherEgg.parent.children[1] = fatherSeedClone
        } else {
            motherEgg.parent.children[0] = fatherSeedClone
        }

        fatherClone.updateIndexes()
        motherClone.updateIndexes()
        return [fatherClone, motherClone]
    }
}