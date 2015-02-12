## Java file
### .java file without file name
It is valid. It can be compiled and run with inner public class name.

### Java file without a public class
It runs normally. If a class has the same name as Java file name, it is default to be public.


## Main Function 
* without public or static: Runtime error.
* main function can be overloaded.
* main function can be final.


## Constructor vs normal methods
### Constructor
* constructor cannot have return type nor return a value. It returns current instance.
* constructor with a return type will have warning, and the constructor method becomes a normal method.
* constructor is not inherited.
* constructor cannot be final.
* can't use both this() and super() in a constructor

***note: always define the default constructor, because missing it may cause problem in inheritance.***


## Modifier
### Two groups  
1. Accessibility (Public, Protected, Private)
2. static, final  

They can be in different order. But we usually put accessibility before static, final.


## Encapsulation & Inheritance  
### Inheritance
Inheritance represents a 'is a' relation.

* Subclass inherit all members(fileds, methods, and nested classes) from its superclass, except private member. However, subclass can has indirect access to all of the private members of its superclass via inherited public or protected methods, nested class.
 
### Encapsulation / Composition
Encapsulation represents a 'has a' (composition) relation.

* composition: holding the reference of the other class within some other class.


## Polymorphism: Override vs Overload
***note: Polymorphism can be applied only for non-static non-private METHODs. It is applied before object creation.***

### Override
Subclass provides a specific implementation of a method that is already provided by its parent class.

* How to create a valid override
> method can be defined as follow:  
> [accessibility][static][final] RT methodName(params) [throws Exception]

	To override method A into method B
	1. A cannot be static or final
	2. B & A have same methodName
	3. B & A have same params
	4. RT B "is a" RT A
	5. accessibility B has bigger than or equal to accessibility A
	6. Exception B "is a" Exception A, and only for checked exception.

	* Covariant return type: method can be override by changing the return type if the return type is subclass type which is a covariant return type (>= Java 5).
	```java
	class A {}
	class B extends A {}

	class C { A getFoo() { return new A(); } }
	class D extends C {
		//Overriding getFoo() in father class C
		B getFoo() { 
			return new B();
		}
	}
	```

### Overload
Same function name, same return type, different parameters, in same class.

> A class have methods by same name but different parameters.

* overloaded method can be override.

* Different Parameters: different types, different order, different numbers.  
* Signature: the method's name and the parameter types.


## Object Class
* It is the superclass for every class
```java
// 1
public String toString();

// 2
public boolean equals(Object another);
```


## String pool and intern()  
There is a string pool in stack which contains non-duplicated strings. Those strings will be reused by references. intern() returns canonical copy of string from the string pool. Create a string using new keyword will first create a string in string pool and then copy it to heap to create an String object.


## Constant pool
If the value p being boxed is `true`, `false`, a `byte`, or a `char` in the range `\u0000` to `\u007f`, or an `int` or `short` number between `-128` and `127` (inclusive), then let `r1` and `r2` be the results of any two boxing conversions of p. It is always the case that `r1 == r2`.

11. New keyword  
	Create new object in heap.


## Immutable vs Final 
### Immutable
value can not be changed.

\#1 How to write an immutable class:
* class is final
* all members are final, private
* only getters, no setters
* constructors for initialization

Benefits: Immutable object is thread-safe and commonly used in multi-threading environment.

### Final
reference can not be changed. But the value can be changed.

***note: String is both final and immutable.***


## String vs StringBuffer vs StringBuilder  
* String is immutable.  
* StringBuffer, StringBuilder are mutable.  
* StringBuffer is thread-safe.  
* StringBuilder (Java 5) is not thread-safe which has better performance.

> note: thread-safe means only one thread can access the class at a time.


## Boxing vs Autoboxing  
* primitive type & wrapper class  
* boxing: convert primitive type to wrapper class object
* autoboxing (Java 5): 'Integer x = 10; int y = x;'

***note: all wrapper classes are immutable.***


## Upcast vs Downcast
### Upcast  
* Primitive: shorter byte to longer byte.
* Objects: upcast by "is a".

### Downcast
* Primitive: longer byte to shorter byte.  
* Objects: downcast from parent reference to child.

### Conversion:
* primitive
  1. closest upper primitive type
  2. wrapper class  
  
* class  
closest upper class in the inheritance tree. If there are more than one class available, there will be a compile error.


## \#2 clone() vs Cloneable 
```java
protected Object clone() throws CloneNotSuppertedException
```

To use clone(), a class has to implement Cloneable, or it throws CloneNotSuppertedException.

* Why Object.clone() is protected?
The method is protected because we shouldn't call it on Object, we can (and should) override it as public.


## Abstract class vs Interface
| Abstract class | Interface |
|----------------|-----------|
| partial implementation | no implementation |
| single inheritance | multiple inheritance |
| any fields | [public static final] fields<br> default: public static final |
| any methods | [public abstract] methods<br> default: public abstract |
| | Lazy-loading feature |

> Why only Interface allow multiple inheritance?  
> For example, in diamond paradigm, if class allow multiple inheritance, method cannot be resolved. However, method within interface has to be override, therefore there is no resolving problem of interface multiple inheritance.

### Abstraction
Abstraction, a process of hiding the implementation details.

#### Abstract class
An abstract class IS same as other classes besides that it cannot be instantiated using `new` directly.

* Abstract class don't need to have abstract method. However, If a class has abstract method, it must be abstract class.
* Abstract class can have constructor, but it cannot be called using `new` directly. It need to be extended and can call by super.
* Abstract class can extend other classes.

#### Abstract method
An abstract method consists of a method signature, but no method body.

* Any child class must either override the abstract method or declare itself abstract.

### Interface

#### Marker interfaces
interface that have no data member.
* Serializable, Cloneable, EvetListener, RandomAccess, SingleThreadModel.


## static vs Non-static
static member is class level, which will be loaded in stack (Because class are loaded in stack).

* static method cannot be override.

### static block
```java
class HelloWorld {
	static String str = "Hello World";
	public static void main(String[] args) {
		print(str);
	}
	static {
		print(str);
		str = "Hello Foo";
	}
}

//> Hello World
//> Hello Foo
```
static block runs before main(), because static blocks run during loading the class.


## final, finally, finalize
Initialize final variable
```java
// 1
class A {
	final int x = 10;
}

// 2
class A {
	final int x;
	A(int x) { this.x = x; }
}

// 3
// cannot assign a value to final variable x
class A {
	final int x;
	A(int x) { init(x); }
	void init(int x) { this.x = x; }
}
```
Only 1 & 2 can be used. A final variable that is not initialized at the time of declaration which can be initialize only in constructor.

***note: Use `static final` to define constant variable. It can only be initialized by assigning a value directly (as 1 above).***

* final object still can change value.
* final method cannot be override.
* final class cannot be inherited.

### finally
* finally block will not be executed if program exits (`system.exit()` or fatal error)

### finalize()
```java
protected void finalize();
```
It will be called garbage collection, which performs like a destructor in C++. It can be override.


## Reflection
```java
public final Class<?> getClass();

class A { f() }
class B extends A { g() }
class C extends B { h() }

A a = new C();
B b = (B) a;
C c = (C) a;

a.getClass() // C
b.getClass() // C
c.getClass() // C
```
```
a.f() √ a.g() X a.h() X  
b.f() √ b.g() √ b.h() X  
c.f() √ c.g() √ c.h() √ 
```

### getClass()
```java
int[] a;
a.getClass()	// [I
String[][] str;
str.getClass() 	// [[S
```
Array.getClass() => '[' (array) + 'I' (Base Type)


## Inner Class
* Interface can have inner class, but the inner class has to be public static.
* Inner class can be extended by outer class, but the outer class has to define constructor.
```java
class A { class B {} }

class C extends A.B {
	C() {
		new A().super();
	}
	C(A a) {
		a.super();
	}
}
```

### Instantiate an inner class  
static inner class (nested class): `A.C c = new A.C();`    
inner class: `A a = new A(); a.B b = a.new B();` or `A.B b = new A().new B();`

### Local Inner class
inner class within a function.

### Anonymous Inner class
```java
inferface I {
	void foo();
}

// anonymous inner class
I a = new I() {
	@Override
	public void foo() {
		//implementation
	}
};
```
### Compiled class name
* inner class: `<class>$<inner_class>.class`
* local inner class: `<class>$<function_num><local_inner_class>.class`
* anonymous inner class: `<class>$<function_num>.class`


## Garbage Collection
* How to invoke garbage collection
`System.gc();`
`Runtime.getRuntime().gc();`

## Multi-threading


## Import
* package/class can imported multiple times but JVM will load only once.
* static import: access static method without qualify it by the class name.


## Lazy-loading
Abstact class don't have Lazy-loading feature

