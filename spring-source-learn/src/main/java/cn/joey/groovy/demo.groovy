import org.springframework.context.ApplicationEvent

def listener ={ e -> println "Clicked on $e.source"}
assert listener instanceof Closure
Closure callback = {println 'Done!'}
Closure<Boolean> isTextFile = {
    File it -> it.name.endsWith('.txt')
}


//闭包的参数，可以直接是参数名，可以加参数的类型，如int i，也可以直接写一个默认值，如int b=2等
def isOdd = { int i-> i%2!=0}
println isOdd(10)
println isOdd.call(11)

def closureWithOnearg ={ str-> str.toUpperCase()}
assert closureWithOnearg('groovy') == 'GROOVY'

def closureWithTwoArgAndDefaultValue = {int a,int b=2 -> a+b}
assert closureWithTwoArgAndDefaultValue(1) ==3

//当闭包没有指定明确的参数时，有一个默认的内部参数it，可以拿来使用
//If you want to declare a closure which accepts no argument and must be restricted to calls without arguments,
// then you must declare it with an explicit empty argument list
// 如果声明一个闭包时，没有参数，则调用时也一定不能有参数，否则的话，会报错
def greeting = {"Hello, $it!"}
println greeting('Patrick')

def concat2 = { String[] args -> args.join('')}
assert concat2('abc','def') == 'abcdef'

class Enclosint{
    void run(){
        def whatIsThisObject = { getThisObject()}
        assert whatIsThisObject ==this
        println whatIsThisObject
        def whatIsThis = { this}
        assert whatIsThis() ==this
        println whatIsThis
    }
}

class EnclosingInInnerClass{
    class Inner {
        Closure cl = {this}
    }
    void run(){
        def inner = new Inner()
        assert inner.cl() ==inner
        println inner.cl()
    }

}

class NestedClosures{
    void run(){
        def nestedClosures = {
            def cl = {this}
            cl()
        }
        assert nestedClosures() ==this
        println nestedClosures()
    }
}

//class Person{
//    String name
//}
//class Thing{
//    String name
//}
//def p = new Person(name:'Norman')
//def t = new Thing(name:'Teapot')

//def upperCasedName = {delegate.name.toUpperCase()}
//upperCasedName.delegate = p
//println upperCasedName() =='NORMAN'
//upperCasedName.delegate = t
//println upperCasedName() =='TEAPOT'
//
//
//def target = p
//def upperCasedNameUsingVar= {target.name.toUpperCase()}
//assert upperCasedNameUsingVar() == 'NORMAN'

// -------------
//def p = new Person(name:'Igor')
//def cl = {name.toUpperCase()}
//cl.delegate=p //当把cl的委托赋值给p后，name属性就能访问了
//assert cl() =='IGOR'


class Person{
    String name
    def pretty = {"My name is $name"}
    String toString(){
        pretty()
    }
}
class Thing{
    String name
}
def p = new Person(name:'Sarah')
def t = new Thing(name:'Teapot')
assert p.toString() =='My name is Sarah'
p.pretty.delegate=t
assert p.toString() =='My name is Sarah'

p.pretty.resolveStrategy=Closure.DELEGATE_FIRST
assert p.toString() =='My name is Teapot'


def volume ={double l,double w,double h -> l*w*h}

def fixedWidthAndHeight = volume.ncurry(1,2d,4d)
println volume(3d,2d,4d)

println volume (3d,2d,4d) == fixedWidthAndHeight(3d)

// 费布那切数列
def fib
//fib= {long n -> n<2?n:fib(n-1)+fib(n-2)}
//assert fib(15) == 610 // slow!

// 可以使用memoize() 方法把之前计算的值缓存下来，而不需要一次又一次的去计算fib(14),fib(13)这些值
fib = {long n -> n<2?n:fib(n-1)+fib(n-2)}.memoize()
assert fib(25) ==75025





