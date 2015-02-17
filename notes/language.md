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

***note: local variables never assigned a default value. Accessing an uninitialized local variable will result in compile-time error.***


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
***note: all enums implicitly extend `java.lang.Enum`, therefore it cannot extend anything else.***


## Control Flow
### `switch` statement
`switch` works with `byte`, `short`, `char`, `int`, `Enum`, `String`, `Character, `Short, `Byte`, `Integer`.

*note: if expression in any switch statement is `null`, then a `NullPointerException` is thrown.*


## Java file
### .java file without file name
It is valid. It can be compiled and run with inner public class name.

### Java file without a public class
It runs normally. If a class has the same name as Java file name, it is default to be public.


## Main Function 
* without public or static: Runtime error.
* main function can be overloaded.
* main function can be final.

















## Inheritance & Encapsulation
### Inheritance
Inheritance represents a 'is a' relation.

* Subclass inherit all members(fields, methods, and nested classes) from its superclass, except private member. However, subclass can has indirect access to all of the private members of its superclass via inherited public or protected methods, nested class.
 
### Encapsulation / Composition
Encapsulation represents a 'has a' (composition) relation.

* composition: holding the reference of the other class within some other class.






## Object Class
* It is the superclass for every class
```java
// 1
public String toString();

// 2
public boolean equals(Object another);
//compare reference of two objects

// 6
public int hashCode();
address
```


## String
### HashCode
```
for each character c in string:
	hashCode = c + 31 * hashCode
```
prime number: evenly distributed

* why choose 31?

### String pool and intern()  
There is a string pool in stack which contains non-duplicated strings. Those strings will be reused by references. intern() returns canonical copy of string from the string pool. Create a string using new keyword will first create a string in string pool and then copy it to heap to create an String object.


## Constant pool
If the value p being boxed is `true`, `false`, a `byte`, or a `char` in the range `\u0000` to `\u007f`, or an `int` or `short` number between `-128` and `127` (inclusive), then let `r1` and `r2` be the results of any two boxing conversions of p. It is always the case that `r1 == r2`.

11. New keyword  
	Create new object in heap.


## Immutable vs Final 
### Immutable
value can not be changed.

**\#1 How to define an immutable class**:
* declare class as final OR make the constructor private and construct instances in factory methods
* all fields are final and private
* constructor for initialization
* no setters - methods that modify fields or objects referred to by fields.
* *!!* If the instance fields include references to mutable objects, don't allow those objects to be changed:
  1. don't provide methods that modify the mutable objects.
  2. don't share references to the mutable objects. Never store references to external, mutable objects passed to the constructor if necessary, create copies, and store the references to the copies. Similarly, create copies of your internal mutable objects when necessary to avoid returning the originals in your methods.

Benefits: Immutable object is thread-safe and commonly used in multi-threading environment.

### Final
The value of the field cannot change.

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


## **\#2 clone() vs Cloneable**
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


## Generics
Type-safe

Without generics, collection api, some classes, some functions will return Object, so that we need to deal with downcast to proper class. If we use generics, it limits to a specified type, that is why it is type-safe.

Generics is used in collection api, class, function.

### WildCard
`List<?> list;`

* What is the different between Object and WildCard (?)
```java
List<String> list = new ArrayList<String>();
List<Object> list2 = list;	// compile error
List<?> list3 = list;		// works
```
Because, although `String` is a `Object`, `List<String>` is NOT a `List<Object>`. However, every `List` is a `List<?>`

### extend vs super
`List<? extend A> list`
* extend: only subclass

`List<? super A>list`
* super: itself or superclass

> note: A can be an interface