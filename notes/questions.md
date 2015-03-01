## OOP Concepts
### 1. What is wrong with the structure.
```
			Computer
			  / \
		Window	UNIX
		/   \       \
	Desktop Laptop  WorkStation
```
It is wrong because it means Window is Computer and Desktop is window vs inheritance.

Correct one
```
				  Interface
		Computer ----------- OS (functional, different
		/   |    \ 				implementation)
  Desktop Laptop WorkStation

		OS
		/\
 Windows UNIX
```


## Polymorphism
### 1. Overload vs Override
```java
// a
void foo(int x)
// b
void foo(Integer x)
// c
int foo(int x)

// a & b : overload
// a & c, b & c : neither override nor overload


// a
void setName(String fn, String ln);
// b
void setName(String ln, String fn);
// a & b : override

### 2. When and Where Polymorphism Applied.
```java
class A {
	protected String name = null;
	protected void init() {
		name = "A";
	}
	protected String getName() {
		return name;
	}
	A() { init(); }
}

class B extends A {
	protected String name = null;
	protected void init() {
		name = "B";
	}
	protected String getName() {
		return name;
	}
	B() { }
}

A a = new B();
a.getName(); // null
```
1. A.name = null 
2. A call B.init() -> B.name = "B"
!! But no B yet
3. B start to initialize B.name = null
B is created with B.name = null


## The `Object` Class
### 1. Fina a pair of string s1, s2 that `s1.hashCode() == s2.hashCode()`, but `s1.equals(s2) == false` 


## String
### 1. Check if the substring only occurs once
```java
str.indexOf(substr) == str.lastIndexOf(substr)
```

### 2. String pool
```java
String s1 = "abc";
String s2 = "abc";
String s3 = new String("abc");

print(s1 == s2) // True
print(s1 == s3) // False
print(s1.equals(s3)) // True

print(s1 == s3.intern()) // True

boolean inPool(String str) {
	return str == str.intern();
}
```

### 3. string pool and pass by value of reference
```java
class A {
	int x;
	A(int x) { this.x = x; }
}
void foo(A bar) {
	bar.x = 100;
}
A a = new A(10);
foo(a);
print(a.x); // 100

void foo(String s) {
	s = "inFoo";
}
String str = "abc";
foo(str);
print(str); // abc
```
Because String immutable, "inFoo" is created. "abc" doesn't change, however, s the copy of str, points to "inFoo".


## Immutable vs Final
### 1. final object
```java
class A {
	int x = 10;
}
final A a = new A();

a.x = 20; // works
a = new A(); // exception
```

### 2. Write an immutable Person class.


## Boxing vs Autoboxing
### 1. constant pool
```java
// for number within [-128, 127]
Integer x = 100;
Integer y = 100;
x == y // True

// for other numbers
Integer x = 1000;
Integer y = 1000;
x == y // False
```


## Casting
### 1. Promotion
```java
short x = 6;
short y = 7;
short z = x + y; // compile error
Result of integer arithmetic operation will only be long (if there is a long) or int.
```

### 2. Float vs Double
```java
float x = 1.2; // compile error: incompatible types, 1.2 is double
float x = 1.2F;
```

### 3. Boolean vs Number
```java
int x = 0;
if (x) {} // compile error: incompatible types.
```

## Garbage collection
### 1. Create an object that can not be garbage collected
```java
class A {
	static final Object obj = ... ;
}
```

### 2. How to minimize garbage collection?
Object Pooling -> Factory Pattern


## Heap vs Stack
### 1. If a program uses the heap faster than garbage collection, we need to increase the heap size.  
`java -Xms1GB -Xmx4GB <class filename>`

### 2. How to dump JVM (heap overflow)
```java
Object[] obj = null;
while(true) {
	obj = new Object[] { obj };
}
```


## Others
### 1. 
```java
void foo(int ... vals, String str); // not work
void foo(String str, int ... vals); // work
```

## Static
```java
class A {
	static {
		print("static in A");
	}
	A() { print("A"); }
}
class B extends A {
	static {
		print("static in B");
	}
	B() { print("B"); }
}
A a = new B();

// static in A 	(load A)
// static in B 	(load B)
// A 			(create A)
// B 			(create B)
```

## Collection
### 1. Print out a linked list in reverse order

### 2. Find the entry of the loop within a linked list

### 3. Use stack to build a queue
Use two stacks, inStack & outStack. input -> inStack -> outStack -> output.

### 4. Print a map in the order of value
```java
void printMap(Map<String, Integer> map) {
	
}
```

### 5. Implement shuffling functionality
```
int n = n.length;
Random rnd = new Random();
for (int i=n; i>1; i--)
	swap(list, i-1, rnd.nextInt(i));
```

### 6. Poker Game
Order:
1. SPADE: A K Q J 10 ... 2
2. HEART: 
3. DIAMOND:
4. CLUB:

Separate cards and display to 4 players.

TODO:
1. Use Enum type
2. Override toString for A K Q J
3. How to compare cards

### 7. Write a person class with one field (name), which can be insert into TreeSet.


## Multi-threading
1. write a multi-threading program to compute `x*y / (x+y)`.


## {2,3,5} 72 output, 2,3,5 based.
for (int i = 0; i < 72; i++) {
	x = i;
	fint = x % 2;
	x /= 2;
	secint = x % 3;
	x /= 3;
	thrint = x % 5;
	x /= 5;
}
