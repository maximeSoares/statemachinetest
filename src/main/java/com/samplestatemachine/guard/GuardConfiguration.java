package com.samplestatemachine.guard;

import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

import com.samplestatemachine.bean.Events;
import com.samplestatemachine.bean.FlowContext;
import com.samplestatemachine.bean.StateStatus;
import com.samplestatemachine.bean.States;
import com.samplestatemachine.bean.Status;

public class GuardConfiguration {
	@Bean
    public static Guard<States, Events> isClient() {
        return new Guard<States, Events>() {

            @Override
            public boolean evaluate(StateContext<States, Events> context) {
            	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
            	return flowContext.isClient();
            }
        };
    }
	@Bean
    public static Guard<States, Events> currentIsCompleted() {
        return new Guard<States, Events>() {

            @Override
            public boolean evaluate(StateContext<States, Events> context) {
            	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
            	int index = context.getStateMachine().getState().getId().getIndex();
            	return flowContext.getHistory().get(index).getStatus() == Status.Completer;
            }
        };
    }
	@Bean
    public static Guard<States, Events> currentIsNotInvalide() {
        return new Guard<States, Events>() {

            @Override
            public boolean evaluate(StateContext<States, Events> context) {
            	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
            	StateStatus source = flowContext.getHistory().get(context.getSource().getId().getIndex());
            	StateStatus target = flowContext.getHistory().get(context.getTarget().getId().getIndex());
            	return source.getStatus() != Status.Invalide && target.getStatus() != Status.Intoucher;
            }
        };
    }
	@Bean
    public static Guard<States, Events> passMagasinage() {
        return new Guard<States, Events>() {

            @Override
            public boolean evaluate(StateContext<States, Events> context) {
            	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
            	StateStatus state = flowContext.getHistory().get(context.getTarget().getId().getIndex());
				if(state.getStatus() == Status.Intoucher){
					flowContext.getHistory().get(context.getTarget().getId().getIndex()).setStatus(Status.EnCour);
				}
				return true;
            }
        };
    }
	@Bean
    public static Guard<States, Events> passCreationClient() {
        return new Guard<States, Events>() {

            @Override
            public boolean evaluate(StateContext<States, Events> context) {
            	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
            	StateStatus state = flowContext.getHistory().get(context.getTarget().getId().getIndex());
				if(state.getStatus() == Status.Intoucher){
					flowContext.getHistory().get(context.getTarget().getId().getIndex()).setStatus(Status.EnCour);
				}
				return true;
            }
        };
    }
}
