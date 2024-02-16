package fa.dfa;

import fa.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DFA implements DFAInterface{

    private HashSet<Character> sigma;
    private DFAState startState;
    private HashSet<DFAState> finalState;
    private HashSet<DFAState> states;
    private HashMap<String, HashMap<Character, String>> delta;

    public DFA (){
        sigma = new HashSet<Character>();
        startState = new DFAState();
        finalState = new HashSet<DFAState>();
        states = new HashSet<DFAState>();
        delta = new HashMap<String, HashMap<Character, String>>();
    }



    @Override
    public boolean addState(String name) {
        Object stateArray[] = states.toArray(); 
        for(int i = 0; i < stateArray.length; i++){
            if(((DFAState) stateArray[i]).getName().equals(name)){
                return false;
            }
        }
        DFAState newState = new DFAState(name);
        states.add(newState);
        System.out.println(states);
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        Object stateArray[] = states.toArray(); 
        for(int i = 0; i < stateArray.length; i++){
            if(((DFAState) stateArray[i]).getName().equals(name)){
                DFAState newState = new DFAState(name);
                finalState.add(newState);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        Object stateArray[] = states.toArray(); 
        for(int i = 0; i < stateArray.length; i++){
            if(((DFAState) stateArray[i]).getName().equals(name)){
                DFAState newState = new DFAState(name);
                startState = newState;
                return true;
            }
        }
        return false;
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
        boolean fromStateBool = false;
        boolean toStateBool = false;
        boolean onSymbBool = false;
        Object stateArray[] = states.toArray(); 
        for(int i = 0; i < stateArray.length; i++){
            if(((DFAState) stateArray[i]).getName().equals(fromState)){
                fromStateBool = true;
            }
        }
        for(int i = 0; i < stateArray.length; i++){
            if(((DFAState) stateArray[i]).getName().equals(toState)){
                toStateBool = true;
            }
        }
        if(sigma.contains(onSymb)){
            onSymbBool = true;
        }
        if(fromStateBool && toStateBool && onSymbBool == true){
            HashMap<Character, String> deltaRow = new HashMap<Character, String>();
            deltaRow.put(onSymb, toState);
            delta.put(fromState, deltaRow);
            return true;
        }
        return false;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }
}
