package geneticProgramming

import java.util.Random;

class Tree implements Serializable{

	def head

	Random random = new Random()

	def functions
	def varArray

	def Tree(functions = [
		new ValueArityPair({x,y -> x+y}, '+', 2),
		new ValueArityPair({x,y -> x-y}, '-', 2),
		new ValueArityPair({x,y -> x*y}, '*', 2),
		new ValueArityPair({x,y -> x/y}, '/', 2),
		new ValueArityPair({x-> Math.sin(x)}, 'Sin', 1),
		new ValueArityPair({x-> Math.cos(x)}, 'Cos', 1),
		new ValueArityPair({x-> Math.log(x)}, 'Log', 1)
	], variableArray = [101, 102], newTree = true) {
		//include max depth and size here
		this.functions = functions
		varArray = variableArray
		if (newTree) head = new FunctionNode(this, null, 0)
		//       varArray = new Object[problem.numVars]
	}

	def updateIndexes() {
		head.updateIndexes(0)
	}

	def size() {
		return head.size()
	}

	def depth() {
		return head.depth()
	}

	def eval() {
		return head.eval()
	}

	def search(index = random.nextInt(size())) {
		return searchHelper(head, index)
	}

	def searchHelper(currentNode, index){
		if (currentNode.getIndex == index) return currentNode
		if (currentNode.Arity == 2 && currentNode.children[1].getIndex <= index) return searchHelper(currentNode.children[1], index)
		return searchHelper(currentNode.children[0], index)
	}

	String toString() {
		"${head.eval()}\n${head.toString()}"
	}

	def treeClone() {
		def bos = new ByteArrayOutputStream()
		def oos = new ObjectOutputStream(bos)
		oos.writeObject(this); oos.flush()
		def bin = new ByteArrayInputStream(bos.toByteArray())
		def ois = new ObjectInputStream(bin)
		return ois.readObject()
	}
	/*mutate takes a tree and an optional mutation index(default value is a random index within the tree. 
	 * Replaces the node at that index with either a new tree or a new LeafNode with a 50% probability. */
	def mutate(tree, mutateIndex = random.nextInt(tree.size)) {
		def node = tree.search(mutateIndex)
		if(random.nextBoolean()){
			node = new Tree()
		} else {
		def newNode = new LeafNode(tree, node.parent, 0)
		node = newNode
		}
		tree.updateIndexes()
	}
	
}