package fa.dfa;

import fa.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DFA implements DFAInterface{

    private HashSet<Character> sigma;
    private  State startState;
    private HashSet<State> finalState;
    private HashSet<State> states;
    private HashMap<State, Character> delta;

    public DFA (){
        sigma = new HashSet<Character>();
        startState = new DFAState();
        finalState = new HashSet<State>();
        states = new HashSet<State>();
        delta = new HashMap<State, Character>();
    }



    @Override
    public boolean addState(String name) {
        if(states.contains(name)){
            return false;
        }
        DFAState newState = new DFAState(name);
        states.add(newState);
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        if(finalState.contains(name) && !states.contains(name)){
            return false;
        }
        DFAState newState = new DFAState(name);
        finalState.add(newState);
        return true;
    }

    @Override
    public boolean setStart(String name) {
        if(startState.getName() == null && !states.contains(name))
            return false;
        DFAState newState = new DFAState(name);
        startState = newState;
        return true;
    }

    @Override
    public void addSigma(char symbol) {
        if(sigma.contains(symbol)){
            return;
        }
        sigma.add((Character)symbol);
    }

    @Override
    public boolean accepts(String s) {
        return false;
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        if(!states.contains(name)){
            return null;
        }
        DFAState state = new DFAState(name);
        return state;
    }

    @Override
    public boolean isFinal(String name) {
        if(finalState.contains(name)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        if(startState.getName().equals(name)){
            return true;
        }
        return false;
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        return false;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }
}
