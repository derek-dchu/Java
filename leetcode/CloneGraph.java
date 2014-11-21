/**
 * LeetCode OJ: Clone Graph 
 */


import java.util.*;

public class CloneGraph {
	static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	};
	
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) return null;
		
        ArrayList<UndirectedGraphNode> visited = new ArrayList<>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> oldNewNodePair = new HashMap<>();
        
        visited.add(node);
        oldNewNodePair.put(node,  new UndirectedGraphNode(node.label));
        
        int i = 0;
        while (i < visited.size()) {
        	UndirectedGraphNode currOld = visited.get(i++);
        	Iterator<UndirectedGraphNode> iterNeighbor = currOld.neighbors.iterator();
        	while (iterNeighbor.hasNext()) {
        		UndirectedGraphNode neighbor = iterNeighbor.next();
        		
        		if (!oldNewNodePair.containsKey(neighbor)) {
        			oldNewNodePair.put(neighbor, new UndirectedGraphNode(neighbor.label));
        			visited.add(neighbor);
        		}
        	}
        }
        
        Iterator<UndirectedGraphNode> iter = visited.iterator();
        while (iter.hasNext()) {
        	UndirectedGraphNode currOld = iter.next();
        	UndirectedGraphNode currNew = oldNewNodePair.get(currOld);
        	Iterator<UndirectedGraphNode> iterNeighbor = currOld.neighbors.iterator();
        	while (iterNeighbor.hasNext()) {
        		UndirectedGraphNode currNeighbor = iterNeighbor.next();
        		currNew.neighbors.add(oldNewNodePair.get(currNeighbor));
        	}
        }
        
        return oldNewNodePair.get(node);
	}
	
	public static void main(String[] args) {
		UndirectedGraphNode oldGraph = new UndirectedGraphNode(0);
		oldGraph.neighbors.add(oldGraph);
		oldGraph.neighbors.add(oldGraph);
		
		CloneGraph solution = new CloneGraph();
		UndirectedGraphNode newGraph = solution.cloneGraph(oldGraph);
		System.out.println(newGraph.neighbors);
	}
}
