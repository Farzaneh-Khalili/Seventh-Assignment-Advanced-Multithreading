## Seventh Assignment 
# *Advanced Multithreading*
## *-PI calculater-*
- for the first exercise we had to write a program which calculate the value of pi number with exact flating numbers after"."
- we had to use *BigDecimal* number and their operation was a bit different from int and double etc.
- I used add instead of (+), multiply instead of ( * ) and divide method of **BigDecimal** instead of ( / ).
- and also used an object of *MathContext* for divide operation
- to show the number with exact floating numbers after ".", I used *setScale* method.
- I used 2 different way to calculate the pi number but both of them had different numbers after 7 floating number and only the first test case was correct.


## *-Priority Simulator-*
- for the second exercise we had to make a priority simulator system.
- there are three kind of threads : black, blue and white. and this code should be the way that first all of the black thread be executed, then the blue and at the end the white threads.
- as the guidelines in the Runner class told us, I used *CountDownLatch* class to make this priority system.
- first I added attribute *CountDownLatch* in every color thread and make their constructor functions with it.
- then in the **run** function I made 3 new CountDownLatch object for every color with value of their thread numbers. (for example : CountDownLatch CDLatchBlack = new CountDownLatch(blackCount); ).
- and after every for loop(of color threads' making) I used *await* method of CountDownLatch which puts the current thread on hold until the latch has counted down to zero.
- and how we make the latch count to zero? by using *countDown* method of the class *CountDownLatch* in the run method of every color class so whenever a thread executed, one is subtracted from the countdown number.
- I also added *printMessage()* method in **run()** function of every color class 

## *-Semaphore-*
- for the third exercise we had to write a code in which only 2 threads have access to the *Resource* by using **Semaphore**.
- to achieve this goal I made one new single Semaphore in the *main* function(with value of 2 because a maximum of 2 operators can have access the resource)
- this Semaphore will be shared between all the threads to be synchronized.
- then I add a Semaphore attributes to the  *Operator* class and also add it to the constructor method 
- in the run() function in the Operator class I used *acquire()* method of Semaphore class to acquire the lock
- and after the thread job was done completely, it will release it by using *release()* method of Semaphore class and then another thread can access to the source.
- when we use a semaphore with value of number 2 it means that only maximum of 2 threads can have access to the source at same time and other threads should wait for them to leave and release the permit and then they can have the source.
- I also add another attribute to the semaphore object : a boolean. and when I set it to true at the first place it will ensure that waiting threads are granted a permit in the order in which they requested access and will access to the source by order.
- to show time when a thread get access and when it will release it, I made an object of *DateTimeFormatter* class with the format of ("HH:mm:ss") and also an object of *LocalTime* class.
- I had problem showing the "release" and "get" permit because the "get permit" message would appear on the terminal before the "release message", and I fixed it by changing the line of code which was about showing the message.
- 