package com.samplestatemachine.bean;

public enum States {
	Recherche(0),
	CreationProspect(1),
	Magasinage(2),
	ModeLivraison(3),
		Technicien(4),
		LivraisonEtRecuperationEquipement(5),
	Sommaire(6),
	CreationClient(7),
	Prepaiement(8),
		Equifax(9),
		Paiement(10),
	ConfiguarationService(11),
	ConfiguarationCompte(12),
		DefinirIndividusAuCompte(13),
		ModeFacturation(14),
		PreferenceCommunication(15),
	
	ChoiceIsClient(-1),
	ChoiceHasDoMagasinage(-1);
	
	private final int val;
	private States(int v) {
		val = v;
	}
	public int getIndex() { 
		return val; 
	}
}