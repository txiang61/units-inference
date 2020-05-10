import units.qual.*;

public class Test {

	void test() {
		double d;
		double temp;
		d = (@deg double) 45.0;
		temp = Math.toRadians(d);
		temp = Math.cos(temp);
	}

}
// extends AbstractDiskCache<K, V>
//    implements IRequireScheduler
//{
    // public void test(String fileName) {
    // 	List<String> list = new ArrayList<>();
    // 	try {
    // 		Stream<String> stream = Files.lines(Paths.get(fileName));
    //            list = (List<String>) stream.collect(Collectors.toList());
    //        } catch (IOException ex) {
    //        }
    // }

//     public Map<K, Map<K, V>> processGetMatching(String pattern) {
//         Set<K> matchingKeys =
//                 null; // getKeyMatcher().getMatchingKeysFromArray( pattern, keyArray );

//         Map<K, Map<K, V>> elements =
//                 matchingKeys.stream()
//                         .collect(Collectors.toMap(null, null))
//                         .entrySet()
//                         .stream()
//                         .filter(null)
//                         .collect(Collectors.toMap(null, null));

//         return elements;
//     }
// }
