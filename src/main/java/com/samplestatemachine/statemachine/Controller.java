package com.samplestatemachine.statemachine;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samplestatemachine.bean.Events;
import com.samplestatemachine.bean.FlowContext;
import com.samplestatemachine.bean.StateStatus;
import com.samplestatemachine.bean.States;


@RestController
public class Controller {
	@Autowired
	private StateMachine<States, Events> stateMachine;

	@RequestMapping("/next")
	public StateStatus publisNext(){
        stateMachine.sendEvent(Events.VALID);
		FlowContext flowContext = stateMachine.getExtendedState().get("flowContext", FlowContext.class);
		stateMachine.sendEvent(Events.NEXT);
		return flowContext.getHistory().get(stateMachine.getState().getId().getIndex());
	}
	
	@RequestMapping("/client")
	public States publisClient(){
        stateMachine.sendEvent(Events.VIEW_CreationClient);
		return stateMachine.getState().getId();
	}
	
	@RequestMapping("/prospect")
	public States publisProspect(){
        stateMachine.sendEvent(Events.VIEW_CreationProspect);
		return stateMachine.getState().getId();
	}
	
	@RequestMapping("/magasinage")
	public States publisMagasinage(){
        stateMachine.sendEvent(Events.VIEW_Magasinage);
		return stateMachine.getState().getId();
	}
	
	@RequestMapping("/livraison")
	public States publisLivraison(){
        stateMachine.sendEvent(Events.VIEW_Livraison);
		return stateMachine.getState().getId();
	}
	
	@RequestMapping("/technicien")
	public States publisTechnicien(){
        stateMachine.sendEvent(Events.VIEW_Technicien);
		return stateMachine.getState().getId();
	}
	
	@RequestMapping("/complet")
	public StateStatus publisCompleted(){
		stateMachine.sendEvent(Events.VALID);
		FlowContext flowContext = stateMachine.getExtendedState().get("flowContext", FlowContext.class);
		return flowContext.getHistory().get(stateMachine.getState().getId().getIndex());
	}
	
	@RequestMapping("/current")
	public StateStatus publisCurrent(){
		FlowContext flowContext = stateMachine.getExtendedState().get("flowContext", FlowContext.class);
		return flowContext.getHistory().get(stateMachine.getState().getId().getIndex());
	}
	
	@RequestMapping("/list")
	public ArrayList<StateStatus> publisList(){
		FlowContext flowContext = stateMachine.getExtendedState().get("flowContext", FlowContext.class);
		return flowContext.getHistory();
	}
	
	@RequestMapping("/finish")
	public boolean publisfinish(){
		return stateMachine.isComplete();
	}
}
