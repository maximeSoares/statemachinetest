package com.samplestatemachine.bean;

import java.util.ArrayList;

public class FlowContext {
	private boolean isClient;
	private ArrayList<States> history;
	public States current;
	
	public FlowContext(boolean isClient) {
		super();
		this.isClient = isClient;
		this.history = new ArrayList<>();
	}

	public boolean isClient() {
		return isClient;
	}

	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}
	
	public boolean isProspect() {
		return !isClient;
	}

	public void setProspect(boolean isClient) {
		this.isClient = !isClient;
	}

	public ArrayList<States> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<States> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "FlowContext [isClient=" + isClient + ", history=" + history + ", current=" + current + "]";
	}


	
}
