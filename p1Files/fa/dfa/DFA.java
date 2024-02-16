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
        // searches hash map
        // if it's isFinal() at the end of the symbol searches, then it passes. 
        char[] arrOfSymb = s.toCharArray();
        String start = startState.getName();
        for(int i = 0; i < arrOfSymb.length; i++){
            if(start == null || delta.get(start).get(arrOfSymb[i]) == null){
                return false;
            }
            start = delta.get(start).get(arrOfSymb[i]);
        }
        if(isFinal(start)){
            return true;
        }
        return false;
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        Object stateArray[] = states.toArray(); 
        for(int i = 0; i < stateArray.length; i++){
            if(((DFAState) stateArray[i]).getName().equals(name)){
                DFAState state = new DFAState(name);
                return state;
            }
        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        Object stateArray[] = finalState.toArray(); 
        for(int i = 0; i < stateArray.length; i++){
            if(((DFAState) stateArray[i]).getName().equals(name)){
                return true;
            }
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
//            HashMap<Character, String> deltaRow = new HashMap<Character, String>();
//            deltaRow.put(onSymb, toState);
//            delta.put(fromState, deltaRow);
//            System.out.println(delta);
//            return true;
            delta.putIfAbsent(fromState, new HashMap<>());
            delta.get(fromState).put(onSymb, toState);
            return true;
        }
        return false;
//        if (!states.contains(fromState) || !states.contains(toState) || !sigma.contains(onSymb)) {
//            return false;
//        }
//        delta.putIfAbsent(fromState, new HashMap<>());
//        delta.get(fromState).put(onSymb, toState);
//        return true;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }
}
