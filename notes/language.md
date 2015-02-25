## Variables
* Instance Variables (Non-Static Fields)
* Class Variables (Static Fields)
* Local Variables
* Parameters


## Primitive Data Types
| Type | Length | Range | Default |  
|------|--------|-------|---------|   
| byte | 8-bit | -128 ~ 127 | 0 |  
| short | 16-bit | -32,768 ~ 32,767 | 0 |  
| int | 32-bit | -2^31 ~ 2^31-1 | 0 |  
| long | 64-bit | -2^63 ~ 2^63-1 | 0L |  
| float | 32-bit | single-precision | 0.0f |  
| double | 64-bit | double-precision | 0.0d |  
| boolean | no precisely defined | true/false | false |  
| char | 16-bit | '\u0000' ~ '\uffff' | '\u0000' |  

**Note:** local variables never assigned a default value. Accessing an uninitialized local variable will result in compile-time error.


## Arrays
An array is a container object that holds a fixed number of values of a single type.

### Creating, Initializing
```java
// 1
int[] anArray = new int[10];
anArray[0] = 100;
anArray[1] = 200;

// 2
int [] anArray = {100, 200};
```


## Enum Types
**Note:** all enums implicitly extend `java.lang.Enum`, therefore it cannot extend anything else.


## Control Flow
### `switch` statement
`switch` works with `byte`, `short`, `char`, `int`, `Enum`, `String` (Java 7), `Character, `Short, `Byte`, `Integer`.

**Note:** if expression in any switch statement is `null`, then a `NullPointerException` is thrown.


## `Number` Class
An final and immutable wrapper classes for each of the numeric primitive data types.


## `Character` Class
An final and immutable wrapper class for `char`.


## String
**Note:** String is final, immutable, compareable, serializable.

* String implements `CharSequence`.

### HashCode
```
for each character c in string:
	hashCode = c + 31 * hashCode
```

* why choose 31?
  1. prime number: evenly distributed

### String pool and intern()  
There is a string pool in stack which contains non-duplicated strings. Those strings will be reused by references. `intern()` returns canonical copy of string from the string pool. Create a string using new keyword will firstly create a string in string pool and then copy it into heap to create an String object.

### String vs StringBuffer vs StringBuilder  
* String is immutable.  
* StringBuffer, StringBuilder are mutable.  
* StringBuffer is thread-safe.  
* StringBuilder (Java 5) is not thread-safe which has better performance.

> **Note:** thread-safe means:
>    1. the class is thread-safe: only one thread can access that class at a time.
>    2. the program is thread-safe: return consistence output.


## Constant pool
If the value p being boxed is `true`, `false`, a `byte`, or a `char` in the range `\u0000` to `\u007f`, or an `int` or `short` number between `-128` and `127` (inclusive), then let `r1` and `r2` be the results of any two boxing conversions of p. It is always the case that `r1 == r2`.


## Autoboxing vs Unboxing
### Autoboxing (Java 5)
Automatic conversion from primitive type to corresponding wrapper class.

```java
Integer x = 10; int y = x;

List<Integer> li = new ArrayList<>();
li.add(10);
```

* Passed as a parameter to a method that expects an object of the corresponding wrapper class.
* Assigned to a variable of the corresponding wrapper class.

### Unboxing
reverse operation of autoboxing.


## Generics
Generics enable `types` (classes and interface) to be parameters when defining classes, interfaces and methods. It is used in collection api, class, function.

> Without generics, collection api, some classes, some functions will return Object, so that we need to downcast to proper class. If we use generics, it limits to a specified type, that is why it is type-safe.

* Stronger type checks at compile time (Type safe), thus reducing errors at runtime.
* Elimination of casts.
* generic algorithms.

### Generic Methods
Methods that introduce their own type parameters.

* For static generic method, the type parameter section must appear before the method's return type.

```java
public staic <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) { /* ... */ }
```

### Multiple Bounds
* class must be specified first.

```java
Class A { /* ... */ }
interface B { /* ... */ }
interface C { /* ... */ }

class D <T extends A & B & C> { /* ... */ }

class E <T extends B & A & C> { /* ... */ }  // compile-time error
```

### WildCard
It represents an unknown type.

* What is the different between Object and WildCard (?)

```java
List<String> list = new ArrayList<String>();
List<Object> list2 = list;	// compile error
List<?> list3 = list;		// works
```
Because, although `String` is a `Object`, `List<String>` is NOT a `List<Object>`. However, every `List` is a `List<?>`

### `extends` vs `super`
`List<? extends A> list`
* extend: itself and subclass

`List<? super A>list`
* super: itself and superclass

> **Note:** class A can be an interface

### Wildcard Guidelines: 
`method(in, out)`

* "in": `extends`.
* "out": `super`.
* In the case where the "in" variable can be accessed using methods defined in the Object class, use an unbounded wildcard.
* In the case where the code needs to access the variable as both an "in" and an "out" variable, do not use a wildcard.

### Type Erasure
Instead of creating new classes
* replace all type parameters.
* insert type casts.
* generate bridge methods.


## Immutable vs Final 
### Immutable
value can not be changed.

** (#1) How to define an immutable class:**
* declare class as final OR make the constructor private and construct instances in factory methods
* all fields are `private` and `final`
* constructor for initialization
* no setters - methods that modify fields or objects referred to by fields.
* *!!* If the instance fields include references to mutable objects, don't allow those objects to be changed:
  1. don't provide methods that modify the mutable objects.
  2. don't share references to the mutable objects. Never store references to external, create copies in constructor. Similarly, create copies of your internal mutable objects in getter.

Benefits: Immutable object is thread-safe and commonly used in multi-threading environment.

### Final
The value of the field cannot change.


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


## Java file
### .java file without file name
It is valid. It can be compiled and run with inner public class name.

### Java file without a public class
It runs normally. If a class has the same name as Java file name, it is default to be public.


## Main Function 
* without public or static: Runtime error.
* main function can be overloaded.
* main function can be final.

## Import
* package/class can imported multiple times but JVM will load only once.
* static import: access static method without qualify it by the class name.


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

* final object still can change value.
* final method cannot be override.
* final class cannot be inherited.

### finally
* finally block will not be executed if program exits `system.exit()` or fatal error (e.g. OutOfMemoryError).

### finalize()
A callback method that may be invoked by garbage collection, which performs like a destructor in C++. It can be override.


## Garbage Collection
A separate thread with the lowest priority.

* How to invoke garbage collection
  1. `System.gc();`
  2. `Runtime.getRuntime().gc();`
* A lost reference might be obtained back.

### When an object becomes eligible for Garbage Collection
1. If it is not reachable from any live thread.
2. If it is not reachable by any strong reference.
3. Cyclic dependencies are not counted as reference. e.g. A -> B, B -> A, both A and B are eligible.


## Multi-threading


## Lazy-loading
Abstract class don't have Lazy-loading feature


## instanceof vs getClass()
```java
class A
class B extends A
class C extends B

A a = new C()
B b = (B) a;
C c = (C) a;

a instanceof A, B, C // true
b instanceof A, B, C // true
c instanceof A, B, C // true

a,b,c getClass() // C
```
If an object is `instanceof` of class, that means the object can do either upcasting or downcasting to that class.


## Serialiable
### What is a serialiable object?
A serialiable object can be converted into a binary string, so that it can be transfered via Internet or saved to a file.

### Serialization vs Deserialization
Serialization: convert a serialiable object into abinary string.

* For an class, each level of fields need to be serializable (regarding `transient`) to perform serialization.
* For an class, it has at less one superclass that is serializable to perform serialization.

Deserialization: convert the binary string back to an object.

* Deserialzation will construct the object directly without calling its constructor. However, if the class is subclass, then it will call constructors of its superclass which are not serializable.

### What is the purpose of `SerialVersionUID`?
It is a `private static final long` variable. It is a class uuid that generates serialization and deserialization perform on the same class.

### `transient` keyword
Make a field non-serialiable in a serialiable class.

* We can put `transient` in any class.
* We can put `transient` in any field.
* Statics cannot be serialize, because it is implicitly `transient`.

### Create a deep copy using serializable
**note:** If an object implements serializable at each level, then deep copy of the object can be made via serializable.


## Externalizable
It is a subinferface of Serialiable which contains two methods: `readExternal()`, `writeExternal()`.

Add customization to serialization. (e.g. encryption)

* `transient` has no effect in externalizable object.
* externalizable class will call default constructor to deserialize.


