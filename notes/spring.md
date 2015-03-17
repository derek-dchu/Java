# Spring
## Modules
1. IoC: Inversion of Control
2. AOP: Aspect-Oriented Programming
3. DAO (ORM)
4. MVC (Web)
5. Spring Security
6. Spring Integration (Struts2, REST, SOAP)
7. Spring Front-end

* Each of module has their own jarfiles

**note:** Spring 2.x comes with `lib` folder contains (dependent library jarfiles), Spring 3.x ship without `lib` folder.

## (#8) Configuration File
### Header


## `log4j` module
Only one singleton class: `Logger`.

```java
Logger log = Logger.getLogger();

log.append()
log.debug()
log.warm()
log.info()
```

## IoC ~ Dependency Injection (DI)
Factory Pattern: id always reference an object
Reflection: inject values into fields
Decoupling: decouple Data & Logic

### Scope
1. singleton (default)
2. prototype (shallow copy)
3. request
4. session
5. global session

### DI
1. Setter Injection	(use setter to inject fields)
```xml
<bean id="<id>" class="<package.class>" scope="">
	<property name="<field>" value="" />
	<property name="<field>">
		<value>??</value>
	</property>
</bean>
```

**note:** the class may not have a field that matches exactly the property name, but it must has a setter matching that property name (`public void set<field>`).

2. Constructor Injection


* What is method injection?
* autowire: automatically injection other beans inside current bean (usually `ref` is better)
  1. no
  2. byName: use field name to match other beans
  3. byType: user field type to match other beans (but this cannot handle the situation that different beans have same type or subtype)
  4. constructor
  5. autodetect (spring 2.x, deprecated)


#### Singleton Injection
By default Spring uses reflection to bypass private constructor and instantiate an new object. Therefore, a singleton in Spring will return different objects under scopes except singleton.

* We HAVE to specify `factory-method="getInstance"` to force Spring get a singleton object in all scopes.
* Without `factory-method`, even in singleton scope, we will have two objects from a singleton. One is from reflection API, the other one is from `getInstance()`.


## Lazy Loading
BeanFactory	(all lazy loaded) (mobile application)
	^
	|
ApplicationContext (not lazy loaded) : all beans are created after `new FileSystemXmlApplicationContet("<config.xml>")` (enterprise application)

* We control lazy loading feature for both of them by setting `lazy-init="default|true|false"`.


## AOP: Aspect Oriented Programming
* Purpose of AOP: Build dependency among separate classes
* Key concepts:
  1. Advice (what)
  2. Pointcut (where)
  3. Advisor = Advice + Pointcut
* Proxy Pattern


## Cache (Spring 3.x)


## Spring DAO
CMP: Container-Managed Persistence
BMP: Bean-Managed Persistence


## Transaction Propagation
```
BeginTx
	BeginTx
		...
	Commit
	...
	BeginTx
	Commit
Commit
```

* Reqiured:
```
BT 						BT
	#do1 					#do1
	BT 						#do2
		#do2	=> 			#do3
	CT 					CT
	#do3
CT
```
Any inner transaction fails will rollback the outer transaction (Default behavior of propagation)
