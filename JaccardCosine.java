import java.util.*;
import java.io.*;


public class JaccardCosine {
	
	public static void main(String args[]) throws IOException{
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the name of Doc1: ");
		String doc1 = input.nextLine();
		System.out.print("Enter the name of Doc2: ");
		String doc2 = input.nextLine();
		BufferedReader br1 = new BufferedReader(new FileReader(doc1));
		BufferedReader br2 = new BufferedReader(new FileReader(doc2));
																				
		ArrayList<String> docOne = new ArrayList<String>();						//ArrayList of strings to store terms of document 1.
		ArrayList<String> docTwo = new ArrayList<String>();						//ArrayList of strings to store terms of document 2.
		
		String current = br1.readLine();
		
		while(current!=null){
			StringTokenizer st = new StringTokenizer(current," .");
			while(st.hasMoreTokens()){
				String hello = st.nextToken();
				docOne.add(hello);
			}
			current = br1.readLine();
		}
		current = br2.readLine();
		
		while(current!=null){
			StringTokenizer st = new StringTokenizer(current," .");
			while(st.hasMoreTokens()){
				String hello = st.nextToken();
				docTwo.add(hello);
			}
			current = br2.readLine();
		}
		
		
		
		Jaccard(docOne,docTwo);
		CosineSimilarity(docOne,docTwo);
	}
	/* Description: Method calculates the Jaccard coefficient for the two documents.
	 * It takes two arrayLists of string as input and outputs the jaccard coefficient for two arrays.
	 * HashMap has been used in this method to store the word in the documents along with an associated 
	 * integer to ensure that only unique words are accounted for.
	 */
	public static void Jaccard(ArrayList<String> docOne,ArrayList<String> docTwo){
		int countNum = 0;
		int countDen = 0;
		HashMap<String,Integer> hm = new HashMap<String,Integer>();	
		for(int i = 0 ;i<docOne.size();i++){
			String key = docOne.get(i);
			if(!hm.containsKey(key)){
				hm.put(key, 0);
				countDen++;
			}
		}
		
		for(int i = 0;i<docTwo.size();i++){
			String key = docTwo.get(i);
			if(hm.containsKey(key)){
				if(hm.get(key)==0){
					countNum++;
					hm.put(key, 1);
				}
			}
			else{
				hm.put(key, 0);
				countDen++;
			}
		}
		
		double jCoefficient = ((double)countNum)/((double)countDen);
		System.out.println("Jaccard Coefficient = "+jCoefficient);
	}
	
	/* Description: Method calculates the Cosine Similarity Index for the two documents.
	 * It takes two arrayLists of string as input and outputs the Cosine Similarity Index for two arrays.
	 * Multiple HashMaps are used to store the word along with their term frequencies , weights and then normalised weights
	 * to calculate the cosine similarity for the documents.
	 */
	public static void CosineSimilarity(ArrayList<String> docOne,ArrayList<String> docTwo){
		HashMap<String,Double> doc1 = new HashMap<String,Double>();
		for(int i = 0 ;i<docOne.size();i++){
			String key = docOne.get(i);
			if(!doc1.containsKey(key)){
				doc1.put(key, 1.0);
			}
			else{
				double val = doc1.get(key);
				val = val+1.0;
				doc1.put(key, val);
			}
		}
		HashMap<String,Double> doc2 = new HashMap<String,Double>();
		for(int i = 0 ;i<docTwo.size();i++){
			String key = docTwo.get(i);
			if(!doc2.containsKey(key)){
				doc2.put(key, 1.0);
			}
			else{
				double val = doc2.get(key);
				val = val+1.0;
				doc2.put(key, val);
			}
		}
		HashMap<String,Double> docc1 = 	new HashMap<String,Double>();
		
		Iterator it = doc1.entrySet().iterator();
		double den1 = 0,den2 = 0;
		while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        String key = (String) pair.getKey();
		        double value  = (double) pair.getValue();
		        
		        if(value!=0.0)
		         value = 1.0 + Math.log10(value);
		        else value = 0.0;
		        
		        den1 += (value*value);
		        
		        docc1.put(key, value);
		        it.remove(); 
		    }
		HashMap<String,Double> docc2 = 	new HashMap<String,Double>();
		
		Iterator it1 = doc2.entrySet().iterator();
		
		while (it1.hasNext()) {
		        Map.Entry pair = (Map.Entry)it1.next();
		        String key = (String) pair.getKey();
		        double value  = (double) pair.getValue();
		        
		        if(value!=0.0)
			         value = 1.0 + Math.log10(value);
			    else value = 0.0;
			    den2 += (value*value);
		        docc2.put(key, value);
		        it1.remove(); 
		}
		
		den1 = Math.sqrt(den1);
		den2 = Math.sqrt(den2);
		
		HashMap<String,Double> doccc1 = 	new HashMap<String,Double>();
		
		Iterator it2 = docc1.entrySet().iterator();
		
		while (it2.hasNext()) {
		        Map.Entry pair = (Map.Entry)it2.next();
		        String key = (String) pair.getKey();
		        double value  = (double) pair.getValue();
		       
		        value = value/den1;
		        
		        doccc1.put(key, value);
		        it2.remove(); 
		    }
		HashMap<String,Double> doccc2 = 	new HashMap<String,Double>();
		
		Iterator it3 = docc2.entrySet().iterator();
		double cosineSim = 0;
		
		while (it3.hasNext()) {
		        Map.Entry pair = (Map.Entry)it3.next();
		        String key = (String) pair.getKey();
		        double value  = (double) pair.getValue();
		        value = value/den2;
		        if(doccc1.containsKey(key)){
		        	double temp = doccc1.get(key);
		        	
		        	cosineSim += (value*temp);
		        }
		        
		        doccc2.put(key, value);
		        it3.remove(); 
		}
		
		System.out.println("Cosine Similarity Index: "+cosineSim);
		
	}
}
