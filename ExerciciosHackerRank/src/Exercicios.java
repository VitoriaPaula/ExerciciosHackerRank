import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Exercicios {
	
	// Exercicio de dividir um valor X em notas de 100, 50, 20, 10, 5, 2 e 1
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)) {
			Integer value = scanner.nextInt();
			
			Integer[] notes = {100, 50, 20, 10, 5, 2, 1};
			
			System.out.println(value);
			for (int i = 0; i < notes.length; i++) {
				System.out.println(value/notes[i] + " nota(s) de R$ " + notes[i] + ",00");
				value = value % notes[i];
			}
		}
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

}
