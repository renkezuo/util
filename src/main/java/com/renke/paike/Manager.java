package com.renke.paike;

import java.util.ArrayList;
import java.util.List;

import com.renke.paike.model.Comb;

public class Manager {
	public Comb[] combs;
	public List<List<TeamCell>> cells;
	public Manager(Comb[] combs){
		this.combs = combs;
		cells = new ArrayList<>(combs.length);
	}
	
	public void register(int combIndex, TeamCell cell){
		DataUtil.addListEle(cells, combIndex, cell);
	}
	
	public void changeCombUse(int combIndex){
		
	}
	
}
