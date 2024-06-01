package com.example.myapplication;

public class Analyzer {
	private static String[] goodWords = {"good"};
	private static String[] badWords = {"bad"};
	private static String[] multiWords = {"very"};
	private static String[] revWords = {"not"};
	public static boolean contains(String[] list, String str) {
		for (String s: list) {
			if (s.equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}
	public static double analyze(String str) {
		str = keepLettersOnly(str);
		String[] words = str.split(" ");
		double total = 0;
		double cur = 1.0;
		for (String s: words) {
			if (contains(goodWords, s)) {
				total += 10 * cur;
				cur = 1.0;
			} else if (contains(badWords, s)) {
				total += -10 * cur;
				cur = 1.0;
			} else if (contains(multiWords, s)) {
				cur *= 2;
			} else if (contains(revWords, s)) {
				cur *= -0.5;
			}
		}
		return total;
	}
	public static String keepLettersOnly(String str) {
		String[] words = str.split("");
		String res = "";
		for (String s: words) {
			if ("abcdefghijklmnopqrstuvwxyz ".indexOf(s.toLowerCase()) != -1) {
				res += s;
			}
		}
		return res;
	}
}
