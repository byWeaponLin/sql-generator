## specific need for sql generator（abstract、stream and functional programming）

设计一个 **特定需求** 下的sql生成器,具体规则就不说明了，都在代码中。

SQL Format: SELECT --- FROM --- WHERE --- FOR LAST ---

for example: SELECT name, age, gender FROM user WHERE avg(age) > 20 for last 10 events # (a very simple example)
    
在最初，我们设计的方案是直接接受客户端传过来的参数，然后使用最简单的办法直接进行字符串拼接（没错，sql generator都是在一个类中完成的，没有任何的抽象），

显然，这样的代码可扩展性和可维护性非常低，于是我们重新设计了这一版的SQL Generator。

首先我们希望设计的sql是一种类似fluent api的形式，比如select.column(col1).col(col2).from(tbl).where().and(avg(col1)>10).and(col2<100).forMin(10),
这样的设计很直观，很符合一条sql语句的形式。

## 抽象
首先我们需要抽象，观察一下select、from、where、for-last四个部分的特定：
> select part: 1.column, 2.column as alias_name, 3.aggregate_function(column) as alias_name
> from part: table_name
> where part: left compare(>,<,=...) right, left和right部分可以是function/placeholder/numeric，function中也可以是字段、函数、四则运算等等
> for last part: x min/events

然后展示一下设计之后的类图:

解释一下每个类大概是干什么的：
> `NameOperand` : 抽象类，包含了name字段，以及自定义了toString(boolean)方法，boolean参数的原因是有些地方可能需要别名(select部分)，而有些地方就不需要(where部分)
> --`ColumnOperand` ： 顾名思义，这是一个代表字段的类
> --`TransformOperand` ： 这是where部分的抽象类，定义了四则运算，以及比较的方法
> ----`CalculateOperand` ： 四则运算，+，-，*,/
> ----`NumberOperand` ： 数值，比如： 19,29.2
> ----`VariableOperand` ： 占位符，比如： ?avg_age
> ----`FunctionOperand` ： 聚合函数， `sum` ,`avg` , `count` , `max` , `min` ，比如： sum(ColumnOperand)
> ----`PeriodOperand` ： 自定义函数
> ----`` ： 
> --`BitwiseOperand` ： condition类，每个 `BitwiseOperand` 都是where部分的一个condition  


## functional programming
当上述类结构设计完之后，接下来便是编程的问题，首先我们从客户端接收传过来的参数，

对于四则运算和比较符肯定是一个标识，这样的话，首先想到的便是：使用switch或if...else的方式来实现，
显然这种实现方法比较low，所以这里我们使用了functional programming。

比如常规做法：
```java
public CalculateOperand arithmetic(TransformOperand left, String operator, TransformOperand right){
    switch(operator){
        case "+" :
            return left.add(right);
        case "-" :
            return left.minus(right);
        case "*" :
            return left.multiply(right);
        case "/" :
            return left.divide(right);
    }
    throw new IllegalArgumentException("invalid operator");
}
```
如果使用函数式编程的话，
```java
@AllArgsConstructor
public enum CalculateOperator implements Operator<CalculateOperand> {
    ADD("+", TransformOperand::add),
    MINUS("-", TransformOperand::minus),
    MULTIPLY("*", TransformOperand::multiply),
    DIVIDE("/", TransformOperand::divide);
    private String operator;
    private BiFunction<TransformOperand, TransformOperand, CalculateOperand> function;
    @Override
    public String getOperator() {
        return operator;
    }
    @Override
    public BiFunction<TransformOperand, TransformOperand, CalculateOperand> getFunction() {
        return function;
    }
}
//当我们使用的使用
public CalculateOperator usage(TransformOperand left, String operator, TransformOperand right){
    Operator<BitwiseOperand> compare = getOperatorType(operator);  //简化操作
    //如果operator=<,此时的compare就代表了一个<的操作
    return compare.getFunction().apply(left, right);
}
```


### what is functional programming?

可以把函数理解为一个对象，


## stream
java stream的操作
### reduce

### 



























