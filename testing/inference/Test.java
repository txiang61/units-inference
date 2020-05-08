import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class Test<K, V>
	// extends AbstractDiskCache<K, V>
 //    implements IRequireScheduler
{
	// public void test(String fileName) {
	// 	List<String> list = new ArrayList<>();
	// 	try {
	// 		Stream<String> stream = Files.lines(Paths.get(fileName));
 //            list = (List<String>) stream.collect(Collectors.toList());
 //        } catch (IOException ex) {
 //        }
	// }

    public Map<K, Map<K, V>> processGetMatching( String pattern )
    {
    	Set<K> matchingKeys = null;//getKeyMatcher().getMatchingKeysFromArray( pattern, keyArray );

        Map<K, Map<K, V>> elements = matchingKeys.stream()
            .collect(Collectors.toMap(null, null)).entrySet().stream()
            .filter(null)
                .collect(Collectors.toMap(null, null));

        return elements;
    }
}


