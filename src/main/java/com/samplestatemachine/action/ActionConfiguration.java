package com.samplestatemachine.action;

import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import com.samplestatemachine.bean.Events;
import com.samplestatemachine.bean.FlowContext;
import com.samplestatemachine.bean.StateStatus;
import com.samplestatemachine.bean.States;
import com.samplestatemachine.bean.Status;

public class ActionConfiguration {
	@Bean
	public static Action<States, Events> CreationClient() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
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
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
					flowContext.setProspect(true);
					context.getExtendedState().getVariables().put("flowContext", flowContext);
				}
				System.out.println(flowContext);
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
				FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
				if (flowContext == null) {
					context.getExtendedState().getVariables().put("flowContext", new FlowContext());
				} else {
					StateStatus state = flowContext.getHistory().get(context.getTarget().getId().getIndex());
					if(state.getStatus() == Status.Intoucher){
						flowContext.getHistory().get(context.getTarget().getId().getIndex()).setStatus(Status.EnCour);
					}
				}

				context.getExtendedState().getVariables().put("flowContext", flowContext);
			}
		};
	}
}
