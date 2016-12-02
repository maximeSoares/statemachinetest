package com.samplestatemachine.bean;

public class StateStatus {
	private States state;
	private Status status;
	private boolean hasChange;
	public StateStatus(States state, Status status) {
		super();
		this.state = state;
		this.status = status;
	}
	public StateStatus() {
		super();
		this.state = States.Recherche;
		this.status = Status.Debuter;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public States getState() {
		return state;
	}
	
	public boolean hasChange() {
		return hasChange;
	}
	public void setHasChange(boolean hasChange) {
		this.hasChange = hasChange;
	}
	@Override
	public String toString() {
		return "StateStatus [state=" + state + ", status=" + status + "]";
	}
	
	
}
