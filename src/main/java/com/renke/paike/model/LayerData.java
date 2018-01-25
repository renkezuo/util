package com.renke.paike.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LayerData {
	public Integer[] subs;
	public Map<Integer, Integer> subClassCnt = new HashMap<>();
	public Map<Integer, Integer> subClassUserCnt = new HashMap<>();
	public Map<Integer, List<LayerUserInfo>> combMapping = new HashMap<>();
	public Map<Integer, List<LayerUserInfo>> combMappingSubs = new HashMap<>();
//	public Map<Integer, List<LayerUserInfo>> combMappingSubs2 = new HashMap<>();
	public List<Integer> subsCombs = new ArrayList<>();
	public UserBlock[] blocks = new UserBlock[3];
	public Comb[] combs;
//	public List<Integer> subs2Combs = new ArrayList<>();
	public int classCnt;
	public int classUserCnt;
	public int userCnt;
	public int subCnt;
	public Integer[] subs1;
	public Integer[] subs2;
	public LayerUserInfo[] users;
	public List<Integer> sortSubs = new ArrayList<>(7);
}
