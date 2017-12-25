# test-spark (temporary name)

Boilerplate code when trying out Spark Framework

## Libraries

* **Spark Framework** (not Apache Spark) - Routing libraries. Uses Java 8 syntax.
* **Google Guice** - Will never write in Java without any dependency injection.
* **Liquibase** - Has rollback.
* **Jdbi** - I'm familiar with SQL. Don't need ORM. I can't copy paste jOOQ's syntax to test some query.
* **Lombok** - I'm just that lazy.
* **Google Guava** - Immutable collections.
* **Gson** - Nicer API.
* **Caffeine** - Was part of Guava, so...
* **HikariCP** - Must have for any database-backed app.
* **Junit** - Duh.
* **SLF4J** - Spark Framework's default.

## Known issues

* For Liquibase changelog, the schema version need to be the same with Liquibase version.
* HikariCP can't use MySQLDataSource for the config. Configure using jdbcUrl instead.
* There is no automatic `@OneToMany` mapping for Jdbi like in ORM. Just write a mapper for it.
* When having collections as a field in the POJO, Gson didn't map an empty collection when there is no data, it simply gives null.
