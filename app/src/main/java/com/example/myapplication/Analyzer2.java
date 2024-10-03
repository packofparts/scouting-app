package com.example.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Analyzer2 {
	static HashMap<String, Double> sentiment;
	public static void populate(InputStream stream){
		if (sentiment != null){
			return;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		try {
			sentiment = new HashMap<>();
			String line;
			while((line = reader.readLine()) != null){
				String[] temp = line.split(",");
				sentiment.put(temp[0],Double.parseDouble(temp[1]));
			}
			reader.close();
		}
		catch(Exception e){
			throw new RuntimeException();
		}
	}
	public static double analyze(String str) {
		str = str.toLowerCase();
		str = str.replaceAll("[^a-z ]", "");
		String[] words = str.split(" ");
		double totalSentiment = 0.0;
		for (String word : words){
			Double d = sentiment.get(word);
			totalSentiment += d == null ? 0 : d;
		}
		return Math.round(totalSentiment * 100)/100.0;
	}

}

