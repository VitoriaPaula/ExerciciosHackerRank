package linked.list;

import java.util.LinkedList;

public class LinkedListExercises {

	LinkedList<Integer> list = new LinkedList<>();
	
	//Methods 
	public void main(String[] args) {
		//Adicionar um elemento tail / head
		list.add(2); /* é igual a */ list.addLast(2);
		list.addFirst(2);
		
		//Devolve um Iterator na ordem de tail -> head
		list.descendingIterator();
		
		//Pega e não remove o elemento head
		list.element();
		
		//Busca na lista pelo index / head / tail
		list.get(0); list.getFirst(); list.getLast();
		
		//Pega sem remover elemento (head / head / tail)
		list.peek(); list.peekFirst(); list.peekLast();
		
		//Pega e remove elemento (head / head / tail)
		list.poll(); list.pollFirst(); list.pollLast();
		
		//Remove o primeiro elemento / index / tail
		list.pop(); /* igual a */ list.removeFirst();
		list.remove(); list.remove(0); list.removeLast();
		
		//Insere um elemento no inicio da lista
		list.push(2);
	}
}
