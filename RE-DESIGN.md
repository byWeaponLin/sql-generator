## Universal-SQL-Generator
通用的SQL生成器

## 0. We should know everything about SQL so that we can design a Universal-SQL-Generator

## 1. What does Universal-SQL-Generator like?

假设有个名为`students`的表，表结构设计如下：
```sql
create table students (
  id int primary key autoincrease;
  `name` varchar(32),
  age int,
  score decimal(10,2),
  class_id int
);
```

### 1.1 select

期望的SQL API的设计 
```java
select().columns()
    .from("students")
    .build();
// ==> select * from students; 

select().operand("id").operand("name").operand("score")
    .from("students")
    .build();
// ==> select id, name, socre from students;

select().operand("id","name","score")
    .from("students")
    .where().and(operand("class_id").eq(11))
    .orderBy(operand("score"))
    .build();
// ==> select id, name, score from students where class_id = 11 order by score;

select().count()
    .from("students")
    .where().and(operand("class_id").eq(11))
    .build();
// ==> select count(1) from students where class_id = 11;

select().max(operand("score"))
    .from("students")
    .where().and(operand("class_id").eq(11))
    .build();
// ==> select max('score') from students where class_id =  11;

select().columns()
    .from("students")
    .groupBy(columns("class_id"))
    .orderBy(operand("class_id"))
    .desc()
    .build();
// ==> select * from students group by class_id order by class_id desc;

select().columns()
    .from("students")
    .groupBy(columns("class_id"))
    .having(count('class_id').gt(10))
    .build();
// ==> select * from students group by class_id having count('class_id') > 10;

select().columns()
    .from("students")
    .where().and(operand("name").like('%zhang')).and(operand('class_id').in(1,2,3,4)).and(operand("score").between(60, 100))
    .limit(100)
    .build();
// ==> select * from students where name like '%zhang' and class_id in (1,2,3,4) and score between 60 and 100 limit 100;

select().columns()
    .from("students")
    .where().and(operand("class_id").eq(1))
    .orderBy(operand("score"))
    .desc()
    .limit(10, 10)
    .build();
// ==> select * from students where class_id = 1 order by score desc limit 10, 10;

select().columns()
    .from("students")
    .where().and(operand("class_id").eq(1)).and(expression().or(operand("age").gt(20)).or(operand("score").le(90)))
    .build();

// ==> select * from students where class_id = 1 and (age > 20 or score <= 90)
```

### 1.2 insert

```java
insert().into("students")
    .operand("id").operand("name").operand("age")
    .value(1111).value("zhangsan").value(22)
    .build();
// ==> insert into students (id, name, age) values (1111, 'zhangsan', 22);

insert().into("students")
    .columns(new String[]{"id", "name", "age"})
    .values(new Object[]{1111, "zhangsan", 22})
    .build();
// ==> insert into students (id, name, age) values (1111, 'zhangsan', 22);

insert().into("students")
    .columns(new String[]{"id", "name", "age"})
    .values(new Object[]{1111, "zhangsan", 22})
    .values(new Object[]{1112, "lisi", 21});
// ==> insert into students (id, name, age) values (1111, 'zhangsan', 22), (1112, 'lisi', 21);
``` 

### 1.3 update

```java
update().table("students")
    .set(operand("name").eq("zhangsan2")).set(operand("score").eq(99.5))
    .build();
// ==> update students set name = 'zhangsan2', score = 99.5;

update().table("students")
    .set(operand("name").eq("zhangsan2")).set(operand("score").eq(99.5))
    .where().and(operand("name").eq("zhangsan"))
    .build();
// ==> update students set name = 'zhangsan2', score = 99.5 where name = 'zhangsan';
```

### 1.4 delete

```java
delete().from("studens")
    .where().and(operand("id").eq(1111))
    .build();
// ==> delete from students where id = 1111;

delete().from("students")
    .where().or(columns("score").lt(60)).or(columns("age").gt(22))
    .build();
// ==> delete from students where score < 60 or age > 22;
```

## 2. 抽象
### 2.1 SQL Expression(SQL表达式)
**1.Compare Expression**

主要集中在`where`部分
- score > 90
- age IN (20, 21, 22)
- name IS NULL
- age between 20 and 23
- name LIKE '%X'
- score + 5 >= 60
- count(score) > 10

**2.Nested Expression(累加多个比较表达式)**

主要集中在`where`部分
- (score > 90 and age IN (20, 21, 22) and class_id = 1)

**3.Others(Include: Columns, Function, Compare Expression, Arithmetic Expression)**

主要集中在select部分
- name AS nm
- MAX(score) AS maxScore
- score > 90
- score + 5

### 2.2 Expression Operand(表达式操作数)
- Column
- Value, Values
- Arithmetic
- Function

其实这些操作数都可以当做一个SQL表达式


## 3. 设计图