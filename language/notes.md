1. Java file without a public class  
	It runs normally. If a class has the same name as Java file name, it is default to be public.

2. main function without public or static
	Runtime error.

3. Constructor vs Other method  
	* constructor cannot have a return type. Other method have to has a return type.  
	* constructor with a return type will have warning, and the constructor method becomes a normal method.

4. Two groups of modifier  
	a. Accessibility (Public, Protected, Private)  
	b. static, final  
They can be in different order. But we usually put accessibility before static or final.

5. We can have final main.

6. Encapsulation & Inheritance  
	Inheritance -> 'is a'  
	Encapsulation -> 'has a' (composition)

7. Children inherit everything from parent, but it doesn't has the accessibility to private members of  parent.

8. Override vs Overload  
	Override: Same signature, in two different classes, different implementation.  
	Overload: Same function name, same return type, in same class, different parameters.

	> Different Parameters: different types, different order, different numbers.  
	> Signature: the method's name and the parameter types.

9. Object Class (Java 6)  
	```java
	// 1
	public String toString();

	// 2
	public boolean equals(Object another);
	```

10. String pool, and intern()  
	There is a string pool in stack which contains non-duplicated strings. Those strings will be reused by references. intern() returns canonical copy of string from the string pool. Create a string using new keyword will first create a string in string pool and then copy it to heap to create an String object.

11. New keyword  
	Create new object in heap.

12. Immutable vs Final  
	Final: reference can not be changed. But the value can be changed. 

	Immutable: value can not be changed.

	\#1 How to write an immutable class:
	* class is final
	* all members are final, private
	* only getters, no setters
	* constructors for initialization

	note: String is both final and immutable.

13. String vs StringBuffer vs StringBuilder  
	String is immutable.  
	StringBuffer, StringBuilder are mutable.  
	StringBuffer is thread-safe.  
	StringBuilder (Java 5) is not thread-safe which has better performance.

	note: thread-safe means only a thread can access the class at a time.

14.	Boxing vs Autoboxing  
	primitive type & wrapper class  
	boxing: convert primitive type to wrapper class object
	autoboxing (Java 5): 'Integer x = 10; int y = x;'

	note: all wrapper classes are immutable.

15. Upcast vs Downcast  
	* Primitive: Upcast, shorter byte to longer byte. Downcast, longer to shorter.  
	* Objects: Upcast by "is a". Downcast from parent reference to child.

16. (\#2) clone() vs Cloneable 
	```java
	protected Object clone() throws CloneNotSuppertedException
	```

	To use clone(), a class has to implement Cloneable, or it will throw CloneNotSuppertedException.