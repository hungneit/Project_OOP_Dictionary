package main;
//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdOut;
import com.sun.security.jgss.GSSUtil;

import java.util.*;

public class DictionaryCommandline {
    public void showAllWords(DictionaryManagement dictionaryManagement, Dictionary dictionary) {
        String s = "";
        s += "No\t|\t\tEnglish\t\t|\t\tVietnamese \n";

        int n = 1;
        TreeMap<String, Pair<String, Integer>> sorted = new TreeMap<String, Pair<String, Integer>>(dictionaryManagement.mp);
        Set<Map.Entry<String, Pair<String, Integer>>> mappings = sorted.entrySet();
        for (Map.Entry<String, Pair<String, Integer>> mapping : mappings) {
            s += n + "\t|\t\t" + mapping.getKey() + "\t\t|\t\t" + mapping.getValue().getKey() + "\n";
            n++;
        }
        System.out.println(s);
    }
    public void dictionaryBasic(DictionaryManagement dictionaryManagement, Dictionary dictionary, String path) {
        dictionaryManagement.insertFromCommandline(dictionary, path);
        showAllWords(dictionaryManagement, dictionary);
    }

    public List<String> dictionarySearcher(DictionaryManagement dictionaryManagement, Dictionary dictionary, String t) {
            List<String> list = dictionaryManagement.trie.searchWithPrefix(t);
            List<String> result = new ArrayList();
            for (int i = 0; i < Math.min(10, list.size()); i++) {
                result.add(list.get(i));
            }
            return result;
    }

    public void dictionaryAdvanced(DictionaryManagement dictionaryManagement, Dictionary dictionary, String path) {
        while (true) {
            System.out.println("Welcome to My Application!");
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.println("Your action:");
            Scanner sc5 = new Scanner(System.in);
            int choice = sc5.nextInt();
            if (choice == 0) {
                break;
            }
            else if (choice == 1) {
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Nhap target: ");
                String t = sc1.nextLine();
                System.out.println("Nhap explain: ");
                String e = sc1.nextLine();
                dictionaryManagement.dictionaryAdd(dictionary, t, e, path);
            }
            else if (choice == 2) {
                Scanner sc3 = new Scanner(System.in);
                System.out.println("Nhap target cua word can xoa: ");
                String t = sc3.nextLine();
                dictionaryManagement.dictionaryRemove(dictionary, t, path);
            }
            else if (choice == 3) {
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Nhap target: ");
                String t = sc2.nextLine();
                System.out.println("Nhap explain can sua: ");
                String e = sc2.nextLine();
                System.out.println("[1] Thêm");
                System.out.println("[2] Thay thế");
                int c = sc2.nextInt();
                if (c == 1) dictionaryManagement.dictionaryUpdate_add(dictionary, t, e, path);
                else dictionaryManagement.dictionaryUpdate_replace(dictionary, t, e, path);
            }
            else if (choice == 4) {
                showAllWords(dictionaryManagement, dictionary);
            }
            else if (choice == 5) {
                Scanner sc0 = new Scanner(System.in);
                System.out.println("Nhap target: ");
                String t = sc0.next();
                System.out.println(dictionaryManagement.dictionaryLookup(t));
            }
            else if (choice == 6) {
                Scanner sc0 = new Scanner(System.in);
                System.out.println("Nhap tien to: ");
                String t = sc0.next();
                System.out.println(dictionarySearcher(dictionaryManagement, dictionary, t));
            }
            else if (choice == 7) {
                ListQuestion listQuestion = new ListQuestion();
                listQuestion.insertFromCommandline();
                for (int i =0 ; i< listQuestion.size(); i++) {
                    Question q = listQuestion.get(i);
                    System.out.println(q.getContent());
                    System.out.println(q.getChoices().get(0));
                    System.out.println(q.getChoices().get(1));
                    System.out.println(q.getChoices().get(2));
                    System.out.println(q.getChoices().get(3));
                    Scanner sc0 = new Scanner(System.in);
                    String c = sc0.next();
                    if (q.getAnswer().equals("A")) {
                        if (c.equals("A")) {
                            System.out.println("Exactly!");
                        } else {
                            System.out.println("Wrong!");
                        }
                    } else if (q.getAnswer().equals("B")) {
                        if (c.equals("B")) {
                            System.out.println("Exactly!");
                        } else {
                            System.out.println("Wrong!");
                        }
                    }else if (q.getAnswer().equals("C")) {
                        if (c.equals("C")) {
                            System.out.println("Exactly!");
                        } else {
                            System.out.println("Wrong!");
                        }
                    } else if (q.getAnswer().equals("D")) {
                        if (c.equals("D")) {
                            System.out.println("Exactly!");
                        } else {
                            System.out.println("Wrong!");
                        }
                    }
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("[1] Thoát khỏi Game");
                    System.out.println("[2] Làm lại");
                    System.out.println("[3] Câu tiếp");
                    int x = sc1.nextInt();
                    if ( x == 1) break;
                    else if (x == 2) i--;
                }
            }
            else if (choice == 8) {
                dictionaryManagement.insertFromCommandline(dictionary, path);
            }
            else if (choice == 9) {
                dictionaryManagement.dictionaryExportToFile(dictionary, path);
            }
            else {
                System.out.println("Action not supported");
                break;
            }
        }
    }
}
