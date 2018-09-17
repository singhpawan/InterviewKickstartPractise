package strings;

import java.util.ArrayList;
import java.util.List;

public class PatternMatchingTrie {
	TrieNode root;
	public PatternMatchingTrie(){
		root = new TrieNode('$');
	}
	
	public void insert(String str){
		TrieNode temp = root;
		for(int i=0;i<str.length();i++){
			char x = Character.toLowerCase(str.charAt(i));
			if(!temp.children.containsKey(x)){
				temp.children.put(x, new TrieNode(x));
			}
			temp = temp.children.get(x);
		}
		temp.end = true;
		temp.str = str;
	}
	
	public TrieNode prefixNode(String str){
		TrieNode temp = root;
		for(int i=0;i<str.length();i++){
			char x = Character.toLowerCase(str.charAt(i));
			temp = temp.children.get(x);
			if(temp==null){
				return null;
			}
		}
		return temp;
	}
	
	public boolean contains(String str){
		if(prefixNode(str)!=null){
			return true;
		}
		return false;
	}
	
	public boolean containsWord(String str){
		TrieNode temp = prefixNode(str);
		if(temp.end && temp.str==str){
			return true;
		}
		return false;
	}
	
	public List<String> getChildren(String str){
		List<String> result = new ArrayList<String>();
		TrieNode start = prefixNode(str);
		if(start==null){
			return result;
		}
		getChildrenHelper(start, result);
		return result;
	}
	
	public void getChildrenHelper(TrieNode start, List<String> result){
		if(start.end){
			result.add(start.str);
		}
		if(start.children.size()==0){
			return;
		}
		for(Character x:start.children.keySet()){
			getChildrenHelper(start.children.get(x), result);
		}
	}
}
