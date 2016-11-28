package com.samplestatemachine.statemachine;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import com.samplestatemachine.bean.Events;
import com.samplestatemachine.bean.States;


@Configuration
@EnableStateMachine
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<States, Events> {
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
            .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
            .withStates()
			.initial(States.Recherche)
            .choice(States.ChoiceIsClient)
            .choice(States.ChoiceHasDoMagasinage)
            .end(States.PreferenceCommunication)
            .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
		.withExternal()
			.source(States.Recherche).target(States.CreationClient)
			.action(ActionConfiguration.CreationClient())
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.VIEW_CreationClient)
			.and()
		.withExternal()
			.source(States.Recherche).target(States.CreationProspect)
			.action(ActionConfiguration.CreationProspect())
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.VIEW_CreationProspect)
			.and()
		.withExternal()
			.source(States.CreationProspect).target(States.Magasinage)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withExternal()
			.source(States.Magasinage).target(States.Technicien)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withExternal()
			.source(States.Technicien).target(States.LivraisonEtRecuperationEquipement)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withExternal()
			.source(States.LivraisonEtRecuperationEquipement).target(States.Sommaire)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withExternal()
			.source(States.Sommaire).target(States.ChoiceIsClient)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withChoice()
			.source(States.ChoiceIsClient)
            .first(States.Prepaiement, GuardConfiguration.isClient())
            .last(States.CreationClient)
			.and()
		.withExternal()
			.source(States.CreationClient).target(States.ChoiceHasDoMagasinage)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withChoice()
			.source(States.ChoiceHasDoMagasinage)
            .first(States.Prepaiement, GuardConfiguration.hasDoMagasiange())
            .last(States.Magasinage)
			.and()
		.withExternal()
			.source(States.Prepaiement).target(States.Equifax)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withExternal()
			.source(States.Equifax).target(States.Paiement)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withExternal()
			.source(States.Paiement).target(States.ConfiguarationCompte)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withExternal()
			.source(States.ConfiguarationCompte).target(States.DefinirIndividusAuCompte)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withExternal()
			.source(States.DefinirIndividusAuCompte).target(States.ModeFacturation)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT)
			.and()
		.withExternal()
			.source(States.ModeFacturation).target(States.PreferenceCommunication)
			.action(ActionConfiguration.pageviewAction())
			.action(ActionConfiguration.history())
			.event(Events.NEXT);
    }
	
	
	@Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change to " + to.getId());
                if(from != null){
                	System.out.println("from: " + from.toString());
                }
                System.out.println("to: " + to.toString());
                
            }
        };
    }
}