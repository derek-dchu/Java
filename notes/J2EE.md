
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
Other: response, out, pageContext, config, exception.

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
```
<form action="<name>.do"> ---> <action path="/<name>" ... />
```

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





## EJB
EJB: Distributed System

S1 -- S2 -- ... -- Sn (EJB run any of there servers)
 \     |           /
 	Interface (DAO)
 	   | (remote)
 	 Client



## Security
### Authentication
```
web.xml

<login-config></login-config>
```

### Authority (Access Control)
```
web.xml

<security-constraint></security-constraint>
```


## JSF: Java Server Face


## SSH: Struts ~ Spring ~ Hibernate
Version #1: Struts 1.x ~ Spring 2.x ~ Hibernate 3.2
Version #2: Struts 2.x ~ Spring 3.x ~ Hibernate 3.5
Version #3: Spring MVC ~ Spring ~ Hibernate

## Java Naming and Directory Interface (JNDI)
An API to access the directory and naming services.
     
### Naming Concepts
Naming service a fundamental facility in any computation system which is mapping people friendly names to objects.

### Context
A *context* is a set of name-to-object bindings.

### Naming Systems and Namespaces
A naming system is a connected set of contexts of the same and provides a common set of operations.

A *namespace* is the set of all possible names in a naming system.


### Directory Concepts
Many naming services are extended with a *directory service*. A *directory service* is a service that provides operations for creating, adding, removing, and modifying the attributes associated with objects in a directory.

directory service = naming service + objects containing attributes.

### `javax.naming` Package
#### `Context` Interface
1. `Object lookup(Name name)`: lookup the object bound to a name.
2. `listBindings()`: an enumeration of name-to-object bindings.
3. `list()`: an enumeration of names containing an object's name and the name of the object's class.
4. `Name` interface: represents a generic name.
5. `Reference` class: represents reference.

#### `InitialContext` Class
In the JNDI, all naming and directory operations are performed relative to a context which is an `InitialContext` object.

#### Exceptions
`NamingException`: Root of the class hierarchy for exceptions in JNDI.

### `javax.naming.directory` Package
#### `DirContext` Interface
1. `getAttributes()`: retrieve the attributes associated with a directory entry.
2. `modifyAttributes()`: add, replace, or remove attributes and/or attributes value.
3. `search()`: content based searching of the directory.

### `javax.naming.ldap` Package

