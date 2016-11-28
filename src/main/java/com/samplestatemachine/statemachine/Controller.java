package com.samplestatemachine.statemachine;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.transition.Transition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samplestatemachine.bean.Events;
import com.samplestatemachine.bean.FlowContext;
import com.samplestatemachine.bean.States;


@RestController
public class Controller {
	@Autowired
	private StateMachine<States, Events> stateMachine;

	@RequestMapping("/next")
	public States publisNext(){
        stateMachine.sendEvent(Events.NEXT);
		return stateMachine.getState().getId();
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
	
	@RequestMapping("/current")
	public FlowContext publisCurrent(){
		FlowContext flowContext = stateMachine.getExtendedState().get("flowContext", FlowContext.class);
		flowContext.current = stateMachine.getState().getId();
		return flowContext;
	}
	
	@RequestMapping("/list")
	public Set<States> publisList(){
        Set<States> list = new HashSet<>();
		for (Transition<States, Events> transition : stateMachine.getTransitions()) {
			if(transition.getSource().equals(stateMachine.getState())) {
				list.add(transition.getTarget().getId());
			}
		}
		
		return list;
	}
	
	@RequestMapping("/complet")
	public boolean publisComplet(){
		return stateMachine.isComplete();
	}
}
