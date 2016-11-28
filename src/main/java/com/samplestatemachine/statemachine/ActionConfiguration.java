package com.samplestatemachine.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import com.samplestatemachine.bean.Events;
import com.samplestatemachine.bean.FlowContext;
import com.samplestatemachine.bean.States;

public class ActionConfiguration {
	@Bean
	public static Action<States, Events> CreationClient() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext(true));
				} else {
					flowContext.setClient(true);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	
	@Bean
	public static Action<States, Events> CreationProspect() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext(false));
				} else {
					flowContext.setProspect(true);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
			}
		};
	}
	@Bean
	public static Action<States, Events> history() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext(false));
				} else {
					flowContext.getHistory().add(context.getSource().getId());
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
			}
		};
	}
	@Bean
	public static Action<States, Events> pageviewAction() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				String variable = context.getTarget().getId().toString();
				Integer count = context.getExtendedState().get(variable, Integer.class);
				if (count == null) {
					context.getExtendedState().getVariables().put(variable, 1);
				} else {
					context.getExtendedState().getVariables().put(variable, (count + 1));
				}
			}
		};
	}
}
