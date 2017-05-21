package Huffman;

import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCode {
    // input is an array of frequencies, indexed by character code
	public static String readfilepath="C:\\Users\\JECY\\document\\experiment\\多媒体\\测试图片.bmp";//此处输入文件路径 
	static String test;
	static HashMap<String, String> mp;
    public static HuffmanTree buildTree(int[] charFreqs) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        // initially, we have a forest of leaves
        // one for each non-empty character
        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i));

        assert trees.size() > 0;
        // loop until there is only one tree left
        while (trees.size() > 1) {
            // two trees with least frequency
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();

            // put into new node and re-insert into queue
            trees.offer(new HuffmanNode(a, b));
        }
        return trees.poll();
    }

    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;

            // print out character, frequency, and code for this leaf (which is just the prefix)
            mp.put(String.valueOf(leaf.value), prefix.toString());
            
            
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;

            // traverse left
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            // traverse right
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    public static void main(String[] args) {
    	mp=new HashMap<>();
        //test = "this is an example for huffman encoding";
        
    	
        R r=new R();
        test =r.read();
        
        
        StringBuffer s=new StringBuffer();

        // we will assume that all our characters will have
        // code less than 256, for simplicity
        int[] charFreqs = new int[65536];
        // read each character and record the frequencies
        for (char c : test.toCharArray())
            charFreqs[c]++;

        // build tree
        HuffmanTree tree = buildTree(charFreqs);

        // print out results
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        printCodes(tree, new StringBuffer());
        
        for (int i=0;i<test.length();i++){
        	s.append(mp.get(String.valueOf(test.charAt(i))));
        }
        System.out.println("压缩前文件大小为"+(test.length())+"字节");
        System.out.println("压缩后文件大小为"+(s.length()/8)+"字节");
    }
}