# OOP Concept
## Class
A class is the blueprint from which individual objects are created.

### Constructor
* constructor cannot have return type nor return a value. It returns current instance.
* constructor with a return type will have warning, and the constructor method becomes a normal method.
* constructor is not inherited.
* constructor cannot be final.
* cannot use both this() and super() in a constructor

***note: always define the default constructor, because missing it may cause problem in inheritance.***


## Object
* Fields: state
* Methods: behavior

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
	5. B has higher than or equal to accessibility A
	6. Exception of B "is a" Exception of A, and only for checked exception.

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
In the same class, two methods with same method name, same return type, but different parameter list.

> A class have methods by same name but different parameters.

* overloaded method can be override.

* different parameters: different types, different order, different numbers.


## static vs Non-static
static member is class level, which will be loaded in stack (Because class are loaded in stack).

* static method cannot be override.
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
### Static Nested Classed
* cannot refer directly to instance variables or methods defined in its enclosing class.

### Inner Classes
non-static nested classes.

* cannot define any static members itself.
* it has direct access to the methods and fields of its enclosing instance.
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

* cannot access local variables in its enclosing scope that are not declared as `final` or effectively final.
* cannot declare constructors.

### Instantiate an nested class  
* static nested class: `A.C c = new A.C();`    
* inner class: `A a = new A(); a.B b = a.new B();` or `A.B b = new A().new B();`

### Compiled class name
* inner class: `<class>$<inner_class>.class`
* local inner class: `<class>$<function_num><local_inner_class>.class`
* anonymous inner class: `<class>$<function_num>.class`

***note: serialization of inner classes, including local and anonymous classes, is strongly discouraged.***

### Lambda Expressions
