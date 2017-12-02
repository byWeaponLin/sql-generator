## 1 SQL-Generator Rule
这里我们需要设计一个**特定需求**下的`SQL-Generator`,其构造与数据库的SQL十分类似.主要区别在于我们有自定义函数(SQL也有自定义函数)和一个FOR LAST部分,这么说起来好像除了FOR LAST没什么区别了(当然除了这些,SQL里面的很多东西我们这里是没有的).先看一下`SQL-Generator`的构造:

    SELECT --- FROM --- WHERE --- FOR LAST ---
举个栗子: 

    SELECT name, age, gender FROM user WHERE avg(age) > 20 for last 10 events

在最初,由于时间关系,我们采用最简单粗暴的方式.从客户端接收参数,然后使用最简单的办法直接进行字符串拼接(没错,`SQL-Generator`都是在一个类中完成的,没有任何的抽象).

显然,这样的代码无法满足我们对性的需求(可扩展性,可维护性...),于是我们决定重新设计一版`SQL-Generator`.首先我们希望设计的sql是一种类似`fluent api`的形式,比如: 
    
    select.column(col1).col(col2).from(tbl).where().and(avg(col1)>10).and(col2<100).forMin(10)
这样的设计很直观很优雅,很符合一条sql语句的形式.

## 2 抽象
首先我们需要抽象,观察一下`select`、`from`、`where`、`for last`四个部分的特点：

**select part**: 
    
    1.column, 2.column as alias_name, 3.aggregate_function(column) as alias_name
**from part**: 
    
    table_name
**where part**: 
    
    left compare(>,<,=...) right, left和right部分可以是function/placeholder/numeric,function中也可以是字段、函数、四则运算等等
**for last part**: 
    
    x min/events

### 2.1 类结构图: 
![sql-generator-class-structure](images/sql-generator-class-structure.png)
解释一下每个类的作用:
* `NameOperand`: 抽象类,包含了name字段,以及自定义了toString(boolean)方法,boolean参数的原因是有些地方可能需要别名(select部分),而有些地方就不需要(where部分)
* `ColumnOperand`： 顾名思义,这是一个代表字段的类
* `TransformOperand`： 这是where部分的抽象类,定义了四则运算,以及比较的方法
* `CalculateOperand`： 四则运算,+,-,*,/ 
* `NumberOperand`： 数值,比如： 19,29.2
* `VariableOperand`： 占位符,比如： ?avg_age
* `FunctionOperand`： 聚合函数, `sum` ,`avg` , `count` , `max` , `min` ,比如： sum(ColumnOperand)
* `PeriodOperand`： 自定义函数
* `BitwiseOperand`： condition类,每个 `BitwiseOperand` 都是where部分的一个condition  


## 3 函数式编程
当上述类结构设计完之后,接下来便是编程的问题.首先我们从客户端接收传过来的参数,对于四则运算和比较符肯定是一个标识,这样的话,首先想到的便是使用switch或if...else的方式来实现,显然这种实现方式比较low,而且这么写看起来也比较臃肿,所以这里我们使用了functional programming来避免这些不好的地方.

比如常规做法,
```java
// common useage
public CalculateOperand arithmetic(TransformOperand left, String operator, TransformOperand right){
    switch(operator){
        case "ADD" :
            return left.add(right);
        case "MINUS" :
            return left.minus(right);
        case "MULTIPLY" :
            return left.multiply(right);
        case "DIVIDE" :
            return left.divide(right);
    }
    throw new IllegalArgumentException("invalid operator");
}
```
如果使用函数式编程的话,
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
    public static CalculateOperator getCalculator(@NonNull String operator) {
        try {
            return CalculateOperator.valueOf(operator);
        } catch (Exception e) {
            throw new IllegalArgumentException("illegal operator: " + operator);
        }
    }
    @Override
    public BiFunction<TransformOperand, TransformOperand, CalculateOperand> getFunction() {
        return function;
    }
}
// function programming
public CalculateOperator arithmetic(TransformOperand left, String operator, TransformOperand right){
    CalculateOperator calculator = CalculateOperator.getCalculator(operator);
    return calculator.getFunction().apply(left, right);
}
```

## 4 总结(一顿操作猛如虎,一看战绩零杆五)
* 抽象思维才是王道
* 函数式编程在这里就是秀操作