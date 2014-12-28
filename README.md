Java Collection Benchmark
=========================
A collection of microbenchmarks to compare different implementation of various Java collection data structures.

Data structures tested:
* Primitive integer array list
* More to come... (Planned: Object lists, sorted lists, queues, maps and sets)

Tested libraries:
* [FastUtils](http://fastutil.di.unimi.it/)
* [Gs](https://github.com/goldmansachs/gs-collection)
* [Hppc](http://labs.carrotsearch.com/hppc.html)
* [Jdk](http://docs.oracle.com/javase/7/docs/api/java/util/package-summary.html)
* [Joda](http://www.joda.org/joda-primitives/)
* [Trove](https://bitbucket.org/robeden/trove/)
* Planned: [Koloboke](https://github.com/OpenHFT/Koloboke), [Javolution](http://javolution.org/)

#### [:checkered_flag: Latest results](http://marcotc.github.io/java-collection-benchmark-results.htm)

### Implementation details
Current implementation uses [Caliper](https://code.google.com/p/caliper/) for microbenchmarking. ([JMH](http://openjdk.java.net/projects/code-tools/jmh/) was considered, but I couldn't find a way to accurately measure the number of objects created during a benchmark run)
