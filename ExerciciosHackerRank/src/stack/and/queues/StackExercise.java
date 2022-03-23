package stack.and.queues;

import java.util.Stack;

public class StackExercise {

	Stack<Integer> stack = new Stack<>();
	
	public void main(String[] args) {
		//PENSE QUE É UMA PILHA DE PRATOS, LIFO!!!
		
		//Adiciona elemento no FINAL do stack
		stack.add(2);
		
		//Retorna o length do stack
		stack.capacity();
		
		//Retona o ultimo elemento da stack
		stack.peek();
		
		//Remove e retorna o ultimo elemento da stack
		stack.pop();
		
		//Adiciona um elemento no FINAL da stack
		stack.push(2);
	}
}
