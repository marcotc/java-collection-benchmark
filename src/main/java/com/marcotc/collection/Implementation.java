package com.marcotc.collection;

public interface Implementation {
	Benchmark getAdd();

	Benchmark getIterate();

	Benchmark getIterator();

	Benchmark getContains();

	Benchmark getRemove();

	Benchmark getClear();
}
