import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Exercicios {
	public static void main(String[] args) {
//		HashMap<Integer, Integer> exemplo = calculateNotas(11257);
//		SortedSet<Integer> keys = new TreeSet<>(exemplo.keySet());
//		for (Integer key : keys) {
//			System.out.println(exemplo.get(key) + " nota(s) de R$ " + key + ".00");
//		}
		
//		fibonacciPosition(30);
//		makeAnagram("afbcdeffff", "defghffffa");
//		alternatingCharacters("ABABAB");
//		isValid("abcccbb");
//		int[] array= {1, 2, 3, 4, 100};
//		for(Integer i: sortArrayByParity(array)) {
//			System.out.print(i + " ");
//		}
//		String[] lista = {"1, 3, 9, 10, 17, 18", "1, 4, 9, 10"};
//		System.out.println(FindIntersection(lista));
	
//		System.out.println(centeredAverage(array));
		List<Integer> teste = new ArrayList<>();
		teste.add(1);
		teste.add(5);
		teste.add(3);
//		System.out.println(findLongestWord2("abppplee", teste));
//		int[] lista = {4, 3, 1, 2};
//		System.out.println(minimumSwaps(lista));
		
		List<List<Integer>> queries = new ArrayList<>();
		queries.add(teste);
		List<Integer> teste2 = new ArrayList<>();
		teste2.add(2);
		teste2.add(4);
		teste2.add(3);
		queries.add(teste2);
		System.out.println(arrayManipulation(5, queries));
	}
	
	//Exercicio Fibonacci, passa uma posição X 
	//e retorna qual o numero Fibonacci que está nessa posição
	public static void fibonacciPosition(Integer posicao) {
		long b, a = 1;
		long valorFinal = 0;
//		for(int i=0; i<posicao; i++) {//0 1 1 2 3 5 8
//			b = a;
//			a = valorFinal;
//			valorFinal = b + a;
//		}
//		valorFinal = fibonacciPositionRecursividade(posicao);		
		valorFinal = fibonacciPositionRecursividadePerformance(posicao, new Integer[posicao+1]);
		System.out.println("Fib(" + posicao + ") = " + valorFinal);
		
	}

	//OPÇÃO RECURSIVA DO EXERCICIO ACIMA
	public static int fibonacciPositionRecursividade(Integer posicao) {
		if (posicao < 2) return posicao;
		else return fibonacciPositionRecursividade(posicao-1) + fibonacciPositionRecursividade(posicao-2);
	}
	
	//OPÇÃO RECURSIVA PERFORMATICA DO EXERCICIO ACIMA
	public static int fibonacciPositionRecursividadePerformance(Integer posicao, Integer[] fibonacci) {
		if (fibonacci.length > 0 && fibonacci[posicao] != null) {
			System.out.println("cache value");
			return fibonacci[posicao];
		} else {
			if (posicao < 2) {
				fibonacci[posicao] = posicao;
				return fibonacci[posicao];
			} else {
				System.out.println("loooong time");
				fibonacci[posicao] = fibonacciPositionRecursividadePerformance(posicao - 1, fibonacci) + fibonacciPositionRecursividadePerformance(posicao - 2, fibonacci);
				return fibonacci[posicao];
			}
		}
	}
		
	// Exercicio de dividir um valor X em notas de 100, 50, 20, 10, 5, 2 e 1
	public static HashMap<Integer, Integer> calculateNotas(Integer valorTotal) {
		HashMap<Integer, Integer> retorno = new HashMap<Integer, Integer>();
		retorno.put(100, 0);
		retorno.put(50, 0);
		retorno.put(20, 0);
		retorno.put(10, 0);
		retorno.put(5, 0);
		retorno.put(2, 0);
		retorno.put(1, 0);

		int valor = 0;
		if (valorTotal >= 100) {
			valor = valorTotal / 100;
			retorno.put(100, valor);
			valorTotal = valorTotal - (100 * (valor));
		}
		if (valorTotal >= 50) {
			valor = valorTotal / 50;
			retorno.put(50, valor);
			valorTotal = valorTotal - (50 * (valor));
		}
		if (valorTotal >= 20) {
			valor = valorTotal / 20;
			retorno.put(20, valor);
			valorTotal = valorTotal - (20 * (valor));
		}
		if (valorTotal >= 10) {
			valor = valorTotal / 10;
			retorno.put(10, valor);
			valorTotal = valorTotal - (10 * (valor));
		}
		if (valorTotal >= 5) {
			valor = valorTotal / 5;
			retorno.put(5, valor);
			valorTotal = valorTotal - (5 * (valor));
		}
		if (valorTotal >= 2) {
			valor = valorTotal / 2;
			retorno.put(2, valor);
			valorTotal = valorTotal - (2 * (valor));
		}
		if (valorTotal >= 1) {
			retorno.put(1, valorTotal);
			valorTotal = valorTotal - (valorTotal);
		}
	
		return retorno;
	}

	public static List<List<Integer>> findRestaurants(List<List<Integer>> allLocations, int numRestaurants) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Map<List<Integer>, Double> distances = new TreeMap<List<Integer>, Double>();
		// aqui eu descobri todas as distancias
		allLocations.forEach(location -> {
			int x = location.get(0);
			int y = location.get(1);
			// x ao quadrado + y ao quadado e tira a raiz quadrada Math.sqrt
			double distance = Math.sqrt((x * x) + (y * y));
			distances.put(location, distance);
		});

		Map<List<Integer>, Double> sortedDistances = distances.entrySet().stream().sorted(Entry.comparingByValue())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, TreeMap::new));

		List<List<Integer>> lista = new ArrayList<>();
		sortedDistances.forEach((o1, o2) -> lista.add(o1));

		for (int i = 0; i < numRestaurants; i++) {
			result.add(lista.get(i));
		}

		return result;
	}

	public static List<List<Integer>> applicationPairs(int deviceCapacity, List<List<Integer>> foregroundAppList,
			List<List<Integer>> backgroundAppList) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Map<List<Integer>, Integer> tuplas = new HashMap<List<Integer>, Integer>();
		backgroundAppList.forEach(list -> {
			foregroundAppList.forEach(tuple -> {
				if (list.get(0) + tuple.get(0) <= deviceCapacity) {
					List<Integer> lista = new ArrayList<>();
					lista.add(list.get(0));
					lista.add(tuple.get(0));
					tuplas.put(lista, list.get(1) + tuple.get(1));
				}
			});
		});
		int maiorValor = 0;
		for (Integer v : tuplas.values()) {
			if (v > maiorValor) {
				maiorValor = v;
			} else if (v == deviceCapacity) {
				maiorValor = deviceCapacity;
				break;
			}
		}
//	    tuplas.forEach((o1, o2) -> {
//	    	if (o2== maiorValor) result.add(o1);
//	    });
		return result;
	}

	public static int makeAnagram(String a, String b) {
//		Map<Character, Integer> count = new HashMap<>();
//		for (char value : a.toCharArray()) {
//			int ct = count.containsKey(value)? count.get(value) : 0;
//			count.put(value, (ct+1));
//		}
//		
//		for (char value: b.toCharArray()) {
//			int ct = count.containsKey(value)? count.get(value): 0;
//			count.put(value, (ct-1));
//		}
//		
//		List<Integer> lista = new ArrayList<>(count.values());
//		int total = 0;
//		for (Integer i : lista) {
//			total += Math.abs(i);
//		}
//		return total;
		
		
		if (a.isEmpty() || b.isEmpty()){
            return 0;
        }
        char[] arrayA = a.toCharArray();
        String arrayB = b;
        int qtdLetrasIguais = 0; 
        
        for(char value: arrayA) {
            String valor = String.valueOf(value);
            if(arrayB.contains(valor)) {
                arrayB = arrayB.replaceFirst(valor, " ").trim();
                qtdLetrasIguais++;
            }
        }
        return (a.length()-qtdLetrasIguais)+(b.length()-qtdLetrasIguais);
		
	}
	
	public static int alternatingCharacters(String s) {

		int retorno = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			char a = s.charAt(i);
			if (a == 'A' && s.charAt(i + 1) != 'B')
				retorno++;
			if (a == 'B' && s.charAt(i + 1) != 'A')
				retorno++;
		}

		return retorno;
	}

	public static String isValid(String s) {

		HashMap<Character, Integer> cont = new HashMap<>();
		for (char value: s.toCharArray()) {
			int i = cont.containsKey(value)? cont.get(value):0;
			cont.put(value, (i+1));
		}
		HashMap<Integer, Integer> qtd = new HashMap<>();
		for(Integer value: cont.values()) {
			int i= qtd.containsKey(value)?qtd.get(value): 0;
			qtd.put(value, (i+1));
		}
		
		if(qtd.size()==1)return "YES";
		else if(qtd.size()==2) {
			int[] freq = qtd.keySet().stream().mapToInt(i -> i).toArray();
			for(int i=0; i<2; i++) {
				if(qtd.get(freq[i]) == 1 && 
						(freq[i]-freq[i^1]==1 || freq[i]-1 ==0)) return "YES";
			}
		}
		return "NO";
	}
	
	public static ArrayList<Integer> sortArrayByParity(int[] nums) {
        ArrayList<Integer> even =  new ArrayList<Integer>(), odds = new ArrayList<Integer>();
        
       for (int i:nums) {
    	   if(i%2==0) even.add(i);
    	   else odds.add(i);
       }
       even.addAll(odds);
        return even;
    }
	
	public static String FindIntersection(String[] strArr) {
	    // code goes here  
		String[] first = strArr[0].split(","), second = strArr[1].split(",");
		String retorno = "";
		
		for (int i=0; i<first.length; i++) {
			for(int j=0; j<second.length; j++) {
				if (first[i].contentEquals(second[j])) {
					if (retorno == "")retorno = first[i].trim();
					else retorno = retorno + "," + first[i].trim();
				}
			}
		}
		
	    return retorno == ""? "false": retorno;
	  }
	
	public static Integer centeredAverage(int[] lista) {
		int cont=lista.length-2, soma = 0, min = lista[0], max = lista[0];
		
		for(int i=0; i<=lista.length-1; i++) {
			soma += lista[i];
			if (lista[i]<min) min = lista[i];
			if (lista[i]>max) max = lista[i];
			
		}
		soma = soma - max - min;
		return soma/cont;
	}
	
	//Se for 13 não soma, e oq vem logo depois do 13 tb nao soma
	public static int sum13(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 13 && (i == 0 || nums[i - 1] != 13))
				sum += nums[i];
		}

		return sum;
	}
	
	//Time complexity O (n^2)
	public static int minimumAbsoluteDifference(List<Integer> arr) {
		int min = 0;
		
		for (int i=0; i<arr.size()-1; i++) {
			for (int j = i+1; j<arr.size(); j++) {
				int dif = Math.abs(arr.get(i)-arr.get(j));
				if (dif == 1)return dif;
				if(min == 0 || dif < min) min = dif;
			}
		}
		
		return min;
	}
	
	//Time Complexity O(n)
	public static int minimumAbsoluteDifference2(List<Integer> arr) {
		Collections.sort(arr);
		
		int min = 0;
		
		for (int i=0; i<arr.size()-1; i++) {
			int dif = Math.abs(arr.get(i)-arr.get(i+1));
				if (dif == 0) return dif;
				if(min == 0 || dif < min) min = dif;
		}
		
		return min;
	}
	
	//Question from google interviews: https://techdevguide.withgoogle.com/resources/former-interview-question-find-longest-word/#!
	public static String findLongestWord(String s, List<String> d) {
		String[] sArray = s.split("");
		int index = 0;
		boolean exists;
		d.sort(Comparator.comparingInt(String::length).reversed()); //Se eu realizei o sort, garanto que a maior palavra vem primeiro
		System.out.println(d.toString());
		
		for(String value : d) {
			String[] dWord = value.split(""); //kangaroo
			for (int i=0; i<dWord.length; i++) {
				exists = false;
				while(index<sArray.length) { //abppplee
					if(dWord[i].equalsIgnoreCase(sArray[index])) {
						index++;
						exists = true;
						break;
					}
					index++;
				}
				if(exists && i+1==dWord.length) return value;
			}
			index = 0;
		}
		
		return "nada";
	}
	
	public static String findLongestWord2(String s, List<String> d) {
		String[] sList = s.split("");
		HashMap<String, List<Integer>> sHash = new HashMap<>();
		for(int i=0; i<sList.length; i++) {
			sHash.putIfAbsent(sList[i], new ArrayList<Integer>());
			sHash.get(sList[i]).add(i);
		}
		d.sort(Comparator.comparingInt(String::length).reversed());
		
		for (String value : d) {
			int lastIndex = -1;
			String[] dWord = value.split("");
			for(int i=0; i<dWord.length; i++) {
				final int index = new Integer(lastIndex);
				Optional<Integer> newIndex = sHash.get(dWord[i]) == null? Optional.empty(): sHash.get(dWord[i]).stream().filter(x -> x>index).findFirst();
				if(sHash.get(dWord[i]) != null && newIndex.isPresent()) {
					lastIndex = newIndex.get();
				} else {
					break;
				}
				if(i+1==dWord.length) return value;
			}
		}
		
		return "";
	}
	

	//Bubble Sort Method
	public static void countSwaps(List<Integer> a) {
		int countSwaps=0;
		
		for (int j=a.size(); j > 0; j--) {
			for (int i=0; i<a.size()-1; i++) {
				if(a.get(i) > a.get(i+1)) {
					int hold = a.get(i+1);
					a.remove(i+1);
					a.add(i, hold);
					countSwaps++;
				}
			}
		}
		
		System.out.println("Array is sorted in "+ countSwaps + " swaps.");
		System.out.println("First Element: " + a.get(0));
		System.out.println("Last Element: " + a.get(a.size()-1));
	}
	
	
	public int solution(int[] A, String[] D) {
        // write your code in Java SE 8
		HashMap<String, List<Integer>> pagPorMes = new HashMap<>();
		for (int i=0; i<D.length; i++) {
			String[] arrayData = D[i].split("-");//2020-02-03
			String mes = arrayData[1];//02
			pagPorMes.putIfAbsent(mes, new ArrayList<Integer>());
			pagPorMes.get(mes).add(A[i]);
		}
		
		int balancoFinal = 0;
        int soma = 0;
			int somaCard = 0;
			int contCard = 0;
			int months = 12;
		for (String mes : pagPorMes.keySet()) {
			for (Integer value : pagPorMes.get(mes)) {
				if (value < 0) { 
					somaCard -= value;
					contCard++;
				}
				else soma += value;
			}
			if (contCard >= 3 && somaCard >=100) months--;  
		}
		balancoFinal = soma + somaCard - (5 * months);
		
		return balancoFinal;
    }
	
	//Method that returns the biggest sum of the arr(6x6 array), looking like an hour glass
	//   x x x y y y
	//   y x y y y y
	//   x x x y y y
	//   y y y y y y
	//   y y y y y y
	//   y y y y y y
	public static int hourglassSum(List<List<Integer>> arr) {
		Integer sumFinal = null;
		for (int i = 0; i < arr.size() - 2; i++) {
			List<Integer> firstLine = arr.get(i);
			List<Integer> secondLine = arr.get(i + 1);
			List<Integer> thirdLine = arr.get(i + 2);
			for (int j = 0; j < firstLine.size() - 2; j++) {
				Integer sumTemp = firstLine.get(j) + firstLine.get(j + 1) + firstLine.get(j + 2) + secondLine.get(j + 1)
						+ thirdLine.get(j) + thirdLine.get(j + 1) + thirdLine.get(j + 2);
				if (sumFinal == null || sumTemp > sumFinal)
					sumFinal = sumTemp;
			}
		}
		return sumFinal;
	}
	
	public static List<Integer> rotLeft(List<Integer> a, int d) {
		List<Integer> retorno = new ArrayList<>();

		for (int i = d; i < a.size(); i++) {
			retorno.add(a.get(i));
		}
		for (int i = 0; i < d; i++) {
			retorno.add(a.get(i));
		}

		return retorno;
	}
	
	//Fila de brinquedo, onde um cliente pode subornar até 2 na sua frente p passar na frente
	//Mas permanece com seu mesmo numero anterior de ticket, o objetivo é contar quantos subornos
	//ocorreram total, porém se ocorrer +2 subornos p uma pessoa == Too Chaotic
	public static void minimumBribes(List<Integer> q) {
		
		int cont = 0;
		for (int i=q.size()-1; i>=0; i--) {
			if(q.get(i) != i+1) {
				if(q.get(i-1) == i+1) {
					//Realizar uma troca
					cont++;
					Collections.swap(q, i-1, i);
					
				} else if (q.get(i-2) == i+1) {
					//Realizar 2 trocas
					cont += 2;
					Collections.swap(q, i-2, i-1);
					Collections.swap(q, i-1, i);
					
				} else {
					System.out.println("Too chaotic");
					return;
				}
			}
		}
		System.out.println(cont);
	}
	
	static int minimumSwaps(int[] arr) {
		int cont = 0;
		HashMap<Integer, Integer> numXind = new HashMap<>();
		for(int i=0; i<arr.length; i++) {
			if (i+1 != arr[i]) {
				numXind.put(arr[i], i); //Mapeei todos que precisam de swap
			}
		}
	
		for (int i=0; i<arr.length; i++) {//Sorting o array do inicio ao fim
			System.out.println("Quantidade de chaves: "+ numXind.size() + "// Chave da vez:"+ arr[i]);
			numXind.keySet().stream().forEach(key -> System.out.println(key));
			
			if (numXind.containsKey(arr[i])) {//precisa ser trocado só que pelo index eu já sei quem deveria estar aqui
				cont++;
				
				int key = arr[i];
				arr[i] = numXind.get(key)+1;//Coloco quem deveria estar nessa posicao
				arr[numXind.get(arr[i])] = key; // Coloco a antiga chave na antiga posicao do numero acima
				
				numXind.replace(key, numXind.get(arr[i])); //o 4 está no index que o 1 estava
				if(numXind.get(key)== key-1){
					numXind.remove(key);
				}
				
				numXind.remove(arr[i]); //removo do map pq já tá no lugar certo
				System.out.println(arr[0]+ " "+arr[1]+ " "+arr[2]+ " "+arr[3]);
			}
		}
		return cont;
    }
	
	public static long arrayManipulation(int n, List<List<Integer>> queries) {
		Long[] finalSum = new Long[n+1];
		
		for (List<Integer> list : queries) {
			long start = list.get(0); //1
			long end = list.get(1); //5
			long value = list.get(2); //3
			
//			while(start <= end) {//O(end-start)
//					finalSum[(int)start] = Long.sum(finalSum[(int)start]==null?0:finalSum[(int)start], value);
//					start++;
//			}
			
			Arrays.stream(finalSum, (int)start, (int)end).map(obj -> obj==null?0+value: obj+value);
			
		}
		Optional<Long> maxOp = Arrays.stream(finalSum).filter(value -> value != null).max(Long::compare);
		
		return maxOp.isPresent()?maxOp.get():0;
	}
}
