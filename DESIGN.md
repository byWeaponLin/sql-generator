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

select().column("id").column("name").column("score")
    .from("students")
    .build();
// ==> select id, name, socre from students;

select().column("id").column("name").column("score")
    .from("students")
    .where().and(column("class_id").eq(11))
    .orderBy(column("score"))
    .build();
// ==> select id, name, score from students where class_id = 11 order by score;

select().count()
    .from("students")
    .where().and(column("class_id").eq(11))
    .build();
// ==> select count(1) from students where class_id = 11;

select().max(column("score"))
    .from("students")
    .where().and(column("class_id").eq(11))
    .build();
// ==> select max('score') from students where class_id =  11;

select().columns()
    .from("students")
    .groupBy(columns("class_id"))
    .orderBy(column("class_id"))
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
    .where().and(column("name").like('%zhang')).and(column('class_id').in(1,2,3,4)).and(column("score").between(60, 100))
    .limit(100)
    .build();
// ==> select * from students where name like '%zhang' and class_id in (1,2,3,4) and score between 60 and 100 limit 100;    

select().columns()
    .from("students")
    .where().and(column("class_id").eq(1))
    .orderBy(column("score"))
    .desc()
    .limit(10, 10)
    .build();
// ==> select * from students where class_id = 1 order by score desc limit 10, 10;
```

### 1.2 insert

```java
insert().into("students")
    .column("id").column("name").column("age")
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
    .set(column("name").eq("zhangsan2")).set(column("score").eq(99.5))
    .build();
// ==> update students set name = 'zhangsan2', score = 99.5;

update().table("students")
    .set(column("name").eq("zhangsan2")).set(column("score").eq(99.5))
    .where().and(column("name").eq("zhangsan"))
    .build();
// ==> update students set name = 'zhangsan2', score = 99.5 where name = 'zhangsan';
```

### 1.4 delete

```java
delete().from("studens")
    .where().and(column("id").eq(1111))
    .build();
// ==> delete from students where id = 1111;

delete().from("students")
    .where().or(columns("score").lt(60)).or(columns("age").gt(22))
    .build();
// ==> delete from students where score < 60 or age > 22;
```

## 2. 类设计图