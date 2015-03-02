# Multithreading & Concurrency
## How to create a thread?
1. 
```java
class A extends Thread {
	@Override
	public void run() { ... }
}

A a = new A();
a.start();
```

2.
```java
class B implements Runnable {
	@Override
	public void run() { ... }
}

B b = new B();
new Thread(b).start();
```

Method 2 is better than method 1, because it is more flexible (B can still extend another class).

* can we call `a.run()`, `b.run()` directly?
Yes, it will be called by current thread.

### `Thread` class
* implements `Runnable` interface
* contains a `Runnable` object
* follows Decorator pattern

### `start()`
The only way to start an new thread.

* Calling it will:
  1. create a separate thread
  2. call `run()` method
* `start()` can be only called once, or it throws a `IllegalThreadStateException`.


## Thread Lifecycle
4 stages:
1. READY:
  * to Running
2. RUNNING
  * back to Ready: `join()`, `yield()`
  * to Waiting: `sleep()`, `wait()`
  * to Dead
3. DEAD
4. WAITING
  * back to Ready: `notify()`, `notifyAll()`

```java
public final void wait() throws InterruptedException;
public final void notify();
public final void notifyAll();
```

### `join()`
It is like a stop sign

* who calls others `join()`, who will wait for joined threads.

### `yield()`
It is like a yield sign.

* `yield()` is a static method.
* Any runnable calls `yield()` will make their thread back to READY and wait for OS to pick it up.

### `sleep()`
* `Thread.sleep(ms)` is a static method.
* Runnable controls its waiting time.

### `wait()`
Put current thread in waiting status and release the lock.

* Runnable has to wait for `notify()` or `notifyAll()`.
* If we use `wait()` without synchronized, it will throw `IllealMonitorException`.

### 'notify'
Wake up the thread who is waiting for the lock, and release the lock.

* If we use `notify()` without synchronized, it will throw `IllealMonitorException`.

### 'notifyAll'


## Daemon Thread
Daemon Thread is the thread that keep running until all other threads stop and force to stop by OS.

* Garbage collection is a daemon thread.

### Convert regular thread to daemon thread
```java
t.setDaemon(true);
t.start();
```

## Synchronized
```java
synchronized void foo() { ... }
synchronized (obj) { ... }
```

### Lock <=> Monitor
* methods don't have lock
* to call a synchronized static method, the caller requires the lock of the class which contains that method. (class level lock)
* to call a synchronized non-static method, the caller requires the lock of the object which contains that method. (object level lock)
* a class level lock can be converted to object level lock

```java
class A {
	static void foo2() {
		synchronized(A.class) {
			/* ... */
		}
	}
}
```


## Thread-safe
1. the class is thread-safe: only one thread can access that class at a time.
2. the program is thread-safe: return consistence output.


## Mutex


## Semaphore


## How to prevent dead lock
1. Use `Lock` class (safe lock)


## `volatile` keyword
* If an object is `volatile`, then all thread can read it, but only one thread can write.
* If a thread modify a `volatile` object, this modification will be seen by all other threads immediately.


## How to stop a thread?
Doesn't support. Because stop a thread during running may lead to memory leak.


## 'interrupt()'
It throws an exception.


## ThreadLocal
ThreadLocal is like an agent of shared resource. It wraps shared resource, and different threads will have their own shallow copies of the shared resource from ThreadLocal.

```java
class ThreadLocal<V> {
	public V get();
	public void set(V value);
	protected V initialValue();
}
```

* non-blocking algorithm.
