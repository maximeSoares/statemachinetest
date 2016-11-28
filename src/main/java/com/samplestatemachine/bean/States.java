package com.samplestatemachine.bean;

public enum States {
	Recherche,
	CreationProspect,
	Magasinage,
	ModeLivraison,
		Technicien,
		LivraisonEtRecuperationEquipement,
	Sommaire,
	CreationClient,
	Prepaiement,
		Equifax,
		Paiement,
	ConfiguarationService,
	ConfiguarationCompte,
		DefinirIndividusAuCompte,
		ModeFacturation,
		PreferenceCommunication,
	
	ChoiceIsClient,
	ChoiceHasDoMagasinage
}