
## JSP & Servlet
### Servlet
Servlet can only has one instance and by default, it is lazy loaded: Server will only instantiate each servlet once and only once when there is a request for that servlet.

note: we can force it to be loaded when server start by setting `<load-on-startup>1</load-on-startup>`.

### Lifecycle of Servlet
1. `init()`: execute once & only once.
2. `service()`: call -> `doGet`, `doPost`, ...
3. `destroy()`

### Servlet Mapping
#### webapp 3.x

Use annotation

```
form action="xyz"
		|
		| submit
		v
 @WebServlet("/xyz")
 public class SomeServlet extends HttpServlet { ... }
```

#### webapp 2.x
```
form action="xyz"
		|
		| submit
		v
	web.xml

<servlet-mapping>
	<servlet-name>xyz</servlet-name>
	<url-pattern>/xyz</url-pattern>
</servlet-mapping>
<servlet>
	<servlet-name>xyz</servlet-name>
	<servlet-class><package>.XYZServlet</servlet-class>
<servlet>
```

### JSP Lifecycle
1. `jspinit()`
2. `_jspService()`
3. `jspDestroy()`

### JSP Implicit Objects
Scope:
  1. page: Object can only be visible in current page (private).
  2. request: all data from a form by default are in request scope. It can only exists between two pages (original and request page).
  3. session: Object -> any page for only one user.
  4. application: Object -> any page, any user (public)
Other: response, out, pageContext, config.

### JSP Tags `<% ... %>`
1. JSP Expression: `<%= ... %>`
2. JSP Declaration: `<%! ... %>`
  ```
  <%! int x = 10; %>
  <%! int max(int a, int b) {
  	return a > b ? a : b;
  }
  %>
  ```
3. JSP Scriptlet: `<% ... %>`, can not declare a function
4. JSP Direct: `<%@ ... %>`
  1. Page Direct: `<%@page import="..." %>`
  2. Include Direct: `<%@include file="..." %>`
  3. Taglib Direct: `<%@taglib uri="..." prefix="..." %>`

    * uri: uniform resource identifier.

5. `<jsp:useBean id="..." scope="..." class="..." />` => `<class> <id> = (<class>)<scope>.getAttribute("<id>")` or `<class> <id> = new <class>()` if can not find in `<scope>`.
6. `<jsp:getProperty name="..." property="..." />` => `<name>.get<property>()`.
7. `<jsp:setProperty name="..." property="..." value="..." /> => `<name>.set<property>(value)`.

### Multithreading in JSP & Servlet
Servlet itself is thread-safe. However, commonly we will contain shareable service within servlet. For those services, if it is stateful with mutable objects, then it is not thread-safe, in contrast, if it is stateless (all local variables, no shared fields), than it is still thread-safe.

### JSP vs Servlet
A JSP page can be compiled to a Servlet.

### `doGet()` vs `doPost()`
`GET`: transfer via url. Faster. Limited data.
`POST`: transfer a package. More securer. unlimited data.


## Forward vs Redirect
page1 -- forward --> page2 => url: page1, content: page2

```java
<jsp:forward page="..." />
```

page1 -- redirect --> page2 => url: page2, content: page2
```java
<%
	response.sendRedirect("hello.jsp");
%>
```

## JSTL + EL (Expression Language)
1. core	(c)
2. formatting	(fmt)
3. xml	(x)
4. sql	(sql)

## How to create project
Top-down (Design)

Bottom-up (Implementation)
1. Bean
2. DAO
3. JDBC util (try not to make it as static. Create an object if we need)
4. services
5. action
6. setup web.xml (fixed settings)
7. setup struts-config.xml


## Struts
<form action="<name>.do"> ---> <action path="/<name>" ... />

### Validation
1. Use ValidatorForm instead of ActionForm, override validate method.
2. Apply Validation framework
  1. Add a plugin in struts-config.xml to specify two xml files:
    1. validator-rules.xml: general rules that are independent from projects (built-in)
    2. validation.xml: user defined rules
  2. Define validation.xml under `WEB-INF`
  3. Use DynaActionForm instead of ActionForm
  4. set `validate="true"`

### Action
ForwardAction & DispatchAction


## Hibernate
* Each bean must has an ID for caching.

### Caching
Try to reuse the existing object in the session, in order to reduce the number of connection with database.

#### First-level Caching
Caching on Session: cache within current session.

* Each object has three status in a session:
  1. Transient: an object 
  2. Persistent:
  3. Detached:

```java
A a = new A(); // Transient (map doesn't has a's id)

map.put(id, a) // Persistent

map.put(id, null) // Detached (map still has a's id)

map.put(id, a) // Persistent

load(a): 
	if map.contains(id):
		return map.get(id)
	else:
		return map.put(id, a)
```

* `session.evict(object)` changes the object from persistent to detached.
* `session.merge(object)` changes the object from detached to persistent of current session.
* Change non-id fields of persistent object, database will be updated after the transaction.
* Change id of persistent object, database will insert an new record with that id after the transaction.

#### Second-level Caching
Caching on SessionFactory: shallow copies will be shared across different sessions.

**note:** Each level of Caching can only store objects with unique ID.

### HQL vs SQL
```
- SQL:
SELECT s.name FROM Sample s WHERE s.age > 30;
		 |			 |    |			|
	   column      table alias    column

- HQL:
SELECT u.name FROM User as u where u.age > 30;
		 |			 |     |		|
       field      class   obj      field

-note: HQL does not support '*', we use 'from User' directly.
```

**note:** The translation of HQL is independent with mapping configuration.

### `load` vs `get()`
* if we use object as the key (which is a must for composite key), then for `load()`, it will return an new object as the result, in contrast, `get()` will fill in the object and return it.

### generator tag
class:
  1. assigned (default): assigned by user
  2. increment: max(id)+1
  3. sequence
  4. foreign
  5. native
  6. hilo

### Lazy Fetching
```java
class Node<E> {
	E value;
	Node<E> parent;
	Set<Node<E>> children;
}
```

By default, Hibernate will only load current object without loading any of its dependent objects (parent, children) unless we access them.

* Lazy Fetching can be disable by setting `lazy="false"` for its dependent sets.
* Lazy Fetching dependent objects can be forced to initialize by calling `Hibernate.initialize(object)`.

**note:** Lazy Fetching is different from Lazy Loading. Lazy Loading will not load current object until we access it.

### Transaction
A: Atomicity -
C: Consistency - 
I: Isolation -
D: Durability -

#### Isolation Levels
1. Read uncommitted (no lock) - may cause dirty read (read dirty data)
2. Read committed (prevent dirty read) (default level) - affected rows are locked
3. Repeatable Read (prevent non-repeatable read)
4. Serializable (prevent phantom read)

* dirty read: T2 update, T1 read before T2 commit. Because reading is faster than update, at some point, T1 will start reading non-updated data.

#### Pessimistic locking vs Optimistic locking
Optimistic locking: no lock, but we can add an indicator column (such as version), then we can use it to control reading without a lock.

### Hibernate Session vs JPA EntityManager
| SQL | HS | EM |  
|-----|----|----|  
| insert | save(obj) | persist(obj) |  
| load an obj | load/get(<class>, id) | find(<class>, id) |  
| update | load/get + setter | find + setter |  
| delete | delete(obj) | remove(obj) |  
| select | createQuery(hql) | createQuery(JPAquery) |  
| get a result | query.list() | query.getResultList() |  


## EJB
EJB: Distributed System

S1 -- S2 -- ... -- Sn (EJB run any of there servers)
 \     |           /
 	Interface (DAO)
 	   | (remote)
 	 Client
