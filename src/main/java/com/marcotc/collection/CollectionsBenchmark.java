package com.marcotc.collection;

import java.util.Arrays;

import com.google.caliper.AfterExperiment;
import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;
import com.marcotc.collection.impl.FastUtils;
import com.marcotc.collection.impl.Gs;
import com.marcotc.collection.impl.Hppc;
import com.marcotc.collection.impl.Jdk;
import com.marcotc.collection.impl.Joda;
import com.marcotc.collection.impl.Trove;

public class CollectionsBenchmark {

	public static void main(String[] args) {
		// @formatter:off
        String[] args2= new String[] {
        		"-Db=ADD",
        		
                "-Dsize=0,1,10,100,1000",
                //"-Db=ADD,ITERATE,ITERATOR,CONTAINS,REMOVE,CLEAR",
                "-Dimpl=JDK,HPPC,TROVE,GS,FAST_UTILS,JODA",
                "--time-limit=60s",
                //"-iallocation",
        		//"-iruntime",
                //"--verbose"
                };
        // @formatter:on
		CaliperMain.main(BenchmarkClass.class, args2);
	}

	public static class BenchmarkClass {
		@Param
		int size;
		int actualSize;
		@Param
		String b; // Benchmark field is reserved by Caliper
		@Param
		String impl;

		Benchmark fixture;

		private static enum Implementations {
			// @formatter:off
        	TROVE(new Trove()),
        	GS(new Gs()),
        	HPPC(new Hppc()),
        	JDK(new Jdk()),
        	FAST_UTILS(new FastUtils()),
        	JODA(new Joda()),
        	;
        	// @formatter:on

			final Implementation impl;

			Implementations(Implementation impl) {
				this.impl = impl;
			}
		}

		private static enum Benchmarks {
			ADD, ITERATE, ITERATOR, CONTAINS, REMOVE, CLEAR
		}

		@BeforeExperiment
		void setUp() {
			actualSize = size;

			Implementations implementation = Implementations.valueOf(impl);

			switch (Benchmarks.valueOf(b)) {
			case ADD:
				fixture = implementation.impl.getAdd();
				break;
			case ITERATE:
				fixture = implementation.impl.getIterate();
				break;
			case ITERATOR:
				fixture = implementation.impl.getIterator();
				break;
			case CONTAINS:
				fixture = implementation.impl.getContains();
				actualSize = (int) Math.min(Math.log(size) / Math.log(2), 1);
				break;
			case REMOVE:
				fixture = implementation.impl.getRemove();
				break;
			case CLEAR:
				fixture = implementation.impl.getClear();
				break;
			default:
				throw new IllegalArgumentException(b
						+ " is not a valid benchmark. Possible values are: "
						+ Arrays.toString(Benchmarks.values()));
			}

			fixture.setup(size);
		}

		@AfterExperiment
		void after() {
		}

		// @Macrobenchmark
		@com.google.caliper.Benchmark
		int timeFast(long reps) {
			int x = (int) System.nanoTime();
			for (int i = 0; i < reps; i++) {
				x += fixture.execute(actualSize);
			}
			return x;
		}
	}
}
