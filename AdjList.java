import java.io.*;
import java.util.*;

/**
 * Adjacency list implementation for the AssociationGraph interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class AdjList extends AbstractAssocGraph
{

	CustomArrayList<String> vertexList = null;
	CustomArrayList<Edge> edgeList = null;
    /**
	 * Contructs empty graph.
	 */
    public AdjList() {
    	vertexList = new CustomArrayList<String>();
    	edgeList = new CustomArrayList<Edge>();

    } // end of AdjList()


    public void addVertex(String vertLabel) {
    	if (null != vertLabel) {
    		vertexList.add(vertLabel);
    	}
    } // end of addVertex()


    public void addEdge(String srcLabel, String tarLabel, int weight) {
    	if (null != srcLabel && null != tarLabel) {
    		edgeList.add(getEdge(srcLabel, tarLabel, weight));
    	}
        
    } // end of addEdge()

    /**
     * create new edge object return
     * @param srcLabel
     * @param tarLabel
     * @param weight
     * @return
     */
    private Edge getEdge(String srcLabel, String tarLabel, int weight) {
    	Edge edge = new Edge();
        edge.setSrcLabel(srcLabel);
        edge.setTarLabel(tarLabel);
        edge.setWeight(weight);
        return edge;
    }
    
	public int getEdgeWeight(String srcLabel, String tarLabel) {
		if (null != srcLabel && null != tarLabel) {
			for(int index = 0; index < edgeList.size(); index++){
				Edge edge = edgeList.get(index);
				if (srcLabel.equals(edge.getSrcLabel()) && tarLabel.equals(edge.getTarLabel()))
					return edge.getWeight();
			}
		}
		return EDGE_NOT_EXIST;
	} // end of existEdge()


	public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
		if (null != srcLabel && null != tarLabel) {
			for(int index = 0; index < edgeList.size(); index++){
				Edge edge = edgeList.get(index);
				if (srcLabel.equals(edge.getSrcLabel()) && tarLabel.equals(edge.getTarLabel())) {
					edge.setWeight(weight);
					break;
				}
			}
		}
    } // end of updateWeightEdge()


    public void removeVertex(String vertLabel) {
    	if (null != vertLabel) {
	    	vertexList.remove(vertLabel);
	    	CustomArrayList<Edge> removeEdgeList = new CustomArrayList<Edge>();
	    	for(int index = 0; index < edgeList.size(); index++){
	    		Edge edge = edgeList.get(index);
				if (null != edge && (vertLabel.equals(edge.getSrcLabel()) || vertLabel.equals(edge.getTarLabel()))) {
					removeEdgeList.add(edge);
				}
	    	}
	    	for(int index = 0; index < removeEdgeList.size(); index++){
	    		edgeList.remove(removeEdgeList.get(index));
	    	}
    	}
    } // end of removeVertex()



	public List<MyPair> inNearestNeighbours(int k, String vertLabel) {
	 	List<MyPair> neighbours = getAllNearestNeighbours(vertLabel, true);
		if (MINUS_ONE.equals(k)) 
			return neighbours;
		return getNearestNeighbours(neighbours, k);
		//		List<MyPair> neighboursList = getNearestNeighbours(neighbours, k);
//	       printMyPairValue(neighboursList, vertLabel);
//	      return neighboursList;
    
	} // end of inNearestNeighbours()
		/**
	* printMyPairValue
	* @param neighboursList
	* @param vertLabel
	*/
	private void printMyPairValue(List<MyPair> neighboursList, String vertLabel) {
	PrintWriter os = new PrintWriter(System.out, true);
	StringBuffer output = new StringBuffer();
	output.append(vertLabel + " ");
	if (neighboursList != null && !neighboursList.isEmpty()) {
	for(int index = 0; index < neighboursList.size(); index++){
	output.append(neighboursList.get(index).toString());
	}
	}
	os.println(output);
	}
	
	
	/**
	 * getAllInNearestNeighbours
	 * @param vertLabel
	 * @return
	 */
	private List<MyPair> getAllNearestNeighbours (String vertLabel, boolean tarLabel) {
		List<MyPair> neighbours = new ArrayList<MyPair>();
		if (null != vertLabel) {
			for(int index = 0; index < edgeList.size(); index++){
				Edge edge = edgeList.get(index);
				if (null != edge) {
					if (tarLabel) {
			    		if (vertLabel.equals(edge.getTarLabel())) {
			    			neighbours.add(new MyPair(edge.getSrcLabel(), edge.getWeight()));
			    		}
					} else {
						if (vertLabel.equals(edge.getSrcLabel())) {
			    			neighbours.add(new MyPair(edge.getTarLabel(), edge.getWeight()));
			    		}
					}
				}
	    	}
		}
		return neighbours;
	}
	
	private List<MyPair> getNearestNeighbours (List<MyPair> neighbours, int k) {
		List<MyPair> nearestNeighbours = new ArrayList<MyPair>();
		if (! MINUS_ONE.equals(k)) {
			neighbours.sort((a,b)->b.getValue().compareTo(a.getValue()));
			for (int index = 0; index < neighbours.size()-1; index++) {
				nearestNeighbours.add(neighbours.get(index));
			}
		}
		return nearestNeighbours;
	}

        public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
    	List<MyPair> neighbours = getAllNearestNeighbours(vertLabel, false);
		if (MINUS_ONE.equals(k)) 
			return neighbours;
		return getNearestNeighbours(neighbours, k);
   
	       } // end of outNearestNeighbours()


    public void printVertices(PrintWriter os) {
    	StringBuffer outPut = new StringBuffer();
    	for(int index = 0; index < vertexList.size(); index++){
    		String vertex = vertexList.get(index);
    		if (vertex != null) {
    			outPut.append(vertex)
    			  	  .append(BLANK);
    		}
		}
    	os.println(outPut);
    } // end of printVertices()


    public void printEdges(PrintWriter os) {
    	for(int index = 0; index < edgeList.size(); index++){
    		 Edge edge = edgeList.get(index);
    		 if (edge != null) {
    			 os.println(edge.toString());
    		 }
		}
    } // end of printEdges()
} // end of class AdjList