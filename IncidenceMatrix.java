import java.io.*;
import java.util.*;


/**
 * Incident matrix implementation for the AssociationGraph interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class IncidenceMatrix extends AbstractAssocGraph
{

//	DataGenerator data = new DataGenerator();
	
	private int[][] incidentMatrix;
	private String[] edgeLabels;
	Map <String, Integer> vertices;
	Map <String, Integer> edges;
	
	//current size of the matrix
	private int mSize = 0;
	private int nEdges = 0;
	long start = 0;
	long end = 0;
	
	//System.out.println(print);
	private int size = 11000;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public IncidenceMatrix() {
    	
		incidentMatrix = new int[size][size];
		edgeLabels = new String[size];
		vertices = new HashMap();
		edges = new HashMap();

    	//creates an empty matrix
    	clearMatrix();
    	
    	
    	
    } // end of IncidentMatrix()

    private void clearMatrix() {
    	
    	//generates an empty incident matrix
    	//each of the components are listed as 0
    	for (int i=0; i<size;i++)
    	{
    		for (int j=0;j<size;j++)
    			incidentMatrix[i][j]=0;
    	}
    }
    
    
    
    public void addVertex(String vertLabel) {

    	//adds a new vertex to the Hashmap and increases the size of the matrix by 1
    	vertices.put(vertLabel, mSize);
    	mSize++;
    	
    } // end of addVertex()
    
    public void addEdge(String srcLabel, String tarLabel, int weight) {
    	try {
	    	//Set the edge
	    	String edgeName = srcLabel+";"+tarLabel;
	    	
	    	//records the label for the edge
	    	edgeLabels[nEdges] = edgeName;
	    	
//	    	Checks for the presence of Source and Target Label and if the weight is greater than 0
	    	if (vertices.containsKey(srcLabel) && vertices.containsKey(tarLabel) && weight>0)
	    	{
	    		//confirm that the edge does not exist
	    		if (!edges.containsKey(edgeName))
	    		{
	    			edges.put(edgeName, nEdges);
	    			
	    			//gets the location of the source vertex
	    			int src = vertices.get(srcLabel);
	    		
	    			//updates the weight of the source
	    			incidentMatrix[src][nEdges] = weight;
	    			
	    			//gets the location of the target vertex
	    			int tar = vertices.get(tarLabel);
	    			
	    			//The target is updated with negative value
	    			incidentMatrix[tar][nEdges] = weight*-1;
	    			
	    			//increases the number of edges
	    			nEdges++;
	    		}
	    	}	
    	} catch(Exception e) {
    		
    	}
    } // end of addEdge()


	public int getEdgeWeight(String srcLabel, String tarLabel) {
		
		start = System.nanoTime();
		//determines the name of the edges
		String edgeName = srcLabel+";"+tarLabel;
		
		//confirms the edge exists
		if(edges.containsKey(edgeName))
		{
			//gets the location of the edge
			int edgeLocation = edges.get(edgeName);
			
			//gets the location of the source
			int sourceLocation = vertices.get(srcLabel);
			
			//gets the weight of the edge
			int weight = incidentMatrix[sourceLocation][edgeLocation];
		
			end = System.nanoTime();
	//    	System.out.println("time taken = "+getEstimatedTime(start, end));
			
			return weight;
		} 

		// update return value
		return EDGE_NOT_EXIST;
	} // end of existEdge()


	public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
		
		//determine the name of the edges
		String edgeName = srcLabel+";"+tarLabel;
		
		start = System.nanoTime();
//    	Checks for the presence of Source and Target Label and if the weight is greater than 0

		if (edges.containsKey(edgeName) && weight>=0)
		{	
			//gets the location of the edge
			int edgeLocation = edges.get(edgeName);
		
			//gets the location of the source and the target
			int sourceLocation = vertices.get(srcLabel);
			int targetLocation = vertices.get(tarLabel);
	
			//updates the weight of the source and the target
			incidentMatrix[sourceLocation][edgeLocation] = weight;
			incidentMatrix[targetLocation][edgeLocation] = weight*-1;
			
			//if the weight is 0, removes the edge from the map
			if(weight==0)
				edges.remove(edgeName);
		}
		end = System.nanoTime();
		

    } // end of updateWeightEdge()


    public void removeVertex(String vertLabel) {
    	
    	start = System.nanoTime();
    	//confirms the vertex exists
    	if (vertices.containsKey(vertLabel))
    	{
    		//gets the location of the vertex
    		int vertexLocation = vertices.get(vertLabel);
    	
    		//iterates through the array and returns the location of the edges
    		for (int i=0;i<nEdges; i++)
    		{
    		
    			//if the value of the edge is no equal to 0, then there is an edge
    			if(incidentMatrix[vertexLocation][i]!=0)
    			{
    				//gets the name of the edge
    				String edgeName = edgeLabels[i];
    				
    				//System.out.println(edgeName);
    				
    				//splits the string to get the target
    				String[] split = edgeName.split(";");
    			
    				//removes the edge from the map
    				edges.remove(edgeName);
    				
    				//if it is greater than 0 we need to get rid of the target
    				if (incidentMatrix[vertexLocation][i]>0)
    				{   
    					//gets the location of the target vertex
        				int targetVertex = vertices.get(split[1]);
	    				
        				//clears the cell
        				incidentMatrix[targetVertex][i]=0;
        				
    				} else {
    				//otherwise we get rid of the source
    					
        				//gets the location of the source vertex
        				int targetVertex = vertices.get(split[0]);
        				    				
        				//clears the cell
        				incidentMatrix[targetVertex][i]=0;
    					
    				}
    			    				
    				//resets the value of the matrix to 0
    				incidentMatrix[vertexLocation][i]=0;
    				
    				
    			}
    		}
    		
        	//removes the edge
        	vertices.remove(vertLabel);
        	
        	end = System.nanoTime();
        	
    	}
    	
    } // end of removeVertex()


	public List<MyPair> inNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();
        
        start = System.nanoTime();
        //confirms the existence of the edge
        if (vertices.containsKey(vertLabel))
        {
        	//gets the location of the vertex
        	int vertexLocation = vertices.get(vertLabel);
        	
        	//we want all of the in edges
        	if (k==-1)
        	{
        		//goes through each of the edges
        		for (int i=0; i<nEdges;i++)
        		{
        			//if it is less than 1, we want the edge
        			if (incidentMatrix[vertexLocation][i]<0)
        			{
        				String edgeName[] = edgeLabels[i].split(";");

        				//creates a new pair and adds it to the list
        				MyPair pair = new MyPair(edgeName[0],
        						incidentMatrix[vertexLocation][i]*-1);
        				neighbours.add(pair);
        			}
        		}
        	} else {
        		//otherwise we only want the nearest k so we set the values
        		int count=0;
        		int nearest=0;
        		int[] foundEdges = new int[k];
        		
        		//we go through the list k number of times
        		while(count<k)
        		{
        			for (int i=0;i<nEdges;i++)
        			{
        				boolean alreadyUsed=false;
        				
        				//determines whether this is the largest so if
        				if(incidentMatrix[vertexLocation][i]<nearest)
        				{   
        					//checks to see if we have already used it
        					for (int j=0;j<count;j++)
        					{
        						//if i has been recorded we skip it
        						if (i==foundEdges[j])
        							alreadyUsed=true;
        					}
        					
        					if(!alreadyUsed)
        					{
        						//if it is, it records the location
        						nearest=i;
        					}
        				}
        			}
        			
        			//once it is done, we have the largest so we add it to the list
    				String edgeName[] = edgeLabels[nearest].split(";");

    				//creates a new pair and adds it to the list
    				MyPair pair = new MyPair(edgeName[0],
    						incidentMatrix[vertexLocation][nearest]*-1);
    				neighbours.add(pair);
    				
    				//we record the location of the edge
    				foundEdges[count]=nearest;
    				count++;
        		}
        	}
        }
        
        end = System.nanoTime();
        
        return neighbours;
    } // end of inNearestNeighbours()

    public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();
        
        start = System.nanoTime();
        //confirms the existence of the edge
        if (vertices.containsKey(vertLabel))
        {
        	//gets the location of the vertex
        	int vertexLocation = vertices.get(vertLabel);
        	
        	//we want all of the in edges
        	if (k==-1)
        	{
        		//goes through each of the edges
        		for (int i=0; i<nEdges;i++)
        		{
        			//if it is less than 1, we want the edge
        			if (incidentMatrix[vertexLocation][i]>0)
        			{
        				String edgeName[] = edgeLabels[i].split(";");

        				//creates a new pair and adds it to the list
        				MyPair pair = new MyPair(edgeName[1],
        						incidentMatrix[vertexLocation][i]);
        				neighbours.add(pair);
        			}
        		}
        	} else {
        		//otherwise we only want the nearest k so we set the values
        		int count=0;
        		int nearest=0;
        		int[] foundEdges = new int[k];
        		
        		//we go through the list k number of times
        		while(count<k)
        		{
        			for (int i=0;i<nEdges;i++)
        			{
        				boolean alreadyUsed=false;
        				
        				//determines whether this is the largest so if
        				if(incidentMatrix[vertexLocation][i]>nearest)
        				{   
        					//checks to see if we have already used it
        					for (int j=0;j<count;j++)
        					{
        						//if i has been recorded we skip it
        						if (i==foundEdges[j])
        							alreadyUsed=true;
        					}
        					
        					if(!alreadyUsed)
        					{
        						//if it is, it records the location
        						nearest=i;
        					}
        				}
        			}
        			
        			//once it is done, we have the largest so we add it to the list
    				String edgeName[] = edgeLabels[nearest].split(";");

    				//creates a new pair and adds it to the list
    				MyPair pair = new MyPair(edgeName[1],
    						incidentMatrix[vertexLocation][nearest]);
    				neighbours.add(pair);
    				
    				//we record the location of the edge
    				foundEdges[count]=nearest;
    				count++;
        		}
        	}
        }

        end = System.nanoTime();
        		
       // System.out.println("time taken = "+getEstimatedTime(start, end));
        
        return neighbours;
    } // end of outNearestNeighbours()


    public void printVertices(PrintWriter os) {
    	
    	String print="";
    	
    	for (String vertex:vertices.keySet())
    		print = print+vertex+" ";

    	os.println(print);

    } // end of printVertices()

    public void printEdges(PrintWriter os) {
    	
    	String print="";
    	
    	//iterate through the matrix
    	for (int i=0;i<mSize;i++)
    	{
    		for (int j=0;j<nEdges;j++)
    		{
    			//if the cell is greater than 0, we have an edge
    			if (incidentMatrix[i][j]>0)
    			{
    				//we get the name of the edge and record that and the weight
    				String edgeName[] = edgeLabels[j].split(";");
    				
    				print=print+edgeName[0]+" "+edgeName[1]+" "+incidentMatrix[i][j]+"\n";
    			}
    		}
    	}
    	
    	os.println(print);
    	
    } // end of printEdges()

} // end of class IncidenceMatrix