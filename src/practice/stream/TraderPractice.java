package practice.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class TraderPractice {

	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul","Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");

		List<Transaction> transactions = Arrays.asList(
				new Transaction(brian, 2011,300),
				new Transaction(raoul, 2012,1000),
				new Transaction(raoul, 2011,400),
				new Transaction(mario, 2011,710),
				new Transaction(mario, 2011,700),
				new Transaction(alan, 2011,950)
		);
		
		//1. 211년에 일어난 모든 트랜잭션을 찾아서 오름차순 정렬
		List<Transaction> a =  transactions.stream()
				.filter(transaction -> transaction.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getValue))
				.collect(Collectors.toList());
		
		System.out.println(a);
		
		//2. 거래자가 근무하는 모든 도시를 중복 없이 나열
		List<String> b =  transactions.stream()
				.map(Transaction::getTrader)
				.map(Trader::getCity).distinct()
				.collect(Collectors.toList());
		
		System.out.println(b);
		
		//3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬
		String c =  transactions.stream()
				.map(Transaction::getTrader)
				.map(Trader::getName)
				.sorted() //중복된 이름 제거
				.reduce("",(n1,n2)->n1+n2);
		
		System.out.println(c);
		
		//5. 밀라노에 거래자가 있는가
		boolean d= 	transactions.stream()
						.anyMatch(transaction -> transaction
						.getTrader()
						.getCity().equals("Milan"));
				//7. 케임브리지에 거주하는 모든 거래자의 트랜잭션 값 출력
				transactions.stream()
					.filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
					.map(Transaction::getValue)
					.forEach(System.out::println);
				
				//7. 전체 트랜잭션중 최대값
				Optional<Integer> f = transactions.stream()
				.map(Transaction::getValue)
				.reduce(Integer::max);
				
				System.out.println(f);
				
				//8. 전체 트랜잭션중 최대값
				Optional<Integer> g = transactions.stream()
				.map(Transaction::getValue)
				.reduce(Integer::min);
				
				System.out.println(g);
					
	}



}
