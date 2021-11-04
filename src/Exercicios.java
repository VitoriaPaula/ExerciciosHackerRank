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
	public static void main(String[] args) {
		HashMap<Integer, Integer> exemplo = calculateNotas(11257);
		SortedSet<Integer> keys = new TreeSet<>(exemplo.keySet());
		for (Integer key : keys) {
			System.out.println(exemplo.get(key) + " nota(s) de R$ " + key + ".00");
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

}
