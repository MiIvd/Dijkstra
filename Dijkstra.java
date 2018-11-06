import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;


/*
 * Group 29
 * Group Members
 * Yonas Lemmi
 * Milad Balkhinezhad
 * Rasmus Grunnet-Jepsen
*/

public class Dijkstra {
	
	int LENGTH = 233;
	
	public static void main(String[] args) throws IOException {
			int LENGTH = 233;
			int START = 225;
			int DESTINATION = 7;
			int totalCost = 0;
		
		
		int[] hexagon = new int[LENGTH];
		Scanner keyboard = null;
		
		try {
			keyboard = new Scanner(new File("INPUT.TXT"));
			int hexagonCount  = 0;
			while (hexagonCount < LENGTH) {
				int index = keyboard.nextInt();
				int cost = keyboard.nextInt();
				hexagon[index-1] = cost;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(NoSuchElementException e) {
			
		}

	int[] distance = new int[LENGTH];
		for(int i=0; i<233; i++){
			if(i==225){
			distance[225] = 0;
			}
			else{
			distance[i] = Integer.MAX_VALUE;
			}
		
		}
	boolean[] visited = new boolean[233];
	
	boolean[] tobenamed = new boolean[LENGTH];
	
	boolean nodesLeft = true;
	int currentNode;
	int neighbors[] = new int[6];
	int parent[] = new int[LENGTH];
	
	distance[225] = hexagon[225];
	
	while(nodesLeft) {
		currentNode = FindMinUnseen(distance, visited);
		if (currentNode == -1)
			break;
		//System.out.println("currentnode: " + currentNode);
		
		visited[currentNode] = true;
		neighbors = SetNeighbors(currentNode, neighbors);
		
		//System.out.println(neighbors[1]);
		for (int i = 0; i < 6; i++) {
			//System.out.println("neigh i: " + neighbors[i]);
			if (neighbors[i] != -1) {
				if ( hexagon[neighbors[i]] != -1) {
					if(distance[currentNode] + hexagon[neighbors[i]] < distance[neighbors[i]]) {
						distance[neighbors[i]] = distance[currentNode] + hexagon[neighbors[i]];
						parent[neighbors[i]] = currentNode;
				}
			}
			}
			
		}
		for (int i = 0; i < LENGTH; i++) {
			if (visited[i] == false)
				nodesLeft = true;
		}
		//nodesLeft = false;
	}
	PrintWriter writer = null;
				try {
					writer = new PrintWriter("OUTPUT.TXT", "UTF-8");
				} catch (FileNotFoundException | UnsupportedEncodingException e) {

					e.printStackTrace();
				}
	
	int i = DESTINATION;
	System.out.println(DESTINATION + 1);
	writer.println(DESTINATION + 1);
	while (true) {
		System.out.println(parent[i] + 1);
		writer.println(parent[i] + 1);
		totalCost += hexagon[i];
		i = parent[i];
		if (i == START) {
			totalCost += hexagon[START];
			break;
		}
	}
	
	System.out.println("Minimal Path Cost = " + totalCost);
	writer.println("Minimal Path Cost = " + totalCost);
	
	 writer.close();
	   
		
	}
	private static int FindMinDistance(int[] distance) {
		int LENGTH = 233;
		int smallestSoFar = Integer.MAX_VALUE;
		int nodeIndx = -1;
		for(int i = 0; i< LENGTH; i++){
			if (distance[i] < smallestSoFar) {
				smallestSoFar = distance[i];
				nodeIndx = i;
			}
		}
		return nodeIndx;
	
	}
	private static int FindMinUnseen(int[] distance, boolean visited[]) {
		int LENGTH = 233;
		int smallestSoFar = Integer.MAX_VALUE;
		int nodeIndx = -1;
		for(int i = 0; i< LENGTH; i++){
			if ((distance[i] < smallestSoFar) && (!visited[i])) {
				smallestSoFar = distance[i];
				nodeIndx = i;
			}
		}
		return nodeIndx;
	
	}
	private static int[] SetNeighbors(int currentNode, int[] neighbors) {
		
		for (int i = 0; i < 6; i++) {
			neighbors[i] = -1;
		}
	
		for(int i=0; i<233; i++) {
         if(currentNode==226 || currentNode==227 || currentNode==228 || currentNode==229 || currentNode==230 || currentNode==231){
         	
		neighbors[0] = currentNode - 8;
         	neighbors[1] = currentNode - 15;
       		neighbors[2] = currentNode - 7;
  	}
         else if(currentNode==218 || currentNode==219 || currentNode==220 || currentNode==221 || currentNode==222 || currentNode==223 || currentNode==224){
         
         	neighbors[0] = currentNode - 8;
         	neighbors[1] = currentNode - 15;
       		neighbors[2] = currentNode - 7;
       		neighbors[3] = currentNode + 8;
       		neighbors[5] = currentNode + 7;
             }
         else if(currentNode==22 || currentNode==37 || currentNode==52 || currentNode==67 || currentNode==82 || currentNode==97 || currentNode==112 
         || currentNode==127 || currentNode==142 || currentNode==157 || currentNode==172 || currentNode==187 || currentNode==202 || currentNode==217){
	 	neighbors[0] = currentNode - 8;
	 	neighbors[1] = currentNode - 15;
       		neighbors[4] = currentNode + 15;
       		neighbors[5] = currentNode + 7;
	}
	else if(currentNode==15 || currentNode==30 || currentNode==45 || currentNode==60 || currentNode==75 || currentNode==90 || currentNode==105 
		|| currentNode==120 || currentNode==135 || currentNode==150 || currentNode==165 || currentNode==180 || currentNode==195 || currentNode==210){
		neighbors[1] = currentNode -15;
        	neighbors[2] = currentNode - 7;
        	neighbors[3] = currentNode + 8;
        	neighbors[4] = currentNode + 15;
	}
	else if(currentNode==7){
		neighbors[4] = currentNode + 15;
       		neighbors[5] = currentNode + 7;
 	}
  	else if(currentNode==225){
		neighbors[1] = currentNode - 15;
		neighbors[2] = currentNode - 7;
	}
	else if(currentNode==232){
		neighbors[0] = currentNode - 8;
		neighbors[1] = currentNode - 15;
	}
  	else if(currentNode==0){
         	neighbors[3] = currentNode + 8;
       		neighbors[4] = currentNode + 15;
       }
       else if(currentNode==8 || currentNode==9 || currentNode==10 || currentNode==11 || currentNode==12 || currentNode==13 || currentNode==14){
       
       		neighbors[0] = currentNode - 8;
       		neighbors[2] = currentNode - 7;
       		neighbors[3] = currentNode + 8;
       		neighbors[4] = currentNode + 15;
       		neighbors[5] = currentNode + 7;
	}
	else if(currentNode==1 || currentNode==2 || currentNode==3 || currentNode==4 || currentNode==5 || currentNode==6){
	
		neighbors[3] = currentNode + 8; 
		neighbors[4] = currentNode + 15;
		neighbors[5] = currentNode + 7; 
        }
        else{
        	neighbors[0] = currentNode - 8;
        	neighbors[1] = currentNode -15;
        	neighbors[2] = currentNode - 7;
        	neighbors[3] = currentNode + 8;
        	neighbors[4] = currentNode + 15;
		neighbors[5] = currentNode + 7;
	}
  
        }
		return neighbors;
	}
}