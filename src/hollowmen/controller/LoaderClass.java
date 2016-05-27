package hollowmen.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * {@code LoaderClass} is a Singleton used to load images
 * 
 * @author Giordo
 */
public class LoaderClass {
	
	private LoaderClass(){}
	
	private static List<String> nameList(){
		LinkedList<String> nameList=new LinkedList<>();
		/*
		 * Aggiungere percorso per tutte le immagini. Se rimane tempo va cambiato
		 */
		nameList.add("res/title.png");
		nameList.add("resources/littleButtonPressed.png");
		nameList.add("resources/littleButtonNOTAvailable.png");
		return nameList;
	}
	
	/**
	 * method used to load images
	 * @return {@code List<Pair<String, byte[]>>} the list of pair of
	 * name and image (as {@code byte[]})
	 */
	public static Map<String,byte[]> load(){
		Map<String,byte[]> imageMap=new HashMap<>();
		BufferedInputStream bf;
		List<String> nameList=nameList();
		try{
			for(String elem:nameList){
				bf=new BufferedInputStream(new FileInputStream (new File(elem)));
				List<Byte> list2 = new LinkedList<>();
				int c;
				while((c=bf.read())!=-1){
					list2.add((byte)c);
				}
				Byte a[]=list2.toArray(new Byte[0]);
				byte b[]=new byte[a.length];
				for(int i=0;i<a.length;i++){
					b[i]=a[i].byteValue();
				}
				imageMap.put(elem, b);
			}
		}catch(Exception e){
			System.out.println("ERRORE CARICAMENTO IMMAGINI!!!");
		}
		return imageMap;
	}
}
