package com.renke.util.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Mongo {
	public static void main(String[] args) throws IOException {
		File in = new File("mongdb.txt");
		File out = new File("mongodb.out");
		String startCol = "_id";
		int headLength = 4;
		
		Gson gs = new Gson();
		gs.fromJson("{  \"_id\" : {  \"aa\" : NumberLong(918739),  \"bb\" : NumberLong(20786) },  \"total1\" : 5,  \"total\" : 2,  \"total2\" : 2 }", Obj.class);
		
		BufferedReader br = new BufferedReader(new FileReader(in));
		BufferedWriter bw = new BufferedWriter(new FileWriter(out));
		String str = "";
		String[] header = new String[headLength];
		int h = 0;
		List<String> result = new ArrayList<>();
		String line = "";
		while((str = br.readLine())!=null){
			if(str.indexOf(":") > -1){
				String[] kv = str.split(":");
				String key = kv[0].replaceAll("\"", "").trim();
				String value = kv[1].trim();
				if (h < header.length && header[h] == null) {
					header[h++] = key;
				}
				if(key.equals(startCol)){
					result.add(line.trim());
					line = "";
				}
				line += "	" + value;
			}
		}
		result.add(line.trim());
		String headerStr = "";
		for(int i=0;i<headLength;i++){
			headerStr += "	" + header[i];
		}
		bw.write(headerStr.trim());
		bw.newLine();
		for(int i=1;i<result.size();i++){
			bw.write(result.get(i));
			bw.newLine();
		}
		br.close();
		bw.close();
	}
}
