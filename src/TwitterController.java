package com.ccoew.adbms.project;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;



import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;



@Controller
public class TwitterController {
	List<Status> displayStatus = new ArrayList<Status>();
	int clustersformed=0;
	ArrayList<Cluster> clusters=new ArrayList<Cluster>();

	@RequestMapping(value="/page1.html",method=RequestMethod.GET)
	public ModelAndView WelcomePage(){
		ModelAndView model=new ModelAndView("Hello");
		return model;
	}
	
	public static double calculateSimilarity(String words[],ArrayList <String> cr)
	{
		double similarity=0;
		int commonWords=0;
		for(int i=0;i<words.length;i++){
			for(int j=0;j<cr.size();j++)
			{
				if(words[i].equalsIgnoreCase(cr.get(j)))
					commonWords++;
			}
				
		}
		similarity=(double)commonWords/(words.length+cr.size()-commonWords);
		return similarity;
	}


	@RequestMapping(value="/page2.html",method=RequestMethod.POST)
	public ModelAndView DisplayTweets(@RequestParam("username") String username){
		//List<Status> status=null;
		//int i=0;
		ArrayList<Def> def=new ArrayList<Def>();
			try
			{
				
				
				MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
				DB db= mongoClient.getDB( "test1" );
				System.out.println("Connect to database successfully");
			
				ArrayList<String> cr=new ArrayList<String>();
				ArrayList<Cluster> clusters=new ArrayList<Cluster>();
				DBCollection table = db.getCollection("Twitter1");
				    
				ConfigurationBuilder cf=new ConfigurationBuilder();
				cf.setDebugEnabled(true)
				  .setOAuthConsumerKey("ga39RZopRYzhSunnmBUQM7Eg1")
				  .setOAuthConsumerSecret("8RLLTfRWZqxuby8E6H9dET8WHZj5miS3U7GAbI8Y4kRk3V0RX5")
				  .setOAuthAccessToken("605363209-0o2aNPeHsHe6Hd7pwgpZZmDr6h5SncQcvswJzmbu")
				  .setOAuthAccessTokenSecret("moQbwW8eAJvJ0gOWS1c5XUIYfx655UIgufvpXzURjnd9G");
					
				
				
				TwitterFactory tf=new TwitterFactory(cf.build());
				twitter4j.Twitter twitter=tf.getInstance();
				Paging paging = new Paging(1, 50);
				
				List<Status> status=twitter.getUserTimeline(username,paging);
				//List<Status> status=twitter.getUserTimeline();        
				int count=0;
				ArrayList <String> allstatus1 = new ArrayList<String>();
				ArrayList <String> allstatus2 = new ArrayList<String>();
				BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Reliance Digital\\Desktop\\Final\\Output.txt"));
			    
				displayStatus.addAll(status);
				for(Status st : status)
				{
					
					System.out.println(st.getUser().getName()+"------------"+st.getText());
				
					BasicDBObject document= new BasicDBObject();
										
						String REGEX = "@[^\\s]+";
						String REGEX1 = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
						String REGEX2 = "[\\s]+";
						String REGEX3= "#";
						String REGEX4= "!";
						String REGEX5= "\\*";
						String REGEX6= "\\{";
						String REGEX7= "\\}";
						String REGEX8= "\\?";
						String REGEX9= "%";
						String REGEX10= "\\^";
						String REGEX11= "$";
						String REGEX12="\\(";
						String REGEX13="\\)";
						String REGEX14=":";
						String REGEX15=";";
						String REGEX16="<";
						String REGEX17=">";
						String REGEX18="&";
						String REGEX19="~";
						String REGEX20="\\+";
						String REGEX21="=";
						String REGEX22="_";
						String REGEX23="\\.";
						String REGEX24="/";
						String REGEX25="-";
						
						String INPUT = st.getUser().getScreenName();
					    String INPUT1 = st.getText();
					    
					    String REPLACE = "atuser";
					    String REPLACE1 = "URL";
					    String REPLACE2 = " ";
					   
					    
					    Pattern p = Pattern.compile(REGEX);
					    Matcher m = p.matcher(INPUT);
					    Matcher m1 = p.matcher(INPUT1);
					    INPUT = m.replaceAll(REPLACE);
					    INPUT1 = m1.replaceAll(REPLACE);
					
					    
					    Pattern p1 = Pattern.compile(REGEX1);
					    Matcher m2 = p1.matcher(INPUT);
					    Matcher m3 = p1.matcher(INPUT1);
					    INPUT = m2.replaceAll(REPLACE1);
					    INPUT1 = m3.replaceAll(REPLACE1);
					
					    
					    INPUT=INPUT.toLowerCase();
					    INPUT1=INPUT1.toLowerCase();
					    
					    
					    Pattern p3 = Pattern.compile(REGEX3);
					    Matcher m6 = p3.matcher(INPUT);
					    Matcher m7 = p3.matcher(INPUT1);
					    INPUT = m6.replaceAll(REPLACE2);
					    INPUT1 = m7.replaceAll(REPLACE2);
				
					    
					    Pattern p4 = Pattern.compile(REGEX4);
					    Matcher m8 = p4.matcher(INPUT);
					    Matcher m9 = p4.matcher(INPUT1);
					    INPUT = m8.replaceAll(REPLACE2);
					    INPUT1 = m9.replaceAll(REPLACE2);
				
					    Pattern p5 = Pattern.compile(REGEX5);
					    Matcher m10 = p5.matcher(INPUT);
					    Matcher m11 = p5.matcher(INPUT1);
					    INPUT = m10.replaceAll(REPLACE2);
					    INPUT1 = m11.replaceAll(REPLACE2);
				
					    
					    Pattern p6 = Pattern.compile(REGEX6);
					    Matcher m12 = p6.matcher(INPUT);
					    Matcher m13 = p6.matcher(INPUT1);
					    INPUT = m12.replaceAll(REPLACE2);
					    INPUT1 = m13.replaceAll(REPLACE2);
					    
					    Pattern p7 = Pattern.compile(REGEX7);
					    Matcher m18 = p7.matcher(INPUT);
					    Matcher m19 = p7.matcher(INPUT1);
					    INPUT = m18.replaceAll(REPLACE2);
					    INPUT1 = m19.replaceAll(REPLACE2);
				
					    Pattern p8 = Pattern.compile(REGEX8);
					    Matcher m20 = p8.matcher(INPUT);
					    Matcher m21 = p8.matcher(INPUT1);
					    INPUT = m20.replaceAll(REPLACE2);
					    INPUT1 = m21.replaceAll(REPLACE2);
				
				
					    Pattern p9 = Pattern.compile(REGEX9);
					    Matcher m22 = p9.matcher(INPUT);
					    Matcher m23 = p9.matcher(INPUT1);
					    INPUT = m22.replaceAll(REPLACE2);
					    INPUT1 = m23.replaceAll(REPLACE2);
				
					    Pattern p10 = Pattern.compile(REGEX10);
					    Matcher m24 = p10.matcher(INPUT);
					    Matcher m25 = p10.matcher(INPUT1);
					    INPUT = m24.replaceAll(REPLACE2);
					    INPUT1 = m25.replaceAll(REPLACE2);
				
					    Pattern p11 = Pattern.compile(REGEX11);
					    Matcher m26 = p11.matcher(INPUT);
					    Matcher m27 = p11.matcher(INPUT1);
					    INPUT = m26.replaceAll(REPLACE2);
					    INPUT1 = m27.replaceAll(REPLACE2);
				
					    Pattern p12 = Pattern.compile(REGEX12);
					    Matcher m28 = p12.matcher(INPUT);
					    Matcher m29 = p12.matcher(INPUT1);
					    INPUT = m28.replaceAll(REPLACE2);
					    INPUT1 = m29.replaceAll(REPLACE2);
					    
					    
					    Pattern p13 = Pattern.compile(REGEX13);
					    Matcher m30 = p13.matcher(INPUT);
					    Matcher m31 = p13.matcher(INPUT1);
					    INPUT = m30.replaceAll(REPLACE2);
					    INPUT1 = m31.replaceAll(REPLACE2);
					    
					    Pattern p14 = Pattern.compile(REGEX14);
					    Matcher m32 = p14.matcher(INPUT);
					    Matcher m33 = p14.matcher(INPUT1);
					    INPUT = m32.replaceAll(REPLACE2);
					    INPUT1 = m33.replaceAll(REPLACE2);
					    
					    
					    Pattern p15 = Pattern.compile(REGEX15);
					    Matcher m34 = p15.matcher(INPUT);
					    Matcher m35 = p15.matcher(INPUT1);
					    INPUT = m34.replaceAll(REPLACE2);
					    INPUT1 = m35.replaceAll(REPLACE2);
					    
					    
					    Pattern p16 = Pattern.compile(REGEX16);
					    Matcher m36 = p16.matcher(INPUT);
					    Matcher m37 = p16.matcher(INPUT1);
					    INPUT = m36.replaceAll(REPLACE2);
					    INPUT1 = m37.replaceAll(REPLACE2);
					    
					    
					    Pattern p17 = Pattern.compile(REGEX17);
					    Matcher m38 = p17.matcher(INPUT);
					    Matcher m39 = p17.matcher(INPUT1);
					    INPUT = m38.replaceAll(REPLACE2);
					    INPUT1 = m39.replaceAll(REPLACE2);
					    
					    
					    Pattern p18 = Pattern.compile(REGEX18);
					    Matcher m40 = p18.matcher(INPUT);
					    Matcher m41 = p18.matcher(INPUT1);
					    INPUT = m40.replaceAll(REPLACE2);
					    INPUT1 = m41.replaceAll(REPLACE2);
					    
					    
					    Pattern p19 = Pattern.compile(REGEX19);
					    Matcher m42 = p19.matcher(INPUT);
					    Matcher m43 = p19.matcher(INPUT1);
					    INPUT = m42.replaceAll(REPLACE2);
					    INPUT1 = m43.replaceAll(REPLACE2);
					    
					    Pattern p20 = Pattern.compile(REGEX20);
					    Matcher m44= p20.matcher(INPUT);
					    Matcher m45 = p20.matcher(INPUT1);
					    INPUT = m44.replaceAll(REPLACE2);
					    INPUT1 = m45.replaceAll(REPLACE2);
					    
					    
					    Pattern p21 = Pattern.compile(REGEX21);
					    Matcher m46 = p21.matcher(INPUT);
					    Matcher m47 = p21.matcher(INPUT1);
					    INPUT = m46.replaceAll(REPLACE2);
					    INPUT1 = m47.replaceAll(REPLACE2);
					    
					    
					    Pattern p22 = Pattern.compile(REGEX22);
					    Matcher m48 = p22.matcher(INPUT);
					    Matcher m49 = p22.matcher(INPUT1);
					    INPUT = m48.replaceAll(REPLACE2);
					    INPUT1 = m49.replaceAll(REPLACE2);
					    
					    
					    Pattern p23 = Pattern.compile(REGEX23);
					    Matcher m50 = p23.matcher(INPUT);
					    Matcher m51 = p23.matcher(INPUT1);
					    INPUT = m50.replaceAll(REPLACE2);
					    INPUT1 = m51.replaceAll(REPLACE2);
					    
					    Pattern p24 = Pattern.compile(REGEX24);
					    Matcher m52 = p24.matcher(INPUT);
					    Matcher m53 = p24.matcher(INPUT1);
					    INPUT = m52.replaceAll(REPLACE2);
					    INPUT1 = m53.replaceAll(REPLACE2);
					    
					    
					    Pattern p25 = Pattern.compile(REGEX25);
					    Matcher m54 = p25.matcher(INPUT);
					    Matcher m55 = p25.matcher(INPUT1);
					    INPUT = m54.replaceAll(REPLACE2);
					    INPUT1 = m55.replaceAll(REPLACE2);
					    
					    
					    Pattern p2 = Pattern.compile(REGEX2);
					    Matcher m4 = p2.matcher(INPUT);
					    Matcher m5 = p2.matcher(INPUT1);
					    INPUT = m4.replaceAll(REPLACE2);
					    INPUT1 = m5.replaceAll(REPLACE2);

				
					    allstatus1.add(INPUT1);
					    
			System.out.println(INPUT1);
				
				table.insert(document);
				count++;
				
				
			}
								
				for (int i=0;i<allstatus1.size();i++)
				{
					
					bw.write(i+"."+"\t");
					bw.write(allstatus1.get(i)+"\n");
				}
				
				String strLine,strLine2=null;
				int b;
				for(int i=0;i<allstatus1.size();i++)
				{
					strLine2=" ";
					strLine=allstatus1.get(i);
					String[] arr = strLine.split("\\W+");
					
					for(String s : arr){
						BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Reliance Digital\\Desktop\\Final\\stopwords"));
						String strLine1;
						b=0;
						while ((strLine1 = br1.readLine()) != null)
						{
							if(s.equalsIgnoreCase(strLine1))
							{
								b=1;
								break;
							}
						}		
						if(b==0){
									strLine2=strLine2+" "
											+s;
						}
					
						br1.close();
					}
					allstatus2.add(strLine2);
				}
			
				
				bw.close();

				for(int i=0;i<allstatus2.size();i++)
				{
					System.out.println(allstatus2.get(i));
					
				}
				
				String line = allstatus2.get(0);
				int j=0, countq=1;
				double threshold=0.20;
				
				//ArrayList<Cluster> clusters=new ArrayList<Cluster>();
				

				//Put first document in Cluster 1
				Cluster cluster1=new Cluster();
				
					String words[]=line.split("\\W+");
				for(int i=0;i<words.length;i++)
						cluster1.addToClusterRepresentative(words[i]);
				
				cluster1.addToCluster(words);
					clusters.add(cluster1);
					
					for(int i=1;i<allstatus2.size();i++){
						double sim=0,similarity;
						int cluster=0;
						ArrayList<String> cr1=new ArrayList<String>();
						
						line = allstatus2.get(i);
						words=line.split("\\W+");
						for(int m=0;m<words.length;m++)
							cr1.add(words[m]);
						
						
						
						for(j=0;j<clusters.size();j++){
							similarity=calculateSimilarity(words,clusters.get(j).getClusterRepresentative());
							if(similarity>sim){
								sim=similarity;
								cluster=j;
							}
						}
						ArrayList<String> temp1=clusters.get(cluster).getClusterRepresentative();
						
						//int len=words.length+temp1.length+1;
						ArrayList<String> newwords=new ArrayList<String>();
						
						int cnt=0;
						if(sim>=threshold){
							clusters.get(cluster).addToCluster(words);
							
							for(int x=0;x<words.length;x++)
								clusters.get(cluster).addToClusterRepresentative(words[x]);
							
						}
						else{
							Cluster newCluster=new Cluster();
							
							for(int x=0;x<words.length;x++)	
							newCluster.addToClusterRepresentative(words[x]);
							newCluster.addToCluster(words);
							newCluster.setClusterNo(countq);
							clusters.add(newCluster);
							countq++;
						}
					}
					String file="File";
					
					for(int i=0;i<clusters.size();i++)
				{
					BufferedWriter bw1=new BufferedWriter(new FileWriter("C:\\Users\\Reliance Digital\\Desktop\\Final\\Output\\"+file+i));
						
					System.out.println("Cluster number: "+i);
					bw1.write("Cluster"+i+"\n");
					bw1.write("\n");
					bw1.write("\n");
					bw1.write("Cluster Representative:\n");
					bw1.write("\n");
					for(int s=0;s<clusters.get(i).getClusterRepresentative().size();s++)
					{
					
						System.out.println("CR "+clusters.get(i).getClusterRepresentative().get(s));
						bw1.write(clusters.get(i).getClusterRepresentative().get(s)+"\n");
					}
					bw1.write("\n");
					bw1.write("\n");
					bw1.write("Words in the cluster:\n");
					bw1.write("\n");
					ArrayList <String[]> getdoc= clusters.get(i).getDocs();
					for(int u=0;u<clusters.get(i).getDocs().size();u++)
					{
						String a[]=getdoc.get(u);
						for(int o=0;o<getdoc.get(u).length;o++)
						{
							bw1.write(a[o]+"\n");
							System.out.println(a[o]);
						}
						bw1.write("\n");
					}
			
					bw1.close();
				}

					clustersformed=clusters.size();

	     	
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		ModelAndView model=new ModelAndView("Hello2");
		model.addObject("msg1","Username is:  "+username);
		model.addObject("msg2","Number of Clusters being formed "+ clustersformed);
		model.addObject("msg3",displayStatus);
		//model.addObject("msg3","Domain 3 is "+hashtag3);
		//model.addObject("msg4",displayStatus);
		//model.addObject("msg5",clusters);
		//model.addObject("msg6",clusters);
		return model;
		}
}
