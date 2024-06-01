package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;

public class Analyzer {
	//Words that have an "!" also have a form that ends with -ly
	//Words that have an "@" also have a form that ends with -s
	private static String[] goodWords = {"good","wonderful!","excellent","amazing","serene!",
										"strong!","beautiful!","perfect!","fast","perfection",
										"gorgeous!","best","charming","gracious!","better",
										"well","accurate!","reliable!"};
	private static String[] badWords = {"bad!","terrible!","suck@","acts","sucks",
										"old","delicate!","intent","tragedy","awful!",
										"vile!","tragic!","weak!","worst","traumatic!",
										"stinging","accident!","nightmare","victim", "poor!",
										"slow!", "unreliable!"};
	private static String[] multiWords = {"-0.5,not","1.8,very","0.9,most!","0.6,mild!","1.5,too"};
	private static boolean populated = false;
	public static int contains(String[] list, String str) {
		for (int i = 0; i < list.length; i ++) {
			if (keepLettersOnly(list[i]).equalsIgnoreCase(str)) {
				return i;
			}
		}
		return -1;
	}
	public static double analyze(String str) {
		if (!populated) {
			goodWords = populateForms(goodWords);
			badWords = populateForms(badWords);
			multiWords = populateForms(multiWords);
			populated = true;
		}
		str = str.toLowerCase();
		str = keepLettersOnly(str);
		String[] words = str.split(" ");
		double total = 0;
		double cur = 1.0;
		for (String s: words) {
			int index;
			if (contains(goodWords, s) >= 0) {
				total += 10 * cur;
				cur = 1.0;
			} else if (contains(badWords, s) >= 0) {
				total += -10 * cur;
				cur = 1.0;
			} else if ((index = contains(multiWords, s)) >= 0) {
				cur *= Double.parseDouble(multiWords[index].substring(0, multiWords[index].indexOf(",")));
			}
		}
		return total;
	}
	public static String keepLettersOnly(String str) {
		String[] words = str.split("");
		String res = "";
		for (String s: words) {
			if ("abcdefghijklmnopqrstuvwxyz ".contains(s)) {
				res += s;
			}
		}
		return res;
	}
	public static String[] populateForms(String[] list){
		ArrayList<String> str = new ArrayList<>(Arrays.asList(list));
		ArrayList<String> res = new ArrayList<>();
		for (String cur: str){
			if (cur.endsWith("!")){
				String word = cur.substring(0, cur.length() - 1);
				res.add(word.endsWith("le") ? word.substring(0, word.length() - 1) + "y" : (word.endsWith("ic") ? word + "ally" : word + "ly"));
				res.add(word);
			} else if (cur.endsWith("@")){
				String word = cur.substring(0, cur.length() - 1);
				res.add(word + "s");
				res.add(word);
			} else {
				res.add(cur);
			}
		}
		System.out.println(res);
		return res.toArray(new String[] {});
	}
}

