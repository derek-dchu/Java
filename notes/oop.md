# OOP Concept
## Class
A class is the blueprint from which individual objects are created.

### Constructor
* constructor cannot have return type nor return a value. It returns current instance.
* constructor with a return type will have warning, and the constructor method becomes a normal method.
* constructor is not inherited.
* constructor cannot be `final`.
* constructor cannot be `synchronized`.
* cannot use both this() and super() in a constructor

***note: always define the default constructor, because missing it may cause problem in inheritance, serialization.***


## Object
* Fields: state
* Methods: behavior

### `new` keyword
Create an object in heap.

### Object creating procedure
1. Keep chaining constructors (explicit constructor invocation) within the same class until we reach a constructor body which doesn't start with `this`.
2. execute superclass constructor (start from step 1 at superclass level).
3. execute the instance initializers and instance variable initializers for this class in textual order.
4. execute the rest of the body of constructors

***note: for final instance variable, its initializer will execute even before superclass constructor.***

### Constant Variable
A variable of primitive type of type `String` that is declared `static final` and can initialized only with a compile-time constant expression.


## Modifier
### Two groups  
1. access (`public`, `protected`, `private`)
2. `static`, `final`  

They can be in different order. But we usually put accessibility before static, final.

### Access Levels
| Modifier | Class | Package | Subclass | World |  
|----------|-------|---------|----------|-------|  
| public | Y | Y | Y | Y |  
| protected | Y | Y | Y | N |  
| no modifier |	Y | Y |	N |	N |  
| private | Y |	N | N |	N |


## Polymorphism: Override vs Overload
***note: Polymorphism can be applied only for non-static non-private METHODs. It is applied before object creation.***

### Method
A method can be defined as follow:  
**[access][static][final] RT methodName(params) [throws Exception] {body}**

  * Signature: the method's name and the parameter types.

#### `vararags`
`varargs` is used to pass an arbitrary number of values to a method as an array. To use `varargs`, we follow the type of the last parameter by an ellipsis (three dots, ...), then a space, and the parameter name. 

```java
public PrintStream printf(String format, Object... args)

System.out.printf("%s: %d, %s%n", name, idnum, address);
```

### Override
Subclass provides a specific implementation of a method that is already provided by its parent class.

* How to create a valid override
	To override method A into method B
	1. A cannot be static or final
	2. B & A have same methodName
	3. B & A have same params
	4. RT B "is a" RT A
	5. B has higher than or equal to the accessibility of A
	6. Exception of B "is a" Exception of A, and only for checked exception, or B doesn't throw exception (ExceptionB is null)

	* Covariant return type: method can be overridden by changing the return type if the return type is subclass type which is a covariant return type (>= Java 5).

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
* Defining a Method with the Same Signature as a Superclass's Method  
|   | Superclass Instance Method | Superclass Static Method |  
|---|----------------------------|--------------------------|  
| Subclass Instance Method | Overrides | compile-time error |  
| Subclass Static Method | compile-time error |	Hides |  

### Overload
In the same class, two methods with same method name, same return type, but different parameter list.

> A class have methods by same name but different parameters.

* overloaded method can be overridden.

* different parameters: different types, different order, different numbers.

### Runtime (Dynamic) Polymorphism vs Compile-time (Static) Polymorphism
* Override is Runtime Polymorphism
* Overload is Compile-time Polymorphism

```java
class A {
	void process() throws Exception() {
		throw new Exception();
	}
}
class B extends A {
	void process() {
		System.out.println("B");
	}
}
public static void main(String[] args) {
	A a = new B();
	a.process();
}
```
Above code results a compile-time error. Because A.process() will only be overridden by B.process() at runtime. During compile time, compiler is still checking A.process() which throws an exception and doesn't handle or re-throw by main(). If the main() also throws an exception, it will print "B".


## static vs Non-static
static member is class level, which will be loaded in stack (Because class are loaded in stack).

* static method cannot be overridden.
* static method cannot access instance variables or instance methods directly.

### Static Initialization Block
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

> Hello World
> Hello Foo
```
Static initialization block runs before main(), because static blocks run during loading the class.


## Nested Class
### Static Nested Class
* cannot refer directly to instance variables or methods defined in its enclosing class.

### Inner Classes
non-static nested classes.

* cannot define any static members itself.
* it has direct access to the methods and fields of its enclosing instance.
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

#### Local class
An inner class within a block (typically the body of a method). Because a local class is nested class that cannot be a member of top class.

* cannot access local variables in its enclosing scope that are not declared as `final` or effectively final.

***note: a local class cannot have access modifier `public`, `protected`, `private`, and modifier `static`.***

#### Anonymous class
An inner class within the body of a method without naming the class which is a expression.

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
An anonymous class expression consists:
1. `new`
2. name of interface or class
3. invocation of constructor
4. a class declaration body

* cannot declare constructors.
* cannot access local variables in its enclosing scope that are not declared as `final` or effectively final.


### Instantiate an nested class  
* static nested class: `A.C c = new A.C();`    
* inner class: `A a = new A(); a.B b = a.new B();` or `A.B b = new A().new B();`

### Compiled class name
* inner class: `<class>$<inner_class>.class`
* local inner class: `<class>$<function_num><local_inner_class>.class`
* anonymous inner class: `<class>$<function_num>.class`

***note: serialization of inner classes, including local and anonymous classes, is strongly discouraged.***

### Lambda Expressions


## Abstract class vs Interface
| Abstract class | Interface |
|----------------|-----------|
| partial implementation | no implementation |
| single inheritance | multiple inheritance |
| any fields | only constant<br> default: public static final |
| any methods | [public abstract] methods<br> default: public abstract |
| | Lazy-loading feature |

> Why only Interface allow multiple inheritance?  
> For example, in diamond paradigm, if class allow multiple inheritance, fields or methods may not be resolved (multiple inheritance of state). However, method within interface has to be overridden, therefore there is no resolving problem of interface multiple inheritance (multiple inheritance of type).

### Abstraction
Abstraction, a process of hiding the implementation details.

#### Abstract class
An abstract class IS same as other classes besides that it cannot be instantiated using `new` directly.

* Abstract class may not include abstract methods. However, If a class has abstract method, it must be abstract class.
* Abstract class can have constructor, but it cannot be called using `new` directly. It need to be extended and can call by super.
* Abstract class can extend other classes.

#### Abstract method
An abstract method consists of a method signature, but no method body.

* Any child class must either override the abstract method or declare itself abstract.

### Interface
An interface is a reference type, similar to a class, that can contain only constants, abstract methods, default methods (Java 8), static methods (Java 8), and nested types.

* cannot be instantiated - can only be implemented by classes or extends by other interfaces.
* the only methods that have implementations are default and static methods.
* static methods in interfaces are never inherited.
* Interface can have nested class, but the nested class has to be public static.

#### Default Methods (Java 8)
Default methods are new functionality added to the interfaces that have the binary compatibility with code written for older versions of those interfaces.

#### Marker interfaces
Interfaces that have no data member.
* Serializable, Cloneable, EventListener, RandomAccess, SingleThreadModel.


## Inheritance & Encapsulation
### Inheritance
Inheritance represents a 'is a' relation.

* Subclass inherit all members(fields, methods, and nested classes) from its superclass, except private members. However, subclass can has indirect access to all of the private members of its superclass via inherited public or protected methods, public or protected nested class.
* A class with only private constructor cannot be inherited.
 
### Encapsulation / Composition
Encapsulation represents a 'has a' (composition) relation.

* composition: holding the reference of the other class within some other class.


## The `Object` class
* `protected Object clone() throws CloneNotSupportedException`  
Creates and returns a copy of this object.

* `public boolean equals(Object obj)`  
Indicates whether some other object is "equal to" this one.

	* If we override `equals()`, we must override `hashCode()` as well.

* `protected void finalize() throws Throwable`  
  Called by the garbage collector on an object when garbage collection determines to reclaim memory of the object.

* `public final Class getClass()`  
  Returns the runtime class of an object.

  * cannot be overridden.
  * it returns <a class>.class. e.g. `String.class == "abc".getClass()`.

* `public int hashCode()`  
  Returns a hash code value for the object.

* `public String toString()`  
  Returns a string representation of the object.

### clone() vs Cloneable (#2)
```java
protected Object clone() throws CloneNotSuppertedException
```

To use clone(), a class has to implement Cloneable, or it throws CloneNotSuppertedException.

* Why Object.clone() is protected?
The method is protected because we shouldn't call it on Object, we can (and should) override it as public.
* Why we have to use `super.clone()` instead of `this.clone()` in override?
Because if we use this.clone(), it will end up with a infinity loop.


## Loose Coupling (decouple data and logic)
Big problem of OOP --> Coupling (inherite bugs)

### Old design
```java
class A {
	private int x;
	...

	public void foo1() {
		...
	}

	public void foo2() {
		...
	}
}

class B extends A {
	private double d;
	...

	@Override
	public void foo1() {
		...
	}
}

class C extends A {}
```

Problems of above code:
1. Coupling: if we have bugs in foo2(), both B and C will have the same bugs.
2. Multi-threading: race condition.

### Loose coupling design
```java
// Data bean
class A {
	private int x;
	...

	/* getters and setters */
}

class B extends A {}

// Logical bean
class AUtil {
	public void foo1(A a) {}
	public void foo2(A a) {}
}
```
Benefits:
1. Decoupling data and logic. Data bean is always bug free.
2. No share resource => less problem in multi-threading environment.


## The Reflection API
Every object is either a reference or primitive type. For every type of object, the JVM instantiates an immutable instance of `java.lang.Class` which it is the entry point for all of the Reflection APIs.

### Retrieving Class Objects
1. Get from instance using `Object.getClass()`
  * Array.getClass() => '[' (array) + 'I' (Base Type)

  	```java
	int[] a;
	a.getClass()	// [I
	String[][] str;
	str.getClass() 	// [[S
	```

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

2. Get from type by appending ".class" to the name of the type.

```java
boolean b;
Class c = b.getClass();   // compile-time error

Class c = boolean.class;  // correct
```

3. Get from the fully-qualified name using `Class.forName("<class name>")`. This name can be found by class `Class.getName()`.

4. Get the super class: `getSuperclass()`.
5. Get all member classes: `getClasses()`.
6. Get all explicitly declared member classes: `getDeclaredClasses()`.
7. Get the Class in which there members were declared: `getDeclaringClass()`.
  
  * note: Anonymous Class Declarations will not have a declaring class but will have an enclosing class.

8. Get the immediately enclosing class of the class: `getEnclosingClass()`.

### DIscovering Class Members
#### Field
`getDeclaredField()`: Private members
`getField()`: Inherited members
`getDeclaredFields()`: List of private members
`getFields()`: List of Inherited members

#### Method
`getDeclaredMethod()`: Private members
`getMethod()`: Inherited members
`getDeclaredMethods()`: List of private members
`getMethods()`: List of Inherited members

#### Constructor
`getDeclaredConstructor()`: Private constructor
`getConstructor()`: Non-private constructor
`getDeclaredConstructors()`: Private constructor
`getConstructors()`: Non-private constructor