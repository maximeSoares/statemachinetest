package com.samplestatemachine.bean;

import java.util.ArrayList;

public class FlowContext {
	private boolean isClient;
	private boolean isProspect;
	private ArrayList<StateStatus> history;
	
	public FlowContext() {
		super();
		this.isClient = false;
		this.isProspect = false;
		this.history = new ArrayList<>();
		this.history.add(new StateStatus(States.Recherche, Status.Debuter));
		this.history.add(new StateStatus(States.CreationProspect, Status.Debuter));
		this.history.add(new StateStatus(States.Magasinage, Status.Intoucher));
		this.history.add(new StateStatus(States.ModeLivraison, Status.Intoucher));
		this.history.add(new StateStatus(States.Technicien, Status.Intoucher));
		this.history.add(new StateStatus(States.LivraisonEtRecuperationEquipement, Status.Intoucher));
		this.history.add(new StateStatus(States.Sommaire, Status.Intoucher));
		this.history.add(new StateStatus(States.CreationClient, Status.Debuter));
		this.history.add(new StateStatus(States.Prepaiement, Status.Intoucher));
		this.history.add(new StateStatus(States.Equifax, Status.Intoucher));
		this.history.add(new StateStatus(States.Paiement, Status.Intoucher));
		this.history.add(new StateStatus(States.ConfiguarationService, Status.Intoucher));
		this.history.add(new StateStatus(States.ConfiguarationCompte, Status.Intoucher));
		this.history.add(new StateStatus(States.DefinirIndividusAuCompte, Status.Intoucher));
		this.history.add(new StateStatus(States.ModeFacturation, Status.Intoucher));
		
	}

	public boolean isClient() {
		return isClient;
	}

	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}
	
	public boolean isProspect() {
		return isProspect;
	}

	public void setProspect(boolean isProspect) {
		this.isProspect = isProspect;
	}

	public ArrayList<StateStatus> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<StateStatus> history) {
		this.history = history;
	}


	@Override
	public String toString() {
		return "FlowContext [isClient=" + isClient + ", isProspect=" + isProspect + ", history=" + history + "]";
	}


	
}
