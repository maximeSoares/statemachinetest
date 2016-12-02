package com.samplestatemachine.action;

import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import com.samplestatemachine.bean.Events;
import com.samplestatemachine.bean.FlowContext;
import com.samplestatemachine.bean.StateStatus;
import com.samplestatemachine.bean.States;
import com.samplestatemachine.bean.Status;

public class ActionValidation {
	@Bean
	public static Action<States, Events> recherche() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.Recherche.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> prospect() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.CreationProspect.getIndex());
	            	state.setStatus(Status.Completer);
	            	StateStatus impact = flowContext.getHistory().get(States.Magasinage.getIndex());
	            	if(impact.getStatus() == Status.Intoucher){
		            	impact.setStatus(Status.Debuter);
	            	}
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> magasinage() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.Magasinage.getIndex());
	            	state.setStatus(Status.Completer);
	            	StateStatus impactLivraison = flowContext.getHistory().get(States.ModeLivraison.getIndex());
	            	if(impactLivraison.getStatus() != Status.Intoucher){
	            		impactLivraison.setStatus(Status.Invalide);
		            } else if(impactLivraison.getStatus() == Status.Intoucher){
		            	impactLivraison.setStatus(Status.Debuter);
		            }
	            	StateStatus impactSommaire = flowContext.getHistory().get(States.Sommaire.getIndex());
	            	if(impactSommaire.getStatus() != Status.Intoucher){
	            		impactSommaire.setStatus(Status.Invalide);
		            }
	            	StateStatus impactService = flowContext.getHistory().get(States.ConfiguarationService.getIndex());
	            	if(impactService.getStatus() != Status.Intoucher){
	            		impactService.setStatus(Status.Invalide);
		            }
	            	StateStatus impactTechnicien = flowContext.getHistory().get(States.Technicien.getIndex());
	            	if(impactTechnicien.getStatus() == Status.Intoucher){
	            		impactTechnicien.setStatus(Status.Debuter);
	            	}
	            	
	            	StateStatus impactPaiement = flowContext.getHistory().get(States.Paiement.getIndex());
	            	StateStatus impactClient = flowContext.getHistory().get(States.CreationClient.getIndex());
	            	if(impactPaiement.getStatus() == Status.Intoucher && impactClient.getStatus() == Status.Intoucher){
	            		impactPaiement.setStatus(Status.Debuter);
	            	}
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> livraison() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.ModeLivraison.getIndex());
	            	state.setStatus(Status.Completer);
	            	StateStatus impact = flowContext.getHistory().get(States.Sommaire.ordinal());
	            	if(impact.getStatus() != Status.Intoucher){
		            	impact.setStatus(Status.Invalide);
		            } else if(impact.getStatus() == Status.Intoucher){
		            	impact.setStatus(Status.Debuter);
		            }
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> technicien() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.Technicien.getIndex());
	            	state.setStatus(Status.Completer);

	            	StateStatus impactLivraisonEtRec = flowContext.getHistory().get(States.LivraisonEtRecuperationEquipement.getIndex());
	            	if(impactLivraisonEtRec.getStatus() == Status.Intoucher){
		            	impactLivraisonEtRec.setStatus(Status.Debuter);
	            	}
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> livraisonEtRecuperation() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.LivraisonEtRecuperationEquipement.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> sommaire() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.Sommaire.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> client() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.CreationClient.getIndex());
	            	state.setStatus(Status.Completer);
	            	StateStatus impacte = flowContext.getHistory().get(States.Equifax.getIndex());
	            	StateStatus impactMagasinage = flowContext.getHistory().get(States.Magasinage.getIndex());
	            	if(impacte.getStatus() == Status.Completer){
		            	impacte.setStatus(Status.Invalide);
	            	} else if(impacte.getStatus() == Status.Intoucher){
	            		impacte.setStatus(Status.Debuter);
	            	} else if(state.getStatus() == Status.Completer && impactMagasinage.getStatus() == Status.Completer){
	            		
	            	}
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> prepaiement() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.Prepaiement.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				StateStatus impactService = flowContext.getHistory().get(States.ConfiguarationService.getIndex());
            	if(impactService.getStatus() == Status.Intoucher){
            		impactService.setStatus(Status.Debuter);
            	}
				StateStatus impactCompte = flowContext.getHistory().get(States.ConfiguarationCompte.getIndex());
            	if(impactCompte.getStatus() == Status.Intoucher){
            		impactCompte.setStatus(Status.Debuter);
            	}
				StateStatus impactIndividu = flowContext.getHistory().get(States.DefinirIndividusAuCompte.getIndex());
            	if(impactIndividu.getStatus() == Status.Intoucher){
            		impactIndividu.setStatus(Status.Debuter);
            	}
				StateStatus impactFacturation = flowContext.getHistory().get(States.ModeFacturation.getIndex());
            	if(impactFacturation.getStatus() == Status.Intoucher){
            		impactFacturation.setStatus(Status.Debuter);
            	}
				StateStatus impactCommunication = flowContext.getHistory().get(States.PreferenceCommunication.getIndex());
            	if(impactCommunication.getStatus() == Status.Intoucher){
            		impactCommunication.setStatus(Status.Debuter);
            	}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> equifax() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.Equifax.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> paiement() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.Paiement.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> service() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.ConfiguarationService.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> compte() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.ConfiguarationCompte.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> individus() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.DefinirIndividusAuCompte.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> facturation() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.ModeFacturation.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> communication() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
	            	StateStatus state = flowContext.getHistory().get(States.PreferenceCommunication.getIndex());
	            	state.setStatus(Status.Completer);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
}
