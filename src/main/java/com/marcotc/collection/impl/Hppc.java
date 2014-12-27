package com.marcotc.collection.impl;

import com.carrotsearch.hppc.IntArrayList;
import com.carrotsearch.hppc.cursors.IntCursor;
import com.marcotc.collection.Implementation;
import com.marcotc.collection.Benchmark;

public class Hppc implements Implementation {
	
	@Override
	public Benchmark getAdd() {
		return new Add();
	}
	
	@Override
	public Benchmark getIterate() {
		return new Iterate();
	}

	@Override
	public Benchmark getIterator() {
		return new Iterator();
	}
	
	@Override
	public Benchmark getContains() {
		return new Contains();
	}
	
	@Override
	public Benchmark getRemove() {
		return new Remove();
	}
	
	@Override
	public Benchmark getClear() {
		return new Clear();
	}
	
	public static class Add implements Benchmark {
		IntArrayList list;
		
		@Override
		public void setup(int i) {
		}

		@Override
		public int execute(int i) {
			list = new IntArrayList(0);
	        for (int j = 0; j < i; j++) {
	            list.add(j);
	        }
	        
			return list.size();
		}
	}
	
	public static abstract class AbstractFullList implements Benchmark {
		IntArrayList list;
		
		@Override
		public void setup(int i) {
			list = new IntArrayList(0);
        	for (int j = 0; j < i; j++) {
        		list.add(j);
            }
		}
	}
	
	public static class Contains extends AbstractFullList {
		@Override
		public int execute(int i) {
	        for (int j = 0; j < i; j++) {
	            list.contains(j);
	        }
	        
			return list.size();
		}
	}
	
	public static class Iterate extends AbstractFullList {
		@Override
		public int execute(int i) {
			int x = 1;
            for (int j = 0; j < list.size(); j++) {
                x <<= list.get(j);
            }
            return x;
		}
	}
	
	public static class Iterator extends AbstractFullList {
		@Override
		public int execute(int i) {
			int x = 0;
			java.util.Iterator<IntCursor> iterator = list.iterator();
			while (iterator.hasNext()) {
				x += iterator.next().value;
			}
            return x;
		}
	}
	
	public static class Remove extends AbstractFullList {
		@Override
		public int execute(int i) {
			list.add(1);
			list.add(2);
			list.add(3);
			list.remove(list.size() - 1);
			list.remove(list.size()/2);
			list.remove(0);
			return list.size();
		}
	}
	
	public static class Clear extends AbstractFullList {
		@Override
		public int execute(int i) {
			list.release();
			return list.size();
		}
	}
}
