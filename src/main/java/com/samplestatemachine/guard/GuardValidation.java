package com.samplestatemachine.guard;

import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

import com.samplestatemachine.bean.Events;
import com.samplestatemachine.bean.FlowContext;
import com.samplestatemachine.bean.StateStatus;
import com.samplestatemachine.bean.States;
import com.samplestatemachine.bean.Status;

public class GuardValidation {

	@Bean
	public static Guard<States, Events> valideCustomer() {
	    return new Guard<States, Events>() {
	
	        @Override
	        public boolean evaluate(StateContext<States, Events> context) {
	        	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
	        	StateStatus creationClient = flowContext.getHistory().get(States.CreationClient.getIndex());
	        	StateStatus creationProspect = flowContext.getHistory().get(States.CreationProspect.getIndex());
	        	return creationClient.getStatus() == Status.Completer  
	        			|| creationProspect.getStatus() == Status.Completer;
	        }
	    };
	}

	@Bean
	public static Guard<States, Events> valideClient() {
	    return new Guard<States, Events>() {
	
	        @Override
	        public boolean evaluate(StateContext<States, Events> context) {
	        	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
	        	StateStatus creationClient = flowContext.getHistory().get(States.CreationClient.getIndex());
	        	return creationClient.getStatus() == Status.Completer ;
	        }
	    };
	}

	@Bean
	public static Guard<States, Events> valideMagasinage() {
	    return new Guard<States, Events>() {
	
	        @Override
	        public boolean evaluate(StateContext<States, Events> context) {
	        	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
	        	StateStatus state = flowContext.getHistory().get(States.Magasinage.getIndex());
	        	return state.getStatus() == Status.Completer  ;
	        }
	    };
	}

	@Bean
	public static Guard<States, Events> validTechnicien() {
	    return new Guard<States, Events>() {
	
	        @Override
	        public boolean evaluate(StateContext<States, Events> context) {
	        	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
	        	StateStatus state = flowContext.getHistory().get(States.Technicien.getIndex());
	        	return state.getStatus() == Status.Completer  ;
	        }
	    };
	}

	@Bean
	public static Guard<States, Events> validModeLivraison() {
	    return new Guard<States, Events>() {
	
	        @Override
	        public boolean evaluate(StateContext<States, Events> context) {
	        	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
	        	StateStatus state = flowContext.getHistory().get(States.ModeLivraison.getIndex());
	        	return state.getStatus() == Status.Completer  ;
	        }
	    };
	}

	@Bean
	public static Guard<States, Events> validePrerequisPaiement() {
	    return new Guard<States, Events>() {
	
	        @Override
	        public boolean evaluate(StateContext<States, Events> context) {
	        	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
	        	StateStatus ModeLivraison = flowContext.getHistory().get(States.ModeLivraison.getIndex());
	        	StateStatus CreationClient = flowContext.getHistory().get(States.CreationClient.getIndex());
	        	StateStatus Equifax = flowContext.getHistory().get(States.Equifax.getIndex());
	        	return ModeLivraison.getStatus() == Status.Completer ||
	        			CreationClient.getStatus() == Status.Completer ||
	        			Equifax.getStatus() == Status.Completer;
	        }
	    };
	}

	@Bean
	public static Guard<States, Events> validePaiement() {
	    return new Guard<States, Events>() {
	
	        @Override
	        public boolean evaluate(StateContext<States, Events> context) {
	        	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
	        	StateStatus state = flowContext.getHistory().get(States.Paiement.getIndex());
	        	return state.getStatus() == Status.Completer  ;
	        }
	    };
	}

}
