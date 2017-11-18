package com.ccoew.adbms.project;

import java.util.ArrayList;

public class Cluster {
	ArrayList<String> clusterRepresentative=new ArrayList<String>();
	ArrayList<String[]> docs=new ArrayList<String[]>();
	int noOfDocs;
	int clusterNo;
	public int getClusterNo() {
		return clusterNo;
	}
	
	public Cluster(){
		noOfDocs=0;
	}
	public ArrayList<String> getClusterRepresentative() {
		return clusterRepresentative;
	}
	public void addToCluster(String word[]){
		docs.add(word);
		noOfDocs++;
	}
	
	
	public int getNoOfDocs(){
		return noOfDocs;
	}
	public String[] getDoc(int i){
		return docs.get(i);
	}
	public void setClusterNo(int countq) {
		// TODO Auto-generated method stub
		this.clusterNo=countq;
	}
	public ArrayList<String[]> getDocs() {
		return docs;
	}
	public void setDocs(ArrayList<String[]> docs) {
		this.docs = docs;
	}
	public void setClusterRepresentative(ArrayList<String> newwords) {
		this.clusterRepresentative = newwords;
	}
	public void setNoOfDocs(int noOfDocs) {
		this.noOfDocs = noOfDocs;
	}
	public void addToClusterRepresentative(String word){
		if(!this.clusterRepresentative.contains(word))
			clusterRepresentative.add(word);
	}
	
}
