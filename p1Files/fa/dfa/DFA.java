package fa.dfa;

import fa.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class DFA implements DFAInterface{

    private LinkedHashSet<Character> sigma;
    private DFAState startState;
    private LinkedHashSet<DFAState> finalState;
    private LinkedHashSet<DFAState> states;
    private HashMap<String, HashMap<Character, String>> delta;

    public DFA (){
        sigma = new LinkedHashSet<Character>();
        startState = new DFAState();
        finalState = new LinkedHashSet<DFAState>();
        states = new LinkedHashSet<DFAState>();
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
//            return true;
            delta.putIfAbsent(fromState, new HashMap<>());
            delta.get(fromState).put(onSymb, toState);
                        System.out.println(delta);

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
        // Step 1: Create a new DFA instance
        DFA newDFA = new DFA();

        // Copy states and alphabet
        for (State state : this.states) {
            newDFA.addState(state.getName());
        }
        for (char symbol : this.sigma) {
            newDFA.addSigma(symbol);
        }

        // Copy start and final states
        newDFA.setStart(this.startState.getName());
        for (DFAState finalState : this.finalState) {
            newDFA.setFinal(finalState.getName());
        }

        // Step 2: Swap the transitions
        for (HashMap.Entry<String, HashMap<Character, String>> entry : this.delta.entrySet()) {
            String fromState = entry.getKey();
            HashMap<Character, String> transitions = entry.getValue();
            for (HashMap.Entry<Character, String> transition : transitions.entrySet()) {
                char symbol = transition.getKey();
                String toState = transition.getValue();

                // Swap symbols if they match symb1 or symb2
                if (symbol == symb1) {
                    newDFA.addTransition(fromState, toState, symb2);
                } else if (symbol == symb2) {
                    newDFA.addTransition(fromState, toState, symb1);
                } else {
                    // If the symbol is neither symb1 nor symb2, copy it as is
                    newDFA.addTransition(fromState, toState, symbol);
                }
            }
        }

        // Step 3: Return the new DFA
        return newDFA;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();

        // Append the set of states (Q)
        builder.append("Q = { ");
        states.forEach(state -> builder.append(state).append(" "));
        builder.append("}\n");

        // Append the alphabet (Sigma)
        builder.append("Sigma = { ");
        sigma.forEach(symbol -> builder.append(symbol).append(" "));
        builder.append("}\n");

        // Append the transition function (delta)
        builder.append("delta =\n\t");
        sigma.forEach(symbol -> builder.append("\t").append(symbol));
        builder.append("\n");
        states.forEach(state -> {
            builder.append(state);
            sigma.forEach(symbol -> {
                String toState = delta.containsKey(state.getName()) && delta.get(state.getName()).containsKey(symbol)
                        ? delta.get(state.getName()).get(symbol)
                        : "";
                builder.append("\t").append(toState);
            });
            builder.append("\n");
        });

        // Append the start state (q0)
        builder.append("q0 = ").append(startState).append("\n");

        // Append the set of final states (F)
        builder.append("F = { ");
        finalState.forEach(fState -> builder.append(fState).append(" "));
        builder.append("}");
        System.out.println(builder.toString());

        return builder.toString();
    }
}
