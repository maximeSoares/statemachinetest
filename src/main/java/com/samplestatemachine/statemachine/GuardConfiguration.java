package com.samplestatemachine.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

import com.samplestatemachine.bean.Events;
import com.samplestatemachine.bean.FlowContext;
import com.samplestatemachine.bean.States;

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
    public static Guard<States, Events> hasDoMagasiange() {
        return new Guard<States, Events>() {

            @Override
            public boolean evaluate(StateContext<States, Events> context) {
            	FlowContext flowContext = context.getExtendedState().get("flowContext", FlowContext.class);
            	return flowContext.getHistory().contains(States.Magasinage);
            }
        };
    }
}
