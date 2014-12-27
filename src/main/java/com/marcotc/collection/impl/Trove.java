package com.marcotc.collection.impl;

import com.marcotc.collection.Implementation;
import com.marcotc.collection.Benchmark;

import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.array.TIntArrayList;

public class Trove implements Implementation {
	
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
		TIntArrayList list;
		
		@Override
		public void setup(int i) {
		}

		@Override
		public int execute(int i) {
			list = new TIntArrayList(0);
	        for (int j = 0; j < i; j++) {
	            list.add(j);
	        }
	        
			return list.size();
		}
	}
	
	public static abstract class AbstractFullList implements Benchmark {
		TIntArrayList list;
		
		@Override
		public void setup(int i) {
			list = new TIntArrayList(0);
        	for (int j = 0; j < i; j++) {
        		list.add(j);
            }
		}
	}
	
	public static class Contains extends AbstractFullList {
		@Override
		public int execute(int i) {
	        for (int j = 0; j < i; j++) {
	            list.indexOf(j); // contains() goes on reverse order
	        }
	        
			return list.size();
		}
	}
	
	public static class Iterate extends AbstractFullList {
		@Override
		public int execute(int i) {
			int x = 0;
            for (int j = 0; j < list.size(); j++) {
                x += list.getQuick(j);
            }
            return x;
		}
	}
	
	public static class Iterator extends AbstractFullList {
		@Override
		public int execute(int i) {
			int x = 0;
			TIntIterator iterator = list.iterator();
			while (iterator.hasNext()) {
				x += iterator.next();
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
			list.removeAt(list.size() - 1);
			list.removeAt(list.size()/2);
			list.removeAt(0);
			return list.size();
		}
	}
	
	public static class Clear extends AbstractFullList {
		@Override
		public int execute(int i) {
			list.resetQuick();
			return list.size();
		}
	}
}
