package com.example.string;

import java.util.ArrayList;
import java.util.HashMap;


public class ScaleData {
		public static final int FLAT_HASHMAP_CAPACITY = 20;
		public static final int WIRE_SEQUENCE=6;
		public static final int MUTE = -1;
		
		public static final int [] WIRE = {1, 2, 3, 4, 5, 6};
		public static final String[][] SCALE = {
			{"1_0","1_1","1_2","1_3","1_4","1_5","1_6","1_7","1_8","1_9","1_10","1_11","1_12","1_13","1_14","1_15","1_16","1_17","1_18","1_19","1_20"},
			{"2_0","2_1","2_2","2_3","2_4","2_5","2_6","2_7","2_8","2_9","2_10","2_11","2_12","2_13","2_14","2_15","2_16","2_17","2_18","2_19","2_20"},
			{"3_0","3_1","3_2","3_3","3_4","3_5","3_6","3_7","3_8","3_9","3_10","3_11","3_12","3_13","3_14","3_15","3_16","3_17","3_18","3_19","3_20"},
			{"4_0","4_1","4_2","4_3","4_4","4_5","4_6","4_7","4_8","4_9","4_10","4_11","4_12","4_13","4_14","4_15","4_16","4_17","4_18","4_19","4_20"},
			{"5_0","5_1","5_2","5_3","5_4","5_5","5_6","5_7","5_8","5_9","5_10","5_11","5_12","5_13","5_14","5_15","5_16","5_17","5_18","5_19","5_20"},
			{"6_0","6_1","6_2","6_3","6_4","6_5","6_6","6_7","6_8","6_9","6_10","6_11","6_12","6_13","6_14","6_15","6_16","6_17","6_18","6_19","6_20"}
			};
		
		public class Scale{

		public String flat,sca;
		public String wire,wir;
		
		public Scale(String f, String w){
			flat = f;
			wire = w;
			}
		}
		public static ArrayList<Scale> FlatArray;
		public static HashMap<ArrayList<Scale>, String> scaleMap;
		
		public ScaleData(){
			
			scaleMap = new HashMap<ArrayList<Scale>, String>(FLAT_HASHMAP_CAPACITY);
			
			//1번째줄 계이름
			FlatArray = new ArrayList<Scale>(20);
			FlatArray.add(new Scale("1" ,"1"));
			FlatArray.add(new Scale("2", "1"));
			FlatArray.add(new Scale("3", "1"));
			FlatArray.add(new Scale("4" ,"1"));
			FlatArray.add(new Scale("5" ,"1"));
			FlatArray.add(new Scale("6", "1"));
			FlatArray.add(new Scale("7", "1"));
			FlatArray.add(new Scale("8" ,"1"));
			FlatArray.add(new Scale("9" ,"1"));
			FlatArray.add(new Scale("10", "1"));
			FlatArray.add(new Scale("11", "1"));
			FlatArray.add(new Scale("12" ,"1"));
			FlatArray.add(new Scale("13" ,"1"));
			FlatArray.add(new Scale("14", "1"));
			FlatArray.add(new Scale("15", "1"));
			FlatArray.add(new Scale("16" ,"1"));
			FlatArray.add(new Scale("17" ,"1"));
			FlatArray.add(new Scale("18", "1"));
			FlatArray.add(new Scale("19", "1"));
			FlatArray.add(new Scale("20" ,"1"));
			scaleMap.put(FlatArray, "1");
			//2번째줄계이름
			FlatArray = new ArrayList<Scale>(20);
			FlatArray.add(new Scale("1" ,"2"));
			FlatArray.add(new Scale("2", "2"));
			FlatArray.add(new Scale("3", "2"));
			FlatArray.add(new Scale("4" ,"2"));
			FlatArray.add(new Scale("5" ,"2"));
			FlatArray.add(new Scale("6", "2"));
			FlatArray.add(new Scale("7", "2"));
			FlatArray.add(new Scale("8" ,"2"));
			FlatArray.add(new Scale("9" ,"2"));
			FlatArray.add(new Scale("10", "2"));
			FlatArray.add(new Scale("11", "2"));
			FlatArray.add(new Scale("12" ,"2"));
			FlatArray.add(new Scale("13" ,"2"));
			FlatArray.add(new Scale("14", "2"));
			FlatArray.add(new Scale("15", "2"));
			FlatArray.add(new Scale("16" ,"2"));
			FlatArray.add(new Scale("17" ,"2"));
			FlatArray.add(new Scale("18", "2"));
			FlatArray.add(new Scale("19", "2"));
			FlatArray.add(new Scale("20" ,"2"));
			scaleMap.put(FlatArray, "2");
			//3번째줄계이름
			FlatArray = new ArrayList<Scale>(20);
			FlatArray.add(new Scale("1" ,"3"));
			FlatArray.add(new Scale("2", "3"));
			FlatArray.add(new Scale("3", "3"));
			FlatArray.add(new Scale("4" ,"3"));
			FlatArray.add(new Scale("5" ,"3"));
			FlatArray.add(new Scale("6", "3"));
			FlatArray.add(new Scale("7", "3"));
			FlatArray.add(new Scale("8" ,"3"));
			FlatArray.add(new Scale("9" ,"3"));
			FlatArray.add(new Scale("10", "3"));
			FlatArray.add(new Scale("11", "3"));
			FlatArray.add(new Scale("12" ,"3"));
			FlatArray.add(new Scale("13" ,"3"));
			FlatArray.add(new Scale("14", "3"));
			FlatArray.add(new Scale("15", "3"));
			FlatArray.add(new Scale("16" ,"3"));
			FlatArray.add(new Scale("17" ,"3"));
			FlatArray.add(new Scale("18", "3"));
			FlatArray.add(new Scale("19", "3"));
			FlatArray.add(new Scale("20" ,"3"));
			scaleMap.put(FlatArray, "3");
			//4번째줄 계이름
			FlatArray = new ArrayList<Scale>(20);
			FlatArray.add(new Scale("1" ,"4"));
			FlatArray.add(new Scale("2", "4"));
			FlatArray.add(new Scale("3", "4"));
			FlatArray.add(new Scale("4" ,"4"));
			FlatArray.add(new Scale("5" ,"4"));
			FlatArray.add(new Scale("6", "4"));
			FlatArray.add(new Scale("7", "4"));
			FlatArray.add(new Scale("8" ,"4"));
			FlatArray.add(new Scale("9" ,"4"));
			FlatArray.add(new Scale("10", "4"));
			FlatArray.add(new Scale("11", "4"));
			FlatArray.add(new Scale("12" ,"4"));
			FlatArray.add(new Scale("13" ,"4"));
			FlatArray.add(new Scale("14", "4"));
			FlatArray.add(new Scale("15", "4"));
			FlatArray.add(new Scale("16" ,"4"));
			FlatArray.add(new Scale("17" ,"4"));
			FlatArray.add(new Scale("18", "4"));
			FlatArray.add(new Scale("19", "4"));
			FlatArray.add(new Scale("20" ,"4"));
			scaleMap.put(FlatArray, "4");
			//5번째줄계이름
			FlatArray = new ArrayList<Scale>(20);
			FlatArray.add(new Scale("1" ,"5"));
			FlatArray.add(new Scale("2", "5"));
			FlatArray.add(new Scale("3", "5"));
			FlatArray.add(new Scale("4" ,"5"));
			FlatArray.add(new Scale("5" ,"5"));
			FlatArray.add(new Scale("6", "5"));
			FlatArray.add(new Scale("7", "5"));
			FlatArray.add(new Scale("8" ,"5"));
			FlatArray.add(new Scale("9" ,"5"));
			FlatArray.add(new Scale("10", "5"));
			FlatArray.add(new Scale("11", "5"));
			FlatArray.add(new Scale("12" ,"5"));
			FlatArray.add(new Scale("13" ,"5"));
			FlatArray.add(new Scale("14", "5"));
			FlatArray.add(new Scale("15", "5"));
			FlatArray.add(new Scale("16" ,"5"));
			FlatArray.add(new Scale("17" ,"5"));
			FlatArray.add(new Scale("18", "5"));
			FlatArray.add(new Scale("19", "5"));
			FlatArray.add(new Scale("20" ,"5"));
			scaleMap.put(FlatArray, "5");
			//6번째줄계이름
			FlatArray = new ArrayList<Scale>(20);
			FlatArray.add(new Scale("1" ,"6"));
			FlatArray.add(new Scale("2", "6"));
			FlatArray.add(new Scale("3", "6"));
			FlatArray.add(new Scale("4" ,"6"));
			FlatArray.add(new Scale("5" ,"6"));
			FlatArray.add(new Scale("6", "6"));
			FlatArray.add(new Scale("7", "6"));
			FlatArray.add(new Scale("8" ,"6"));
			FlatArray.add(new Scale("9" ,"6"));
			FlatArray.add(new Scale("10", "6"));
			FlatArray.add(new Scale("11", "6"));
			FlatArray.add(new Scale("12" ,"6"));
			FlatArray.add(new Scale("13" ,"6"));
			FlatArray.add(new Scale("14", "6"));
			FlatArray.add(new Scale("15", "6"));
			FlatArray.add(new Scale("16" ,"6"));
			FlatArray.add(new Scale("17" ,"6"));
			FlatArray.add(new Scale("18", "6"));
			FlatArray.add(new Scale("19", "6"));
			FlatArray.add(new Scale("20" ,"6"));
			scaleMap.put(FlatArray, "6");
			
		}

	
		
	/*	public static Scale getScale(String sca, String wir){
			String temp = FlatArray.get(sca);
			Scale scale = temp.get(wir);
			return scale;
		}*/
		
	/*	public static Scale getScale(String name){
			String root, ext;
			if(name.charAt(1) == '#'){
				root = name.substring(0, 5);
				ext = name.substring(5);
			}
			else{
				root = name.substring(0, 1);
				ext = name.substring(1);
			}

			ArrayList<Scale> temp = scaleMap.get(sca);
			Scale scale = temp.get(ext).get(0);
			return scale;
		}*/
	}
	//HashMap 써서 계이름 데이터 저장
	//블루투스스위치신호+레이아웃 기타줄
	 
	 

